package com.cmpe202.aish;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmpe202.aish.creditcard.CreditCard;

public class TestCreditCardClass {

    private CreditCard validCC;
    private CreditCard invalidCC;

    @Before
    public void InitCreditCards(){
        validCC = new CreditCard("525768549463275","8/17/2025","John Sammy");
        invalidCC = new CreditCard("4552205802502508577548868686868","8/17/2025","John John Sammy");
    }

    @Test
    public void TestValidCreditCardNumberLength(){
        boolean result = validCC.isValidCreditCardLength("5257685498463175");
        Assert.assertTrue(result);
    }

    @Test
    public void TestInvalidCreditCardNumberLength(){
        boolean result = invalidCC.isValidCreditCardLength("4552205802502508577548868686868");
        Assert.assertFalse(result);
    }

    @Test
    public void TestNumericCardNumber(){
        boolean result = validCC.isNumeric("5257685498463175");
        Assert.assertTrue(result);
    }

    @Test
    public void TestNonNumericCardNumber(){
        boolean result = validCC.isNumeric("52576AA498463175");
        Assert.assertFalse(result);
    }
}
