package com.cmpe202.aish.creditcard.chain;

import com.cmpe202.aish.creditcard.CreditCard;
import com.cmpe202.aish.creditcard.types.MasterCC;

public class MasterCCTypeChain implements CCTypeChain {
    private CCTypeChain nextInChain;

    public void setNext(CCTypeChain nextInChain) {
        this.nextInChain = nextInChain;
    }

    public CreditCard identifyType(CreditCard cc) {
        if (cc.isNumeric(cc.getCcNumber()) && cc.isValidCreditCardLength(cc.getCcNumber()) && cc.getCcNumber().matches("^5[1-5][0-9]{14}$")){
            return new MasterCC(cc.getCcNumber(), cc.getCcExpiry(), cc.getCcHolderName());
        }
        else{
            return nextInChain.identifyType(cc);
        }
    }
}
