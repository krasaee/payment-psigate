package com.straata.psigate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by krasaee on 2016-04-26.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Result")
public class OrderResponse {

  @XmlElement(name = "TransTime", required = true)
  public String transTime;

  @XmlElement(name = "OrderID", required = true)
  private String orderID;

  @XmlElement(name = "Approved", required = true)
  private String approved;

  @XmlElement(name = "ReturnCode", required = true)
  private String returnCode;

  @XmlElement(name = "ErrMsg", required = true)
  private String errMsg;

  @XmlElement(name = "TaxTotal", required = true)
  private String taxTotal;

  @XmlElement(name = "ShipTotal", required = true)
  private String shipTotal;

  @XmlElement(name = "SubTotal", required = true)
  private String subTotal;

  @XmlElement(name = "FullTotal", required = true)
  private String fullTotal;

  @XmlElement(name = "PaymentType", required = true)
  private String paymentType;

  @XmlElement(name = "CardNumber", required = true)
  private String cardNumber;

  @XmlElement(name = "CardExpMonth", required = true)
  private String cardExpMonth;

  @XmlElement(name = "CardExpYear", required = true)
  private String cardExpYear;

  @XmlElement(name = "TransRefNumber", required = true)
  private String transRefNumber;

  @XmlElement(name = "CardIDResult", required = true)
  private String cardIDResult;

  @XmlElement(name = "AVSResult", required = true)
  private String avsResult;

  @XmlElement(name = "CardAuthNumber", required = true)
  private String cardAuthNumber;

  @XmlElement(name = "CardRefNumber", required = true)
  private String cardRefNumber;

  @XmlElement(name = "CardType", required = true)
  private String cardType;

  @XmlElement(name = "IPResult", required = true)
  private String ipResult;

  @XmlElement(name = "IPCountry", required = true)
  private String ipCountry;

  @XmlElement(name = "IPRegion", required = true)
  private String ipRegion;

  @XmlElement(name = "IPCity", required = true)
  private String ipCity;

  public String getTransTime() {
    return transTime;
  }

  public void setTransTime(String transTime) {
    this.transTime = transTime;
  }

  public String getOrderID() {
    return orderID;
  }

  public void setOrderID(String orderID) {
    this.orderID = orderID;
  }

  public String getApproved() {
    return approved;
  }

  public void setApproved(String approved) {
    this.approved = approved;
  }

  public String getReturnCode() {
    return returnCode;
  }

  public void setReturnCode(String returnCode) {
    this.returnCode = returnCode;
  }

  public String getErrMsg() {
    return errMsg;
  }

  public void setErrMsg(String errMsg) {
    this.errMsg = errMsg;
  }

  public String getTaxTotal() {
    return taxTotal;
  }

  public void setTaxTotal(String taxTotal) {
    this.taxTotal = taxTotal;
  }

  public String getShipTotal() {
    return shipTotal;
  }

  public void setShipTotal(String shipTotal) {
    this.shipTotal = shipTotal;
  }

  public String getSubTotal() {
    return subTotal;
  }

  public void setSubTotal(String subTotal) {
    this.subTotal = subTotal;
  }

  public String getFullTotal() {
    return fullTotal;
  }

