package com.straata.psigate;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * PSIPaymentProcessor: PSIGate Payment Processor
 *
 * @author Kasra Rasaee
 * @since 2016-04-21.
 */
public class PSIPaymentProcessor {

  static Logger LOGGER = LoggerFactory.getLogger(PSIPaymentProcessor.class);

  PSIPaymentConnectionDetails connectionDetails;

  private boolean production = false;

  /**
   * Initialize PSIPaymentProcessor service
   *
   * @param production
   * @param storeID merchant store id, must have a production merchant id with PSIGate
   *                PSIGate mandatory if producution == true
   * @param passphrase merchant store passphrase, must have a production merchant id with PSIGate
   *                   mandatory if production == true
   */
  public PSIPaymentProcessor(boolean production, String storeID, String passphrase) {
    this.production = production;
    if (production) {
      this.initializeProduction(storeID, passphrase);
    } else {
      this.initializeTesting(storeID, passphrase);
    }
  }

  /**
   * Submit PSIGate Transaction Order
   *
   * @param order
   */
  public OrderResponse order(PSIOrder order) throws ValidationErrorsException {
    order.setStoreID(this.connectionDetails.getStoreID());
    order.setPassphrase(this.connectionDetails.getPassphrase());
    return invoke(order);
  }


  /**
   * Initialize this payment instance for production use
   *
   * @param storeID
   * @param passphrase
   */
  protected void initializeProduction(String storeID, String passphrase) {
    if (storeID == null || storeID.length() == 0) {
      throw new NullPointerException("PSIGate StoreID cannot be null or empty for production use");
    }

    if (passphrase == null || passphrase.length() == 0) {
      throw new NullPointerException("PSIGate Passphrase cannot be null or empty for production use");
    }

    this.connectionDetails = new PSIPaymentConnectionDetails(
            storeID,
            passphrase,
            "https://secure.psigate.com:27934/Messenger/XMLMessenger",
            "https://secure.psigate.com:27934/Messenger/AMMessenger");

  }

  /**
   * Initialize this payment instance for testing use
   *
   * @param storeID
   * @param passphrase
   */
  protected void initializeTesting(String storeID, String passphrase) {
    this.connectionDetails = new PSIPaymentConnectionDetails(
            storeID == null ? "teststore" : storeID,
            passphrase == null ? "psigate1234" : passphrase,
            "https://dev.psigate.com:27989/Messenger/XMLMessenger",
            "https://dev.psigate.com:27989/Messenger/AMMessenger");
  }

  /**
   * Simple serialization method for PSIOrder
   *
   * @param order
   * @return
   * @throws JAXBException
   */
  protected String buildOrderXML(PSIOrder order) throws JAXBException {

    /// serialize the object
    ByteArrayOutputStream bout = new ByteArrayOutputStream();

    JAXBContext jaxbContext = JAXBContext.newInstance(PSIOrder.class);
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    // output pretty printed
    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    jaxbMarshaller.marshal(order, bout);

    String xml = bout.toString();

    LOGGER.debug(String.format("Serialized class [%s] to xml output: [%s]", order.getClass(), xml));

    return xml;
  }

  /**
   * Helper method to encapsulate XML bytes in a HttpEntity used by HttpClient and request body.
   *
   * @param order
   * @return
   * @throws JAXBException
   */
  protected HttpEntity buildOrderHttpEntity(PSIOrder order) throws JAXBException {
    String xml = this.buildOrderXML(order);

    // -- convert xml to byte array and create a new TEXT/XML HttpEntity
    byte[] buffer = xml.getBytes(Charset.forName("UTF-8"));
    return new ByteArrayEntity(buffer, ContentType.TEXT_XML);
  }

  /**
   * Simple XML deserialization helper method for OrderResponse returned from PSIGate
   *
   * @param is
   * @return
   * @throws JAXBException
   * @throws XMLStreamException
   */
  protected OrderResponse orderXMLToOrderResponse(InputStream is) throws JAXBException, XMLStreamException {

    JAXBContext jaxbContext = JAXBContext.newInstance(OrderResponse.class);
    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    OrderResponse response = (OrderResponse)unmarshaller.unmarshal(is);

    return response;
  }

