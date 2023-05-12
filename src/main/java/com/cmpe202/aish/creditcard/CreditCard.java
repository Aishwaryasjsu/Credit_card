package com.cmpe202.aish.creditcard;

public class CreditCard {
    String ccNumber;
    String ccExpiry;
    String ccHolderName;
    CCType ccType;

    public String getCcNumber() {
        return ccNumber;
    }
    public String getCcExpiry() {
        return ccExpiry;
    }
    public String getCcHolderName() {
        return ccHolderName;
    }

    public CreditCard(String number, String ccExpiry, String ccHolderName) {
        this.ccNumber = number;
        this.ccExpiry = ccExpiry;
        this.ccHolderName = ccHolderName;
    }

    public boolean isValidCreditCardLength(String number){
        if (number.length() >= 19){
            return false;
        }
        return true;
    }

    public boolean isNumeric(String number){
        try{
            Long.parseLong(number);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public void setType(CCType ccType){
        this.ccType = ccType;
    }

    public CCType getType(){
        return ccType;
    }
}
