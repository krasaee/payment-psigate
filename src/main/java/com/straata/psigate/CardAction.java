package com.straata.psigate;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * CardAction: Enum to represent the card action, sale, preauth, etc.
 *
 * @author Kasra Rasaee
 * @since 2016 APR 26
 * @see PSIOrder
 */
@XmlType
@XmlEnum(Integer.class)
public enum CardAction {
  @XmlEnumValue("0") Sale(0),
  @XmlEnumValue("1") PreAuth(1),
  @XmlEnumValue("2") PostAuth(2),
  @XmlEnumValue("3") Credit(3),
  @XmlEnumValue("4") ForcedPostAuth(4),
  @XmlEnumValue("6") ExtendedPostAuth(6),
  @XmlEnumValue("9") Void(9);

  public final int value;

  CardAction(final int value) {
    this.value = value;
  }

  public int getValue() {
    return this.value;
  }

}
