package com.cmpe202.aish;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmpe202.aish.creditcard.CCType;
import com.cmpe202.aish.creditcard.CreditCard;
import com.cmpe202.aish.creditcard.types.AMEXCC;

public class TestAMEXCCTypeClass {
    private CreditCard cc;

    @Before
    public void InitAMEXCreditCard(){
        cc = new AMEXCC("348752205802502","12/02/2041","Aishwarya Lodhi");
    }

    @Test
    public void TestCardType(){
        CCType result = cc.getType();
        Assert.assertEquals(result, CCType.AmericanExpress);
    }
}
