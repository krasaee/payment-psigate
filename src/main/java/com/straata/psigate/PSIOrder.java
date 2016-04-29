package com.straata.psigate;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * PSIOrder: PSIGate Order POJO, represents a transaction request.
 *
 * @author Kasra Rasaee
 * @since 2016-04-21.
 *
 * @see PSISaleOrder
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Order")
public abstract class PSIOrder {
  /**
   * PSiGate provides the StoreID within the PSiGate Welcome
   * Email.  Note: StoreID is unique and case sensitive.
   *
   * Note: Limitation is 25 alphanumeric characters
   */
  @XmlElement(name = "StoreID", required = true)
  protected String storeID;

  /**
   * PSiGate provides the Passphrase within the PSiGate
   * Welcome Email.
   *
   * Note: Passphrase is case sensitive.
   */
  @XmlElement(name = "Passphrase", required = true)
  protected String passphrase;

  /**
   * The PaymentType element denotes the payment method.
   * CC – Credit Card
   *
   * Note: Currently, only the credit card payment method is available
   * real-time. PSiGate will add payment methods such as E-cheque and
   * ACH in the near future.
   */
  @XmlElement(name = "PaymentType", required = true)
  protected String paymentType = "CC";

  /**
   * You may set the TestResult element to simulate bank responses. PSiGate will return
   * a simulated transaction result once the transaction request passes fulfillment and
   * fraud checks.
   *  A – Simulates an approved response.
   *  D – Simulates a declined response.
   *  R – Randomly simulates an approved or declined response.
   *  F – Simulates a fraud response.
   */
  @XmlElement(name = "TestResult", required = false)
  protected String testResult;

  @XmlElement(name = "OrderID", required = false)
  protected String orderID;

  @XmlElement(name = "Userid", required = true)
  protected String userid;

  @XmlElement(name = "Baddress1", required = false)
  protected String baddress1;

  @XmlElement(name = "Tax1", required = false)
  protected BigDecimal tax1;

  @XmlElement(name = "Tax2", required = false)
  protected BigDecimal tax2;

  @XmlElement(name = "Tax3", required = false)
  protected BigDecimal tax3;

  @XmlElement(name = "Tax4", required = false)
  protected BigDecimal tax4;

  @XmlElement(name = "Tax5", required = false)
  protected BigDecimal tax5;

  @XmlElement(name = "Shippingtotal", required = true)
  protected BigDecimal shippingTotal;

  @XmlElement(name = "Subtotal", required = true)
  protected BigDecimal subTotal;

  @XmlElement(name = "CardAction", required = true)
  protected CardAction cardAction;

  @XmlElement(name = "CardHolderName", required = true)
  protected String cardHolderName;

  @XmlElement(name = "CardNumber", required = true)
  protected String cardNumber;

  @XmlElement(name = "CardExpYear", required = true)
  protected String cardExpYear;

  @XmlElement(name = "CardExpMonth", required = true)
  protected String cardExpMonth;

  @XmlElement(name = "TransRefNumber", required = false)
  protected String transRefNumber;

  @XmlElement(name = "CustomerIP", required = false)
  protected String customerIP;

  @XmlElement(name = "CardIDNumber", required = true)
  protected String cardIDNumber;

  @XmlElement(name = "Item")
  List<OrderItem> items;

  public PSIOrder() {
    super();
  }

  public String getStoreID() {
    return storeID;
  }

  public void setStoreID(String storeID) {
    this.storeID = storeID;
  }

  public String getPassphrase() {
    return passphrase;
  }

