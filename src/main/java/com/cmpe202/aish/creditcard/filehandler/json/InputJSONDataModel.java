package com.cmpe202.aish.creditcard.filehandler.json;

public class InputJSONDataModel {
    String cardNumber = "";
    String expirationDate = "";
    String cardHolderName = "";

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
    	this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
    	this.expirationDate = expirationDate;
    }

    public String getNameOfCardholder() {
        return cardHolderName;
    }

    public void setNameOfCardholder(String nameOfCardholder) {
    	this.cardHolderName = nameOfCardholder;
    }
}
