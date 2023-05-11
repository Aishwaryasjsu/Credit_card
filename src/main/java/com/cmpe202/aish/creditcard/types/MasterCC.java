package com.cmpe202.aish.creditcard.types;

import com.cmpe202.aish.creditcard.CCType;
import com.cmpe202.aish.creditcard.CreditCard;

public class MasterCC extends CreditCard {
    public MasterCC(String number, String expiry, String cardHolderName) {
        super(number, expiry, cardHolderName);
    }

    public CCType getType(){
        return CCType.MasterCard;
    }
}