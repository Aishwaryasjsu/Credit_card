package com.cmpe202.aish.creditcard.factory;

import com.cmpe202.aish.creditcard.CreditCard;
import com.cmpe202.aish.creditcard.chain.*;

public class ConcreteCreditCardFactory implements CreditCardFactory {
    CCTypeChain chain;

    public ConcreteCreditCardFactory(){
        chain = new VisaCCTypeChain(); // Chain starts with Visa type
        CCTypeChain amexTypeChain = new AMEXCCTypeChain();
        CCTypeChain masterTypeChain = new MasterCCTypeChain();
        CCTypeChain discoverTypeChain = new DiscoverCCTypeChain();
        chain.setNext(amexTypeChain);
        amexTypeChain.setNext(masterTypeChain);
        masterTypeChain.setNext(discoverTypeChain);
    }

    public CreditCard create(CreditCard cc) {
        //Calling chain of responsibility
        return chain.identifyType(cc);
    }
}
