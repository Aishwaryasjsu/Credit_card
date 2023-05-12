package com.cmpe202.aish;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmpe202.aish.creditcard.CreditCard;
import com.cmpe202.aish.creditcard.chain.*;
import com.cmpe202.aish.creditcard.factory.ConcreteCreditCardFactory;
import com.cmpe202.aish.creditcard.types.AMEXCC;
import com.cmpe202.aish.creditcard.types.DiscoverCC;
import com.cmpe202.aish.creditcard.types.MasterCC;
import com.cmpe202.aish.creditcard.types.VisaCC;

public class TestConcreteCreditCardFactoryClass {
    private CreditCard visaCC;
    private CreditCard masterCC;
    private CreditCard discoverCC;
    private CreditCard amexCC;
    private CreditCard invalidCC;

    private CCTypeChain visaTypeChain;
    private CCTypeChain amexTypeChain;
    private CCTypeChain masterTypeChain;
    private CCTypeChain discoverTypeChain;

    // Credit Cards with different lengths
    // Visa
    private CreditCard visaCCLength12;
    private CreditCard visaCCLength14;
    private CreditCard visaCCLength15;
    private CreditCard visaCCLength17;
    private CreditCard visaCCLength16;
    private CreditCard visaCCLength13;

    // Master
    private CreditCard masterCCLength15;
    private CreditCard masterCCLength17;
    private CreditCard masterCCLength16;

    // Discover
    private CreditCard discoverCCLength15;
    private CreditCard discoverCCLength17;
    private CreditCard discoverCCLength16;

    // AMEX
    private CreditCard amexCCLength14;
    private CreditCard amexCCLength16;
    private CreditCard amexCCLength15;

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
    public void InitCreditCards(){
        visaCC = new CreditCard("4533989824439445","3/20/2031","John Sami");
        masterCC = new CreditCard("5257685498463175","8/17/2024","John Frogg");
        discoverCC = new CreditCard("6011359235783999","04/22/2025","Linda Cruz");
        amexCC = new CreditCard("348752205802502","12/02/2031","Brendon Woods");

        invalidCC = new CreditCard("4552205802502508577548868686868","8/17/2025","John Dalton");
    }

    @Before
    public void InitCreditCardsWithDifferentLengths(){
        // Visa
        visaCCLength12 = new CreditCard("453398982443", "3/20/2030","Adam Sandler");
        visaCCLength14 = new CreditCard("45339898244394", "3/20/2030","Adam Sandler");
        visaCCLength15 = new CreditCard("453398982443944", "3/20/2030","Adam Sandler");
        visaCCLength17 = new CreditCard("45339898244394456", "3/20/2030","Adam Sandler");
        visaCCLength16 = new CreditCard("4533989824439445", "3/20/2030","Adam Sandler");
        visaCCLength13 = new CreditCard("4533989824439", "3/20/2030","Adam Sandler");

        // Master
        masterCCLength15 = new CreditCard("525768549846317", "3/20/2030","Adam Sandler");
        masterCCLength17 = new CreditCard("52576854984631758", "3/20/2030","Adam Sandler");
        masterCCLength16 = new CreditCard("5257685498463175", "3/20/2030","Adam Sandler");

        // Discover
        discoverCCLength15 = new CreditCard("601135923578391", "3/20/2030","Adam Sandler");
        discoverCCLength17 = new CreditCard("60113592357839182", "3/20/2030","Adam Sandler");
        discoverCCLength16 = new CreditCard("6011359235783918", "3/20/2030","Adam Sandler");

        // AMEX
        amexCCLength14 = new CreditCard("34875220580250", "3/20/2030","Adam Sandler");
        amexCCLength16 = new CreditCard("3487522058025025", "3/20/2030","Adam Sandler");
        amexCCLength15 = new CreditCard("348752205802502", "3/20/2030","Adam Sandler");
    }

    @Test
    public void TestConcreteCreditCardFactoryObjectCreation(){
        ConcreteCreditCardFactory factory = new ConcreteCreditCardFactory();

        Object cc = factory.create(visaCC);

        Assert.assertTrue(cc instanceof VisaCC);

        cc = factory.create(masterCC);
        Assert.assertTrue(cc instanceof MasterCC);

        cc = factory.create(discoverCC);
        Assert.assertTrue(cc instanceof DiscoverCC);

        cc = factory.create(amexCC);
        Assert.assertTrue(cc instanceof AMEXCC);

        cc = factory.create(invalidCC);
        Assert.assertNull(cc);
    }

    @Test
    public void TestMasterCCLength(){
        CreditCard cc = masterTypeChain.identifyType(masterCCLength15);
        Assert.assertNull(cc);

        cc = masterTypeChain.identifyType(masterCCLength17);
        Assert.assertNull(cc);

        cc = masterTypeChain.identifyType(masterCCLength16);
        Assert.assertTrue(cc instanceof MasterCC);

    }

    @Test
    public void TestVisaCCLength(){
        CreditCard cc = visaTypeChain.identifyType(visaCCLength12);
        Assert.assertNull(cc);

        cc = visaTypeChain.identifyType(visaCCLength14);
        Assert.assertNull(cc);

        cc = visaTypeChain.identifyType(visaCCLength15);
        Assert.assertNull(cc);

        cc = visaTypeChain.identifyType(visaCCLength17);
        Assert.assertNull(cc);

        cc = visaTypeChain.identifyType(visaCCLength13);
        Assert.assertTrue(cc instanceof VisaCC);

        cc = visaTypeChain.identifyType(visaCCLength16);
        Assert.assertTrue(cc instanceof VisaCC);
    }

    @Test
    public void TestDiscoverCCLength(){
        CreditCard cc = discoverTypeChain.identifyType(discoverCCLength15);
        Assert.assertNull(cc);

        cc = discoverTypeChain.identifyType(discoverCCLength17);
        Assert.assertNull(cc);

        cc = discoverTypeChain.identifyType(discoverCCLength16);
        Assert.assertTrue(cc instanceof DiscoverCC);
    }

    @Test
    public void TestAMEXCCLength(){
        CreditCard cc = amexTypeChain.identifyType(amexCCLength14);
        Assert.assertNull(cc);

        cc = amexTypeChain.identifyType(amexCCLength16);
        Assert.assertNull(cc);

        cc = amexTypeChain.identifyType(amexCCLength15);
        Assert.assertTrue(cc instanceof AMEXCC);
    }
}
