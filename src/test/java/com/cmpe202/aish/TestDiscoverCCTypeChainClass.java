package com.cmpe202.aish;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmpe202.aish.creditcard.CreditCard;
import com.cmpe202.aish.creditcard.chain.CCTypeChain;
import com.cmpe202.aish.creditcard.chain.DiscoverCCTypeChain;
import com.cmpe202.aish.creditcard.types.AMEXCC;
import com.cmpe202.aish.creditcard.types.DiscoverCC;
import com.cmpe202.aish.creditcard.types.MasterCC;
import com.cmpe202.aish.creditcard.types.VisaCC;

public class TestDiscoverCCTypeChainClass {
    private CCTypeChain discoverTypeChain;
    private CreditCard discoverCC;

    @Before
    public void InitDiscoverCCTypeChain(){
        discoverTypeChain = new DiscoverCCTypeChain();
    }

    @Before
    public void InitDiscoverCreditCard(){
        discoverCC = new CreditCard("6011359235783918","05/23/2027","Linda Cruz");
    }

    @Test
    public void TestIdentifyDiscoverCCType(){
        CreditCard cc = discoverTypeChain.identifyType(discoverCC);
        Assert.assertTrue(cc instanceof DiscoverCC);
        Assert.assertFalse(cc instanceof VisaCC);
        Assert.assertFalse(cc instanceof MasterCC);
        Assert.assertFalse(cc instanceof AMEXCC);
    }
}
