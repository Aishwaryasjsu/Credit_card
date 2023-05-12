package com.cmpe202.aish;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmpe202.aish.creditcard.CreditCard;
import com.cmpe202.aish.creditcard.chain.CCTypeChain;
import com.cmpe202.aish.creditcard.chain.MasterCCTypeChain;
import com.cmpe202.aish.creditcard.types.AMEXCC;
import com.cmpe202.aish.creditcard.types.DiscoverCC;
import com.cmpe202.aish.creditcard.types.MasterCC;
import com.cmpe202.aish.creditcard.types.VisaCC;

public class TestMasterCCTypeChainClass {
    private CCTypeChain masterTypeChain;
    private CreditCard masterCC;

    @Before
    public void InitMasterCCTypeChain(){
        masterTypeChain = new MasterCCTypeChain();
    }

    @Before
    public void InitMasterCreditCard(){
        masterCC = new CreditCard("5257685498463175","8/17/2025","John Sammy");
    }

    @Test
    public void TestIdentifyMasterCCType(){
        CreditCard cc = masterTypeChain.identifyType(masterCC);
        Assert.assertTrue(cc instanceof MasterCC);
        Assert.assertFalse(cc instanceof VisaCC);
        Assert.assertFalse(cc instanceof DiscoverCC);
        Assert.assertFalse(cc instanceof AMEXCC);
    }
}
