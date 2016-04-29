package com.straata;

import com.straata.psigate.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by krasaee on 2016-04-29.
 */
public class PSIPaymentProcessorTest {

  @Test
  public void testSaleOrder() {

    long randomOrderId = Double.valueOf(((Math.random() * 10000000000L) % 10000000000L)).longValue();

    PSIPaymentProcessor payment = new PSIPaymentProcessor(false, null, null);
    PSISaleOrder sales = new PSISaleOrder();
    sales.setOrderID(String.valueOf(randomOrderId));
    sales.setCardNumber("4111111111111111");
    sales.setCardExpYear("17");
    sales.setCardExpMonth("08");
    sales.setCardHolderName("Kas Smith");
    sales.setCardIDNumber("3422");
    sales.setSubTotal(new BigDecimal(345.00));
    sales.setTax1(Financial.mul(sales.getSubTotal(), BigDecimal.valueOf(0.13)));
    sales.setShippingTotal(new BigDecimal(7.00));


    try {
      OrderResponse response = payment.order(sales);

      Assert.assertNotNull(response);

    } catch (ValidationErrorsException e) {
      throw new RuntimeException(e);
    }
  }
}
