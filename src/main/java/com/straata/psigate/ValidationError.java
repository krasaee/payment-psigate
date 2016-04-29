package com.straata.psigate;

/**
 * ValidationError: pojo for holding a specific error with error code.
 *
 * @author Kasra Rasaee
 * @since 2016-04-29
 */
public class ValidationError {

  private String errorKey;
  private String errorValue;

  public ValidationError() {
    super();
  }

  public ValidationError(String errorKey) {
    this.errorKey = errorKey;
  }

  public ValidationError(String errorKey, String errorValue) {
    this.errorKey = errorKey;
    this.errorValue = errorValue;
  }

  public String getErrorKey() {
    return errorKey;
  }

  public void setErrorKey(String errorKey) {
    this.errorKey = errorKey;
  }

  public String getErrorValue() {
    return errorValue;
  }

  public void setErrorValue(String errorValue) {
    this.errorValue = errorValue;
  }
}