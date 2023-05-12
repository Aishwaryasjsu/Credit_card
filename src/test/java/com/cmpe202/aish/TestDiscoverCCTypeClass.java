package com.cmpe202.aish;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmpe202.aish.creditcard.CCType;
import com.cmpe202.aish.creditcard.CreditCard;
import com.cmpe202.aish.creditcard.types.DiscoverCC;

public class TestDiscoverCCTypeClass {
    private CreditCard cc;

    @Before
    public void InitDiscoverCreditCard(){
        cc = new DiscoverCC("6011359235783918","05/23/2027","Linda Cruz");
    }

    @Test
    public void TestCardType(){
        CCType result = cc.getType();
        Assert.assertEquals(result, CCType.Discover);
    }
}
