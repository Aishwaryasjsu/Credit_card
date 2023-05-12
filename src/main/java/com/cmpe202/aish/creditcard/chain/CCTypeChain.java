package com.cmpe202.aish.creditcard.chain;

import com.cmpe202.aish.creditcard.CreditCard;

public interface CCTypeChain {
    void setNext(CCTypeChain nextInChain);
    CreditCard identifyType(CreditCard creditCardNumber);
}
