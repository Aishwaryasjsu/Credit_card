package com.cmpe202.aish;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmpe202.aish.creditcard.CCType;
import com.cmpe202.aish.creditcard.CreditCard;
import com.cmpe202.aish.creditcard.types.VisaCC;

public class TestVisaCCTypeClass {
    private CreditCard cc;

    @Before
    public void InitVisaCreditCard(){
        cc = new VisaCC("4533989824439445","3/20/2030","Aishwarya Lodhi");
    }

    @Test
    public void TestCardType(){
        CCType result = cc.getType();
        Assert.assertEquals(result, CCType.Visa);
    }
}
