package com.straata.psigate;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kasra Rasaee
 * @since 2016 APR 26
 */
public class PSIPaymentConnectionDetails {

  private String storeID;

  private String passphrase;

  private String transactionManagerURL;

  private String accountManagerURL;

  public PSIPaymentConnectionDetails(String storeID, String passphrase, String transactionManagerURL, String accountManagerURL) {
    this.storeID = storeID;
    this.passphrase = passphrase;
    this.transactionManagerURL = transactionManagerURL;
    this.accountManagerURL = accountManagerURL;
  }

  public String getStoreID() {
    return storeID;
  }

  public void setStoreID(String storeID) {
    this.storeID = storeID;
  }

  public String getPassphrase() {
    return passphrase;
  }

  public void setPassphrase(String passphrase) {
    this.passphrase = passphrase;
  }

  public String getTransactionManagerURL() {
    return transactionManagerURL;
  }

  public void setTransactionManagerURL(String transactionManagerURL) {
    this.transactionManagerURL = transactionManagerURL;
  }

  public String getAccountManagerURL() {
    return accountManagerURL;
  }

  public void setAccountManagerURL(String accountManagerURL) {
    this.accountManagerURL = accountManagerURL;
  }

}
