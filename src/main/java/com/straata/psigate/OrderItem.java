package com.straata.psigate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

/**
 * Created by krasaee on 2016-04-21.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "Item")
public class OrderItem {

  @XmlElement(name = "ItemID")
  private String ItemID;

  @XmlElement(name = "ItemDescription")
  private String ItemDescription;

  @XmlElement(name = "ItemQty")
  private Integer ItemQty;

  @XmlElement(name = "ItemPrice")
  private BigDecimal ItemPrice;


//  private OrderItemOption Option;

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
}
