package com.cmpe202.aish.creditcard;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cmpe202.aish.creditcard.chain.*;
import com.cmpe202.aish.creditcard.factory.ConcreteCreditCardFactory;
import com.cmpe202.aish.creditcard.filehandler.CCDetailsHandler;
import com.cmpe202.aish.creditcard.filehandler.FileType;

public class CreditCardDetailsProcessor {
    public static void main(String[] argv) {
        try
        {
            if(argv.length != 2) {
                throw new IOException(Constants.ERROR_ARGUMENTS_COUNT);
            }else {
                String input = argv[0];
                String output = argv[1];

                String input_extension = "";
                String output_extension = "";
                if(input.lastIndexOf(".") != -1 && input.lastIndexOf(".") != 0){
                    input_extension = input.substring(input.lastIndexOf(".")+1);
                }

                if(output.lastIndexOf(".") != -1 && output.lastIndexOf(".") != 0){
                    output_extension = output.substring(output.lastIndexOf(".")+1);
                }
                if(!input_extension.equalsIgnoreCase(output_extension)){
                    throw new IOException(Constants.ERROR_INPUT_OUTPUT_FILE_TYPES_NOT_SAME);
                }

                CCDetailsHandler ccDetailsHandler = new CCDetailsHandler(input, output);
                if (input_extension.equalsIgnoreCase("csv") ) {
                    ccDetailsHandler.setFileType(FileType.CSV);
                } else if (input_extension.equalsIgnoreCase("json")) {
                    ccDetailsHandler.setFileType(FileType.JSON);
                } else if (input_extension.equalsIgnoreCase("xml")) {
                    ccDetailsHandler.setFileType(FileType.XML);
                } else {
                    System.out.println(Constants.ERROR_UNSUPPORTED_INPUT_FILE);
                    System.exit(-1);
                }

                // Parse input file and create list of CreditCard
                List<CreditCard> input_cc_list = ccDetailsHandler.parse();
                List<CreditCard> output_cc_list = new ArrayList<CreditCard>();

                // Iterate through each CreditCard and create the appropriate
                // CreditCard instance based on it's type.
                // e.g. MasterCC, VisaCC, AMEXCC, DiscoverCC
                // Create Chain for Identifying Credit Card type
                ConcreteCreditCardFactory ccFactory = new ConcreteCreditCardFactory();
                for (CreditCard input_cc : input_cc_list) {
                    // Factory method create() creates the instance of
                    // appropriate CreditCard type.
                    CreditCard cc = ccFactory.create(input_cc);
                    if (cc == null) {
                        input_cc.setType(CCType.Invalid);
                        cc = input_cc;
                    }
                    output_cc_list.add(cc);
                }
                ccDetailsHandler.write(output_cc_list);
            }
        }catch (FileNotFoundException e) {
                System.out.println(Constants.ERROR_INPUT_FILE_NOT_FOUND);
                System.exit(-1);
        }catch (IOException e) {
                System.out.println(e.getMessage());
                System.exit(-1);
        }
    }
}