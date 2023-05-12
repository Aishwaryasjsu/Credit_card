package com.cmpe202.aish.creditcard;

public class Constants {
    public static final String ERROR_ARGUMENTS_COUNT="Input and Output files must be provided as command line arguments to this program. Please re-run the program with Input and Output files. Exiting.";
    public static final String ERROR_INPUT_OUTPUT_FILE_TYPES_NOT_SAME="Input and Output files types must be same. Please re-run the program with same Input and Output file types. Exiting.";
    public static final String ERROR_INPUT_FILE_NOT_FOUND="Input file does not exists. Please provide correct input file path. Exiting.";
    public static final String ERROR_UNSUPPORTED_INPUT_FILE="File format is not supported. Supported formats are .csv, .xml and .json. Exiting.";
    public static final String SUCCESS_MESSAGE_OUTPUT_FILE_GENERATED="Successfully generated output file: ";
    public static final String CARD_NUMBER_INPUT_KEY = "cardNumber";
    public static final String CARD_EXPIRATION_DATE_INPUT_KEY = "expirationDate";
    public static final String CARD_HOLDER_NAME_INPUT_KEY = "cardHolderName";
    public static final String MESSAGE_NONE="None";
    public static final String ERROR_MESSAGE_INVALID_CARD_NUMBER="InvalidCardNumber";
    public static final String ERROR_MESSAGE_INVALID_CARD_EXPIRY="InvalidCardExpiry";
    public static final String ERROR_MESSAGE_INVALID_CARD_HOLDER_NAME="InvalidCardHolderName";
    public static final String CARD_NUMBER_OUTPUT_KEY = "cardNumber";
    public static final String CARD_TYPE_OUTPUT_KEY = "cardType";
    public static final String CARD_ERROR_OUTPUT_KEY = "Error";

    // Constants specific to XML file
    public static final String ELEMENT_ROOT = "CARDS";
    public static final String ELEMENT_ROW = "CARD";

    public static final String XML_CARD_NUMBER_INPUT_KEY = "CARD_NUMBER";
    public static final String XML_CARD_EXPIRATION_DATE_INPUT_KEY = "EXPIRATION_DATE";
    public static final String XML_CARD_HOLDER_NAME_INPUT_KEY = "CARD_HOLDER_NAME";
    
    public static final String XML_CARD_NUMBER_OUTPUT_KEY = "CARD_NUMBER";
    public static final String XML_CARD_TYPE_OUTPUT_KEY = "CARD_TYPE";
    
}