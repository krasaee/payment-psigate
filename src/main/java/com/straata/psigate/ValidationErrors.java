package com.straata.psigate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krasaee on 2016-04-29.
 */
public class ValidationErrors {

  protected List<ValidationError> errors;

  public ValidationErrors(){
    super();
  }

  public ValidationErrors(ValidationError...errors) {
    for (ValidationError error : errors) {
      this.add(error);
    }
  }

  public void add(ValidationError error) {
    if (this.errors == null) {
      this.errors =  new ArrayList<>();
    }

    this.errors.add(error);
  }

  public void validNotEmpty(String value, String errorKey) {
    this.validNotEmpty(value, errorKey, null);
  }

  public void validNotEmpty(String value, String errorKey, String errorValue) {
    this.valid((value != null && value.length() > 0),
            errorKey, errorValue);
  }

  /**
   * Add new instance of ValidationError if check == false
   * @param check
   * @param errorKey
   */
  public void valid(boolean check, String errorKey) {
    this.valid(check, errorKey, null);
  }

  /**
   * Add new instance of ValidationError if check == false
   * @param check
   * @param errorKey
   * @param errorValue
   */
  public void valid(boolean check, String errorKey, String errorValue) {
    if (!check) {
      this.add(new ValidationError(errorKey, errorValue));
    }
  }

  public boolean hasErrors() {
    return this.errors != null && this.errors.size() > 0;
  }

  public List<ValidationError> getErrors() {
    return errors;
  }

  public void setErrors(List<ValidationError> errors) {
    this.errors = errors;
  }


}

