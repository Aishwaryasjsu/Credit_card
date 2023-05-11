package com.cmpe202.aish.creditcard.types;

import com.cmpe202.aish.creditcard.CCType;
import com.cmpe202.aish.creditcard.CreditCard;

public class AMEXCC extends CreditCard {

    public AMEXCC(String number, String expiry, String cardHolderName) {
        super(number, expiry, cardHolderName);
    }

    public CCType getType(){
        return CCType.AmericanExpress;
    }
}
