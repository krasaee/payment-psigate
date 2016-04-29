# payment-psigate
PSiGate Payment Processor Java Library

## download source
pull repository to your local machine

## compile and install jar to your local maven repo
mvn clean install -DskipTests

## use library in your application using maven, gradle or what have you - as long as its part of the class path.

Example (test case can be found under src/test/java/com/straata/PSIPaymentProcessorTest)

```java
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
  ```
