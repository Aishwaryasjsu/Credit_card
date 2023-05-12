package com.cmpe202.aish;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmpe202.aish.creditcard.CCType;
import com.cmpe202.aish.creditcard.CreditCard;
import com.cmpe202.aish.creditcard.types.MasterCC;

public class TestMasterCCTypeClass {
    private CreditCard cc;

    @Before
    public void InitMasterCreditCard(){
        cc = new MasterCC("5257685498463175","8/17/2025","John Sammy");
    }

    @Test
    public void TestCardType(){
        CCType result = cc.getType();
        Assert.assertEquals(result, CCType.MasterCard);
    }
}
