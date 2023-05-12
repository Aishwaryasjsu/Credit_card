package com.cmpe202.aish;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmpe202.aish.creditcard.CreditCard;
import com.cmpe202.aish.creditcard.chain.*;
import com.cmpe202.aish.creditcard.types.AMEXCC;
import com.cmpe202.aish.creditcard.types.DiscoverCC;
import com.cmpe202.aish.creditcard.types.MasterCC;
import com.cmpe202.aish.creditcard.types.VisaCC;

public class TestInvalidCCTypeChainClass {

    private CreditCard invalidCC;

    private CCTypeChain visaTypeChain;
    private CCTypeChain amexTypeChain;
    private CCTypeChain masterTypeChain;
    private CCTypeChain discoverTypeChain;


    @Before
    public void InitCreditCardChain(){
        // Set Credit Card type identification chain
        visaTypeChain = new VisaCCTypeChain();
        amexTypeChain = new AMEXCCTypeChain();
        masterTypeChain = new MasterCCTypeChain();
        discoverTypeChain = new DiscoverCCTypeChain();
        visaTypeChain.setNext(amexTypeChain);
        amexTypeChain.setNext(masterTypeChain);
        masterTypeChain.setNext(discoverTypeChain);
    }

    @Before
    public void InitInvalidCreditCard(){
        invalidCC = new CreditCard("4552205802502508577548868686868","8/17/2025","John Sammy");
    }

    @Test
    public void TestIdentifyInvalidCCType(){
        CreditCard cc = visaTypeChain.identifyType(invalidCC);
        Assert.assertEquals(null, cc);
        Assert.assertFalse(cc instanceof VisaCC);
        Assert.assertFalse(cc instanceof MasterCC);
        Assert.assertFalse(cc instanceof DiscoverCC);
        Assert.assertFalse(cc instanceof AMEXCC);
    }
}
