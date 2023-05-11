package com.cmpe202.aish.creditcard.filehandler.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cmpe202.aish.creditcard.CCType;
import com.cmpe202.aish.creditcard.Constants;
import com.cmpe202.aish.creditcard.CreditCard;
import com.cmpe202.aish.creditcard.filehandler.FileHandlingStrategy;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLHandlingStrategy implements FileHandlingStrategy {
    File file;

    public XMLHandlingStrategy(File file){
        this.file = file;
    }
    public List<CreditCard> parse() throws IOException {
        final ArrayList<CreditCard> ccList = new ArrayList<CreditCard>();

        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                boolean bNumber = false;
                boolean bExpiry = false;
                boolean bCardHolder = false;
                String[] elements = new String[3];

                public void startElement(String uri, String localName,String qName,
                                         Attributes attributes) {
                    if (qName.equalsIgnoreCase(Constants.XML_CARD_NUMBER_INPUT_KEY)) {
                        bNumber = true;
                    }
                    else if (qName.equalsIgnoreCase(Constants.XML_CARD_EXPIRATION_DATE_INPUT_KEY)) {
                        bExpiry = true;
                    }
                    else if (qName.equalsIgnoreCase(Constants.XML_CARD_HOLDER_NAME_INPUT_KEY)) {
                        bCardHolder = true;
                    }
                }

                public void endElement(String uri, String localName,
                                       String qName) {
                    if (qName.equalsIgnoreCase(Constants.ELEMENT_ROW)) {
                        CreditCard card = new CreditCard(elements[0], elements[1], elements[2]);
                        ccList.add(card);
                        elements[0] = ""; elements[1]=""; elements[2]="";
                    }
                }

                public void characters(char[] ch, int start, int length) {
                    if (bNumber) {
                        elements[0] = new String(ch, start, length);
                        bNumber = false;
                    }
                    else if (bExpiry) {
                        elements[1] = new String(ch, start, length);
                        bExpiry = false;
                    }
                    else if (bCardHolder) {
                        elements[2] = new String(ch, start, length);
                        bCardHolder = false;
                    }
                }
            };
            saxParser.parse(file, handler);
        } catch (ParserConfigurationException e) {
            throw new IOException("Invalid input XML file. Verify tags are: "
                    + Constants.XML_CARD_NUMBER_INPUT_KEY + ", "
                    +  Constants.XML_CARD_EXPIRATION_DATE_INPUT_KEY
                    + ", " + Constants.XML_CARD_HOLDER_NAME_INPUT_KEY +
                    "Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        } catch (SAXException e) {
            throw new IOException("Invalid input XML file. Verify tags are: "
                    + Constants.XML_CARD_NUMBER_INPUT_KEY + ", "
                    +  Constants.XML_CARD_EXPIRATION_DATE_INPUT_KEY
                    + ", " + Constants.XML_CARD_HOLDER_NAME_INPUT_KEY +
                    "Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        return ccList;
    }

    public void write(List<CreditCard> ccList) throws IOException {
        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement(Constants.ELEMENT_ROOT);
            document.appendChild(root);

            for (CreditCard cc : ccList){
                // row element
                Element rowElement = document.createElement(Constants.ELEMENT_ROW);

                root.appendChild(rowElement);

                // Card Number element
                Element cardNumberElement = document.createElement(Constants.XML_CARD_NUMBER_OUTPUT_KEY);
                if(cc.getCcNumber() != null){
                    cardNumberElement.appendChild(document.createTextNode(cc.getCcNumber()));
                }else{
                    cardNumberElement.appendChild(document.createTextNode(""));
                }
                rowElement.appendChild(cardNumberElement);
                // Card Type element
                Element cardTypeElement = document.createElement(Constants.XML_CARD_TYPE_OUTPUT_KEY);
                cardTypeElement.appendChild(document.createTextNode(cc.getType().toString()));
                rowElement.appendChild(cardTypeElement);

                String error = Constants.MESSAGE_NONE;
                // Card Error element
                if(cc.getType() == CCType.Invalid){
                    error = Constants.ERROR_MESSAGE_INVALID_CARD_NUMBER;
                }else{
                    if(cc.getCcExpiry().equalsIgnoreCase("")){
                        error=Constants.ERROR_MESSAGE_INVALID_CARD_EXPIRY;
                    }
                    if(cc.getCcHolderName().equalsIgnoreCase("")){
                        error=Constants.ERROR_MESSAGE_INVALID_CARD_HOLDER_NAME;
                    }
                }

                Element errorElement = document.createElement(Constants.CARD_ERROR_OUTPUT_KEY);
                errorElement.appendChild(document.createTextNode(error));
                rowElement.appendChild(errorElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            document.setXmlStandalone(true);
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);

            System.out.println(Constants.SUCCESS_MESSAGE_OUTPUT_FILE_GENERATED + file.getAbsolutePath());

        } catch (ParserConfigurationException pce) {
            throw new IOException("Failed to write output XML file." + pce.getMessage());
        } catch (TransformerException tfe) {
            throw new IOException("Failed to write output XML file." + tfe.getMessage());
        }

    }
}
