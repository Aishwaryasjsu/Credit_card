package com.cmpe202.aish.creditcard.chain;

import com.cmpe202.aish.creditcard.CreditCard;
import com.cmpe202.aish.creditcard.types.DiscoverCC;

public class DiscoverCCTypeChain implements CCTypeChain {
    private CCTypeChain nextInChain;

    public void setNext(CCTypeChain nextInChain) {
        this.nextInChain = nextInChain;
    }

    public CreditCard identifyType(CreditCard cc) {
        if (cc.isNumeric(cc.getCcNumber()) && cc.isValidCreditCardLength(cc.getCcNumber()) && cc.getCcNumber().matches("^6011[0-9]{12}$")) {
            return new DiscoverCC(cc.getCcNumber(), cc.getCcExpiry(), cc.getCcHolderName());
        }
        return null;
    }
}