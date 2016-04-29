package com.straata.psigate;

/**
 * Created by krasaee on 2016-04-29.
 */
public class ValidationErrorsException extends Exception {
  protected ValidationErrors errors;

  public ValidationErrorsException(ValidationErrors errors) {
    this.errors = errors;
  }

  public ValidationErrors getErrors() {
    return errors;
  }
}