  /**
   * Http POST order data (as xml) to PSIGate
   * @param order
   */
  protected OrderResponse invoke(PSIOrder order) throws ValidationErrorsException {
    LOGGER.debug(String.format("Validating %s", order));

    // throw validation errors exception if all mandatory fields are not
    // filled for specific Order (i.e. SaleOrder, PreAuthOrder, etc.)
    ValidationErrors errors = order.validate();
    if (errors != null) {
      throw new ValidationErrorsException(errors);
    }

    OrderResponse orderResponse = null;
    String orderResponseXML = null;

    // All is well and continue to deserialize and submit the request
    try {
      LOGGER.debug(String.format("Preparing PSIOrder[%s]", order));
      String xml = this.buildOrderXML(order);

      LOGGER.info(String.format("Building soap/xml for order %s", order.getOrderID()));
      HttpEntity entity =  buildOrderHttpEntity(order);

      // -- create a new http client for call
      HttpClient client = HttpClients.custom().build();

      // -- set the http and soap action headers value
      HttpUriRequest request = RequestBuilder.post()
              .setUri(this.connectionDetails.getTransactionManagerURL())
              .setCharset(Charset.forName("UTF-8"))
              .setHeader("Content-Type", "text/xml")
              .setEntity(entity)
              .build();

      // execute the rquest and get the response content for deserialization to OrderResponse pojo
      HttpResponse response = client.execute(request);
      InputStream is = response.getEntity().getContent();

      // TODO split the input stream using an input splitter (for storing as xml string and for deserialization)
//      IOUtils.copy(is, System.out);

      // deserialize xml to OrderResponse pojo and return to previous call
      orderResponse =  this.orderXMLToOrderResponse(is);
      return orderResponse;
    } catch (JAXBException e) {
      // TODO proper handling -- throw proper error
      LOGGER.error("Unable to initialize JAXB context and marshal or unmarshall object", order, e);
      throw new RuntimeException("Unable to process", e);
    } catch (IOException e) {
      // TODO proper handling -- throw network error?
      LOGGER.error("Unable to process PSIGate request", order, order, e);
      throw new RuntimeException("Connection issue", e);
    } catch (XMLStreamException e) {
      // TODO proper handling
      LOGGER.error("Unable to unmarshall order response", order, orderResponse, orderResponseXML, e);
      throw new RuntimeException("XML Deserialization error", e);
    }
  }


  /*


  public void testDeserialize() {

    StringBuilder sb = new StringBuilder();

    sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    sb.append("<Result>");
    sb.append("<TransTime>Fri Apr 29 12:36:03 EDT 2016</TransTime>");
    sb.append("<OrderID>2016042912360307619</OrderID>");
    sb.append("<TransactionType>SALE</TransactionType>");
    sb.append("<Approved>APPROVED</Approved>");
    sb.append("<ReturnCode>Y:123456:0abcdef::E:NNN</ReturnCode>");
    sb.append("<ErrMsg></ErrMsg>");
    sb.append("<TaxTotal>0.00</TaxTotal>");
    sb.append("<ShipTotal>0.00</ShipTotal>");
    sb.append("<SubTotal>10.00</SubTotal>");
    sb.append("<FullTotal>10.00</FullTotal>");
    sb.append("<PaymentType>CC</PaymentType>");
    sb.append("<CardNumber>......1111</CardNumber>");
    sb.append("<TransRefNumber>1bfa6c58eb2e1ba3</TransRefNumber>");
    sb.append("<CardIDResult></CardIDResult>");
    sb.append("<AVSResult>E</AVSResult>");
    sb.append("<CardAuthNumber>123456</CardAuthNumber>");
    sb.append("<CardRefNumber>0abcdef</CardRefNumber>");
    sb.append("<CardType>VISA</CardType>");
    sb.append("<IPResult>NNN</IPResult>");
    sb.append("<IPCountry>UN</IPCountry>");
    sb.append("<IPRegion>UNKNOWN</IPRegion>");
    sb.append("<IPCity>UNKNOWN</IPCity>");
    sb.append("</Result>");

    try {
      InputStream bais = new ByteArrayInputStream(sb.toString().getBytes());
      OrderResponse r = this.orderXMLToOrderResponse(bais);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


   */

