package com.straata.psigate;

/**
 * PSISaleOrder: Simple Sales Order
 *
 * @author 2016-04-22.
 */
public class PSISaleOrder extends PSIOrder {

  public PSISaleOrder() {
    super();
    this.setCardAction(CardAction.Sale);
    this.setPaymentType("CC");
  }

  @Override
  public ValidationErrors validate() {

    ValidationErrors errors = new ValidationErrors();
    errors.valid(CardAction.Sale.equals(this.getCardAction()), "psigate.cardaction.invalid");
    errors.valid("CC".equals(this.getPaymentType()), "psigate.paymenttype.invalid");
    errors.validNotEmpty(this.getOrderID(), "psigate.orderid.invalid");
    errors.validNotEmpty(this.getCardHolderName(), "psigate.cardholdername.invalid");
    errors.validNotEmpty(this.getCardExpMonth(), "psigate.cardexpirymonth.invalid");
    errors.validNotEmpty(this.getCardExpYear(), "psigate.cardexpiryyear.invalid");
    errors.valid(Financial.greaterThan2(this.getSubTotal(), 0.0), "psigate.subtotal.invalid");

    if (errors.hasErrors()) {
      return errors;
    }

    return null;
  }
}
