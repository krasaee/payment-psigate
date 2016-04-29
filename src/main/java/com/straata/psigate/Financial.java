package com.straata.psigate;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Function;

/**
 * Financial: Utility for handling with BigDecimal and Double value precisions
 *
 * @author Kasra Rasaee
 */
public class Financial {


  /**
   * Checks whether amount is within two values
   *
   * @param amount
   * @param min
   * @param max
   * @return
   */
  public static boolean within2(BigDecimal amount, Double min, Double max) {
    BigDecimal minAmount = Financial.decimal2(min);
    BigDecimal maxAmount = Financial.decimal2(max);

    if (amount.compareTo(minAmount) >= 0 && amount.compareTo(maxAmount) <= 0) {
      return true;
    }

    return false;
  }

  /**
   * Whether amount is greater than value
   *
   * @param amount
   * @param value
   * @return
   */
  public static boolean greaterThan2(BigDecimal amount, Double value) {
    if (amount == null || value == null) {
      return false;
    }

    BigDecimal valueAmount = Financial.decimal2(value);
    return (amount.compareTo(valueAmount) > 0);
  }

  /**
   * Whether amount is greater than value
   *
   * @param amount
   * @param value
   * @return
   */
  public static boolean lessThan2(BigDecimal amount, Double value) {
    if (amount == null || value == null) {
      return false;
    }

    BigDecimal valueAmount = Financial.decimal2(value);
    return (amount.compareTo(valueAmount) < 0);
  }

  /**
  /**
   * Whether two double values are the same within a specific precision length, e.g. 2 decimal places
   *
   * @param d1
   * @param d2
   * @param precision
   * @return
   */
  public static boolean same(Double d1, Double d2, int precision) {
    return decimal(d1, precision).equals(decimal(d2, precision));
  }

  /**
   * Whether two BigDecimal values are the same within a specific precision length, e.g. 2 decimal places
   *
   * @param v1
   * @param v2
   * @param precision
   * @return
   */
  public static boolean same(BigDecimal v1, BigDecimal v2, int precision) {
    Double d1 = v1.doubleValue();
    Double d2 = v2.doubleValue();
    return same(d1, d2, precision);
  }

  /**
   * Whether two BigDecimal values are the same within 2 decimal places
   *
   * @param v1
   * @param v2
   * @return
   */
  public static boolean same2(BigDecimal v1, BigDecimal v2) {
    return same(v1, v2, 2);
  }

  /**
   * Whether two BigDecimal values are the same within 4 decimal places
   *
   * @param v1
   * @param v2
   * @return
   */
  public static boolean same4(BigDecimal v1, BigDecimal v2) {
    return same(v1, v2, 4);
  }

  /**
   * Convert a double value to 2 decimal precisions
   *
   * @param v
   * @return
   */
  public static BigDecimal decimal2(Double v) {
    return decimal(v, 2);
  }

  /**
   * Convert a double value to 4 decimal precisions
   *
   * @param v
   * @return
   */
  public static BigDecimal decimal4(Double v) {
    return decimal(v, 4);
  }

  /**
   * Convert a double value to any length of precision
   *
   * @param v
   * @param precision
   * @return
   */
  public static BigDecimal decimal(Double v, int precision) {
    MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
    return new BigDecimal(v, mc);
  }

  /**
   * Get math context rounded to two decimal precisions
   *
   * @return
   */
  public static MathContext mc2() {
    return new MathContext(2, RoundingMode.HALF_UP);
  }

  /**
   * Get math context rounded to four decimal precisions
   *
   * @return
   */
  public static MathContext mc4() {
    return new MathContext(4, RoundingMode.HALF_UP);
  }


  /**
   * Calculates the total value of a BigDecimal function within a list of items
   *
   * @param items
   * @param priceFunc
   * @param quantityFunc
   * @param <T>
   * @return BigDecimal within a 4 decimal precision
   */
  public static <T> BigDecimal calculate(List<T> items, Function<T, BigDecimal> priceFunc, Function<T, Integer> quantityFunc) {
    if (items == null) {
      return null;
    }

    BigDecimal total = new BigDecimal(0.0f, Financial.mc4());

    for (T item : items) {
      BigDecimal qty = new BigDecimal(quantityFunc.apply(item), mc4());
      BigDecimal price = priceFunc.apply(item);
      total = total.add(price.multiply(qty));
    }

    return total;
  }

  /**
   * Add two decimal numbers together, with a precision of 4 decimal points
   *
   * @param x
   * @param y
   * @return
   */
  public static BigDecimal add(double x, double y) {
    return new BigDecimal(x + y, Financial.mc4());
  }

  /**
   * Add two decimal values
   *
   * @param x
   * @param y
   * @return
   */
  public static BigDecimal add(BigDecimal x, BigDecimal y) {
    return add(x.doubleValue(), y.doubleValue());
  }

  /**
   * Add a double value to a decimal and return a BigDecimal instance
   *
   * @param x
   * @param y
   * @return
   */
  public static BigDecimal add(BigDecimal x, double y) {
    return add(x.doubleValue(), y);
  }

  /**
   * Multiply two decimal numbers together, with a precision of 4 decimal points
   *
   * @param x
   * @param y
   * @return
   */
  public static BigDecimal mul(double x, double y) {
    return new BigDecimal(x * y, Financial.mc4());
  }

  /**
   * Multiple two BigDecimal numbers together
   *
   * @param x
   * @param y
   * @return
   */
  public static BigDecimal mul(BigDecimal x, BigDecimal y) {
    return mul(x.doubleValue(), y.doubleValue());
  }

  /**
   * Multiple a double value and a BigDecimal number and return a BigDecimal instance
   *
   * @param x
   * @param y
   * @return
   */
  public static BigDecimal mul(BigDecimal x, double y) {
    return mul(x.doubleValue(), y);
  }

  /**
   * Divide two decimal numbers together, with a precision of 4 decimal points
   *
   * @param x
   * @param y
   * @return
   */
  public static BigDecimal div(BigDecimal x, BigDecimal y) {
    return new BigDecimal(x.doubleValue() / y.doubleValue(),
            Financial.mc4());
  }
}
