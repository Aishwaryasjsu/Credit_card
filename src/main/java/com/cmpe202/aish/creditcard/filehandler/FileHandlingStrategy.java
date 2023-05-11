package com.cmpe202.aish.creditcard.filehandler;

import java.io.IOException;
import java.util.List;

import com.cmpe202.aish.creditcard.CreditCard;

public interface FileHandlingStrategy {
    List<CreditCard> parse() throws IOException;
    void write(List<CreditCard> creditCardList) throws IOException;
}
