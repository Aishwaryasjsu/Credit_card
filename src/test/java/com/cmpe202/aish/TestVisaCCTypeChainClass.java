package com.cmpe202.aish;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmpe202.aish.creditcard.CreditCard;
import com.cmpe202.aish.creditcard.chain.CCTypeChain;
import com.cmpe202.aish.creditcard.chain.VisaCCTypeChain;
import com.cmpe202.aish.creditcard.types.AMEXCC;
import com.cmpe202.aish.creditcard.types.DiscoverCC;
import com.cmpe202.aish.creditcard.types.MasterCC;
import com.cmpe202.aish.creditcard.types.VisaCC;

public class TestVisaCCTypeChainClass {
    private CCTypeChain visaTypeChain;
    private CreditCard visaCC;

    @Before
    public void InitVisaCCTypeChain(){
        visaTypeChain = new VisaCCTypeChain();
    }

    @Before
    public void InitVisaCreditCard(){
        visaCC = new CreditCard("4533989824439445","3/20/2030","Aishwarya Lodhi");
    }

    @Test
    public void TestIdentifyVisaCCType(){
        CreditCard cc = visaTypeChain.identifyType(visaCC);
        Assert.assertTrue(cc instanceof VisaCC);
        Assert.assertFalse(cc instanceof MasterCC);
        Assert.assertFalse(cc instanceof DiscoverCC);
        Assert.assertFalse(cc instanceof AMEXCC);
    }
}