  public void setPassphrase(String passphrase) {
    this.passphrase = passphrase;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  public String getTestResult() {
    return testResult;
  }

  public void setTestResult(String testResult) {
    this.testResult = testResult;
  }

  public String getOrderID() {
    return orderID;
  }

  public void setOrderID(String orderID) {
    this.orderID = orderID;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getBaddress1() {
    return baddress1;
  }

  public void setBaddress1(String baddress1) {
    this.baddress1 = baddress1;
  }

  public BigDecimal getTax1() {
    return tax1;
  }

  public void setTax1(BigDecimal tax1) {
    this.tax1 = tax1;
  }

  public BigDecimal getTax2() {
    return tax2;
  }

  public void setTax2(BigDecimal tax2) {
    this.tax2 = tax2;
  }

  public BigDecimal getTax3() {
    return tax3;
  }

  public void setTax3(BigDecimal tax3) {
    this.tax3 = tax3;
  }

  public BigDecimal getTax4() {
    return tax4;
  }

  public void setTax4(BigDecimal tax4) {
    this.tax4 = tax4;
  }

  public BigDecimal getTax5() {
    return tax5;
  }

  public void setTax5(BigDecimal tax5) {
    this.tax5 = tax5;
  }

  public BigDecimal getShippingTotal() {
    return shippingTotal;
  }

  public void setShippingTotal(BigDecimal shippingTotal) {
    this.shippingTotal = shippingTotal;
  }

  public BigDecimal getSubTotal() {
    return subTotal;
  }

  public void setSubTotal(BigDecimal subTotal) {
    this.subTotal = subTotal;
  }

  public CardAction getCardAction() {
    return cardAction;
  }

  public void setCardAction(CardAction cardAction) {
    this.cardAction = cardAction;
  }

  public String getCardHolderName() {
    return cardHolderName;
  }

  public void setCardHolderName(String cardHolderName) {
    this.cardHolderName = cardHolderName;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getCardExpYear() {
    return cardExpYear;
  }

  public void setCardExpYear(String cardExpYear) {
    this.cardExpYear = cardExpYear;
  }

  public String getCardExpMonth() {
    return cardExpMonth;
  }

  public void setCardExpMonth(String cardExpMonth) {
    this.cardExpMonth = cardExpMonth;
  }

  public String getTransRefNumber() {
    return transRefNumber;
  }

  public void setTransRefNumber(String transRefNumber) {
    this.transRefNumber = transRefNumber;
  }

  public String getCustomerIP() {
    return customerIP;
  }

  public void setCustomerIP(String customerIP) {
    this.customerIP = customerIP;
  }

  public String getCardIDNumber() {
    return cardIDNumber;
  }

  public void setCardIDNumber(String cardIDNumber) {
    this.cardIDNumber = cardIDNumber;
  }

  public abstract ValidationErrors validate();

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("PSIOrder{");
    sb.append("storeID='").append(storeID).append('\'');
    sb.append(", passphrase='").append(passphrase).append('\'');
    sb.append(", paymentType='").append(paymentType).append('\'');
    sb.append(", testResult='").append(testResult).append('\'');
    sb.append(", orderID='").append(orderID).append('\'');
    sb.append(", userid='").append(userid).append('\'');
    sb.append(", baddress1='").append(baddress1).append('\'');
    sb.append(", tax1=").append(tax1);
    sb.append(", tax2=").append(tax2);
    sb.append(", tax3=").append(tax3);
    sb.append(", tax4=").append(tax4);
    sb.append(", tax5=").append(tax5);
    sb.append(", shippingTotal=").append(shippingTotal);
    sb.append(", subTotal=").append(subTotal);
    sb.append(", cardAction=").append(cardAction);
    sb.append(", cardHolderName='").append(cardHolderName).append('\'');
    sb.append(", cardNumber='").append(cardNumber).append('\'');
    sb.append(", cardExpYear='").append(cardExpYear).append('\'');
    sb.append(", cardExpMonth='").append(cardExpMonth).append('\'');
    sb.append(", transRefNumber='").append(transRefNumber).append('\'');
    sb.append(", customerIP='").append(customerIP).append('\'');
    sb.append(", cardIDNumber='").append(cardIDNumber).append('\'');
    sb.append(", items=").append(items);
    sb.append('}');
    return sb.toString();
  }

  //				protected CardXidprivate
//				protected CardECIprivate
//				protected CardCavvprivate
//				protected CardLevel2POprivate
//				protected CardLevel2Taxprivate
//				protected CardLevel2TaxExemptprivate
//				protected CardLevel2ShiptoZipprivate
//				protected AuthorizationNumberprivate
  //			protected CardRefNumberprivate

//  protected Item>\r\n";
//  strData = strData + "			<ItemID>apple</ItemID>\r\n";
//  strData = strData + "			<ItemDescriptionprivate
//  strData = strData + "			<ItemQty>2</ItemQty>\r\n";
//  strData = strData + "			<ItemPrice>1.5</ItemPrice>\r\n";
//  strData = strData + "	                  <Option>\r\n";
//  strData = strData + "	                      <Colour1>Red</Colour1>\r\n";
//  strData = strData + "	                      <Size1>11</Size1>\r\n";
//  strData = strData + "	                      <Maker1>PSiGate11</Maker1>\r\n";
//  strData = strData + "	                  </Option>\r\n";
//  strData = strData + "		</Item>\r\n";
//  strData = strData + "		<Item>\r\n";
//  strData = strData + "			<ItemID>book</ItemID>\r\n";
//  strData = strData + "			<ItemDescription>good book</ItemDescription>\r\n";
//  strData = strData + "			<ItemQty>3</ItemQty>\r\n";
//  strData = strData + "			<ItemPrice>2.5</ItemPrice>\r\n";
//  strData = strData + "	                  <Option>\r\n";
//  strData = strData + "	                      <Colour21>Red</Colour21>\r\n";
//  strData = strData + "	                      <Size21>21</Size21>\r\n";
//  strData = strData + "	                      <Maker21>PSiGate21</Maker21>\r\n";
//  strData = strData + "	                  </Option>\r\n";
//  strData = strData + "		</Item>\r\n";
//  strData = strData + "		<Item>\r\n";
//  strData = strData + "			<ItemID>computer</ItemID>\r\n";
//  strData = strData + "			<ItemDescription>IBM computer</ItemDescription>\r\n";
//  strData = strData + "			<ItemQty>1</ItemQty>\r\n";
//  strData = strData + "			<ItemPrice>12</ItemPrice>\r\n";
//  strData = strData + "	                  <Option>\r\n";
//  strData = strData + "	                      <Colour33>Red</Colour33>\r\n";
//  strData = strData + "	                      <Size33>33</Size33>\r\n";
//  strData = strData + "	                      <Maker33>PSiGate33</Maker33>\r\n";
//  strData = strData + "	                  </Option>\r\n";
//  strData = strData + "		</Item>\r\n";

}
