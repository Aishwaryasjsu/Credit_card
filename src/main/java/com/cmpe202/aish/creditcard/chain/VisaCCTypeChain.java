package com.cmpe202.aish.creditcard.chain;

import com.cmpe202.aish.creditcard.CreditCard;
import com.cmpe202.aish.creditcard.types.VisaCC;

public class VisaCCTypeChain implements CCTypeChain {
    private CCTypeChain nextInChain;

    public void setNext(CCTypeChain nextInChain) {
        this.nextInChain = nextInChain;
    }

    public CreditCard identifyType(CreditCard cc) {
        if (cc.isNumeric(cc.getCcNumber()) && cc.isValidCreditCardLength(cc.getCcNumber()) && cc.getCcNumber().matches("^4[0-9]{12}(?:[0-9]{3})?$")) {
            return new VisaCC(cc.getCcNumber(), cc.getCcExpiry(), cc.getCcHolderName());
        } else {
            return nextInChain.identifyType(cc);
        }
    }
}