  /*
  URL url = new URL("https://dev.psigate.com:7989/Messenger/XMLMessenger");
  HttpURLConnection connection = (HttpURLConnection)url.openConnection();
  connection.setDoInput(true);
  connection.setDoOutput(true);
  PrintWriter out = new PrintWriter(connection.getOutputStream());

  String strData = "";
  strData = "<PSIOrder>\r\n";
  strData = strData + "	<StoreID>teststore055</StoreID>\r\n";
  strData = strData + "   <Passphrase>testpass055</Passphrase>\r\n";
  strData = strData + "	<PaymentType>CC</PaymentType>\r\n";
  strData = strData + "	<TestResult>R</TestResult>\r\n";
  strData = strData + "   <OrderID>Test123</OrderID>\r\n";
  strData = strData + "	<Userid>User1</Userid>\r\n";
  strData = strData + "	<Baddress1>123 Main St.</Baddress1>\r\n";
  strData = strData + "	<Tax1>1</Tax1>\r\n";
  strData = strData + "	<Tax2>2</Tax2>\r\n";
  strData = strData + "	<Tax3>3</Tax3>\r\n";
  strData = strData + "	<Tax4>4</Tax4>\r\n";
  strData = strData + "	<Tax5>5</Tax5>\r\n";
  strData = strData + "	<Shippingtotal>2.00</Shippingtotal>\r\n";
  strData = strData + "	<Subtotal>10.00</Subtotal>\r\n";
  strData = strData + "	<CardAction>0</CardAction>\r\n";
  strData = strData + "	<CardHolderName>Somebody</CardHolderName>\r\n";

  strData = strData + "	<CardNumber>4005550000000019</CardNumber>\r\n";
  strData = strData + "	<CardExpYear>08</CardExpYear>\r\n";
  strData = strData + "	<CardExpMonth>08</CardExpMonth>\r\n";
  strData = strData + "	<TransRefNumber></TransRefNumber>\r\n";
  strData = strData + "	<CustomerIP>216.220.59.201</CustomerIP>\r\n";
  strData = strData + "	<CardIDNumber>999</CardIDNumber>\r\n";
//				strData = strData + "	<CardXid>cardxid</CardXid>\r\n";
//				strData = strData + "	<CardECI>cardeci</CardECI>\r\n";
//				strData = strData + "	<CardCavv>cardcavv</CardCavv>\r\n";
//				strData = strData + "	<CardLevel2PO>cardlevel2po</CardLevel2PO>\r\n";
//				strData = strData + "	<CardLevel2Tax>cardlevel2tax</CardLevel2Tax>\r\n";
//				strData = strData + "	<CardLevel2TaxExempt>cardlevel2taxexempt</CardLevel2TaxExempt>\r\n";
//				strData = strData + "	<CardLevel2ShiptoZip>CardLevel2ShiptoZip</CardLevel2ShiptoZip>\r\n";
//				strData = strData + "	<AuthorizationNumber>123456</AuthorizationNumber>\r\n";
  //			strData = strData + "	<CardRefNumber>99999</CardRefNumber>\r\n";

  strData = strData + "	<Item>\r\n";
  strData = strData + "			<ItemID>apple</ItemID>\r\n";
  strData = strData + "			<ItemDescription>delicious apple</ItemDescription>\r\n";
  strData = strData + "			<ItemQty>2</ItemQty>\r\n";
  strData = strData + "			<ItemPrice>1.5</ItemPrice>\r\n";
  strData = strData + "	                  <Option>\r\n";
  strData = strData + "	                      <Colour1>Red</Colour1>\r\n";
  strData = strData + "	                      <Size1>11</Size1>\r\n";
  strData = strData + "	                      <Maker1>PSiGate11</Maker1>\r\n";
  strData = strData + "	                  </Option>\r\n";
  strData = strData + "		</Item>\r\n";
  strData = strData + "		<Item>\r\n";
  strData = strData + "			<ItemID>book</ItemID>\r\n";
  strData = strData + "			<ItemDescription>good book</ItemDescription>\r\n";
  strData = strData + "			<ItemQty>3</ItemQty>\r\n";
  strData = strData + "			<ItemPrice>2.5</ItemPrice>\r\n";
  strData = strData + "	                  <Option>\r\n";
  strData = strData + "	                      <Colour21>Red</Colour21>\r\n";
  strData = strData + "	                      <Size21>21</Size21>\r\n";
  strData = strData + "	                      <Maker21>PSiGate21</Maker21>\r\n";
  strData = strData + "	                  </Option>\r\n";
  strData = strData + "		</Item>\r\n";
  strData = strData + "		<Item>\r\n";
  strData = strData + "			<ItemID>computer</ItemID>\r\n";
  strData = strData + "			<ItemDescription>IBM computer</ItemDescription>\r\n";
  strData = strData + "			<ItemQty>1</ItemQty>\r\n";
  strData = strData + "			<ItemPrice>12</ItemPrice>\r\n";
  strData = strData + "	                  <Option>\r\n";
  strData = strData + "	                      <Colour33>Red</Colour33>\r\n";
  strData = strData + "	                      <Size33>33</Size33>\r\n";
  strData = strData + "	                      <Maker33>PSiGate33</Maker33>\r\n";
  strData = strData + "	                  </Option>\r\n";
  strData = strData + "		</Item>\r\n";

  strData = strData + "</PSIOrder>";
  out.println(strData);


  out.flush();
  out.close();

  // code to read response


  BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
  strReturn = new StringBuffer();

  boolean xxx = false;
  while ((inputLine = in.readLine()) != null)
  {
    xxx = true;
    strReturn.append("\r\n" + inputLine);
  }

  System.out.println("strReturn = " + strReturn);
  in.close();
  connection.disconnect();
*/

}
