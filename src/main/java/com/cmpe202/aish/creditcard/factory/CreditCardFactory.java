package com.cmpe202.aish.creditcard.factory;

import com.cmpe202.aish.creditcard.CreditCard;

public interface CreditCardFactory {
    CreditCard create(CreditCard creditCardNumber);
}
