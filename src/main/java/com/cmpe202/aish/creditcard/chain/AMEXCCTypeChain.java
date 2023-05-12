package com.cmpe202.aish.creditcard.chain;

import com.cmpe202.aish.creditcard.CreditCard;
import com.cmpe202.aish.creditcard.types.AMEXCC;

public class AMEXCCTypeChain implements CCTypeChain {
    private CCTypeChain nextInChain;

    public void setNext(CCTypeChain nextInChain) {
        this.nextInChain = nextInChain;
    }

    public CreditCard identifyType(CreditCard cc) {
        if (cc.isNumeric(cc.getCcNumber()) && cc.isValidCreditCardLength(cc.getCcNumber()) && cc.getCcNumber().matches("^3[47][0-9]{13}$")){
            return new AMEXCC(cc.getCcNumber(), cc.getCcExpiry(), cc.getCcHolderName());
        }
        else{
            return nextInChain.identifyType(cc);
        }
    }
}