  public void setFullTotal(String fullTotal) {
    this.fullTotal = fullTotal;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getCardExpMonth() {
    return cardExpMonth;
  }

  public void setCardExpMonth(String cardExpMonth) {
    this.cardExpMonth = cardExpMonth;
  }

  public String getCardExpYear() {
    return cardExpYear;
  }

  public void setCardExpYear(String cardExpYear) {
    this.cardExpYear = cardExpYear;
  }

  public String getTransRefNumber() {
    return transRefNumber;
  }

  public void setTransRefNumber(String transRefNumber) {
    this.transRefNumber = transRefNumber;
  }

  public String getCardIDResult() {
    return cardIDResult;
  }

  public void setCardIDResult(String cardIDResult) {
    this.cardIDResult = cardIDResult;
  }

  public String getAvsResult() {
    return avsResult;
  }

  public void setAvsResult(String avsResult) {
    this.avsResult = avsResult;
  }

  public String getCardAuthNumber() {
    return cardAuthNumber;
  }

  public void setCardAuthNumber(String cardAuthNumber) {
    this.cardAuthNumber = cardAuthNumber;
  }

  public String getCardRefNumber() {
    return cardRefNumber;
  }

  public void setCardRefNumber(String cardRefNumber) {
    this.cardRefNumber = cardRefNumber;
  }

  public String getCardType() {
    return cardType;
  }

  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  public String getIpResult() {
    return ipResult;
  }

  public void setIpResult(String ipResult) {
    this.ipResult = ipResult;
  }

  public String getIpCountry() {
    return ipCountry;
  }

  public void setIpCountry(String ipCountry) {
    this.ipCountry = ipCountry;
  }

  public String getIpRegion() {
    return ipRegion;
  }

  public void setIpRegion(String ipRegion) {
    this.ipRegion = ipRegion;
  }

  public String getIpCity() {
    return ipCity;
  }

  public void setIpCity(String ipCity) {
    this.ipCity = ipCity;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("OrderResponse{");
    sb.append("transTime='").append(transTime).append('\'');
    sb.append(", orderID='").append(orderID).append('\'');
    sb.append(", approved='").append(approved).append('\'');
    sb.append(", returnCode='").append(returnCode).append('\'');
    sb.append(", errMsg='").append(errMsg).append('\'');
    sb.append(", taxTotal='").append(taxTotal).append('\'');
    sb.append(", shipTotal='").append(shipTotal).append('\'');
    sb.append(", subTotal='").append(subTotal).append('\'');
    sb.append(", fullTotal='").append(fullTotal).append('\'');
    sb.append(", paymentType='").append(paymentType).append('\'');
    sb.append(", cardNumber='").append(cardNumber).append('\'');
    sb.append(", cardExpMonth='").append(cardExpMonth).append('\'');
    sb.append(", cardExpYear='").append(cardExpYear).append('\'');
    sb.append(", transRefNumber='").append(transRefNumber).append('\'');
    sb.append(", cardIDResult='").append(cardIDResult).append('\'');
    sb.append(", avsResult='").append(avsResult).append('\'');
    sb.append(", cardAuthNumber='").append(cardAuthNumber).append('\'');
    sb.append(", cardRefNumber='").append(cardRefNumber).append('\'');
    sb.append(", cardType='").append(cardType).append('\'');
    sb.append(", ipResult='").append(ipResult).append('\'');
    sb.append(", ipCountry='").append(ipCountry).append('\'');
    sb.append(", ipRegion='").append(ipRegion).append('\'');
    sb.append(", ipCity='").append(ipCity).append('\'');
    sb.append('}');
    return sb.toString();
  }
/*
<?xml version="1.0" encoding="UTF-8"?>
<Result>
  <TransTime>Mon Nov 08 20:21:06 PST 2004</TransTime>
  <OrderID>2004110820210605147</OrderID>
  <Approved>APPROVED</Approved>
  <ReturnCode>Y:TEST:TESTTRANS:M:X:YYY</ReturnCode>
  <ErrMsg></ErrMsg>
  <TaxTotal>5.00</TaxTotal>
  <ShipTotal>15.00</ShipTotal>
  <SubTotal>55.00</SubTotal>
  <FullTotal>75.00</FullTotal>
  <PaymentType>CC</PaymentType>
  <CardNumber>411111...1111</CardNumber>
  <CardExpMonth>05</CardExpMonth>
  <CardExpYear>07</CardExpYear>
  <TransRefNumber>1bd0082c392b7c5b</TransRefNumber>
  <CardIDResult>M</CardIDResult>
  <AVSResult>X</AVSResult>
  <CardAuthNumber>TEST</CardAuthNumber>
  <CardRefNumber>TESTTRANS</CardRefNumber>
  <CardType>VISA</CardType>
  <IPResult>YYY</IPResult>
  <IPCountry>CA</IPCountry>
  <IPRegion>Ontario</IPRegion>
  <IPCity>Toronto</IPCity>
</Result>
   */
}
