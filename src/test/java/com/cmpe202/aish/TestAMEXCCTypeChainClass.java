package com.cmpe202.aish;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmpe202.aish.creditcard.CreditCard;
import com.cmpe202.aish.creditcard.chain.AMEXCCTypeChain;
import com.cmpe202.aish.creditcard.chain.CCTypeChain;
import com.cmpe202.aish.creditcard.types.AMEXCC;
import com.cmpe202.aish.creditcard.types.DiscoverCC;
import com.cmpe202.aish.creditcard.types.MasterCC;
import com.cmpe202.aish.creditcard.types.VisaCC;

public class TestAMEXCCTypeChainClass {
    private CCTypeChain amexTypeChain;
    private CreditCard amexCC;

    @Before
    public void InitAMEXCCTypeChain(){
        amexTypeChain = new AMEXCCTypeChain();
    }

    @Before
    public void InitAMEXCreditCard(){
        amexCC = new CreditCard("348752205802502","12/02/2041","Aishwarya lodhi");
    }

    @Test
    public void TestIdentifyAMEXCCType(){
        CreditCard cc = amexTypeChain.identifyType(amexCC);
        Assert.assertTrue(cc instanceof AMEXCC);
        Assert.assertFalse(cc instanceof VisaCC);
        Assert.assertFalse(cc instanceof MasterCC);
        Assert.assertFalse(cc instanceof DiscoverCC);
    }
}
