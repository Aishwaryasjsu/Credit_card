package com.cmpe202.aish.creditcard.filehandler.csv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.cmpe202.aish.creditcard.CCType;
import com.cmpe202.aish.creditcard.Constants;
import com.cmpe202.aish.creditcard.CreditCard;
import com.cmpe202.aish.creditcard.filehandler.FileHandlingStrategy;

public class CSVHandlingStrategy implements FileHandlingStrategy {
    File file;

    public CSVHandlingStrategy(File file){
        this.file = file;
    }

    public List<CreditCard> parse() throws IOException {
        ArrayList<CreditCard> ccList = new ArrayList<CreditCard>();
        String row;

        int card_number_input_key_index = -1;
        int card_expiration_date_input_key_index = -1;
        int card_holder_name_input_key_index = -1;
        boolean first_row = true;
        int valid_column_count = 0;

        BufferedReader csvReader;
        csvReader = new BufferedReader(new FileReader(file));
        while ((row = csvReader.readLine()) != null) {
            if(!row.equalsIgnoreCase("")){
                String[] values = row.split(",");

                if(first_row){
                    if(values.length != 3){
                        throw new IOException("Invalid Input CSV file. CSV File should have exactly three columns ("
                                + Constants.CARD_NUMBER_INPUT_KEY +", " + Constants.CARD_EXPIRATION_DATE_INPUT_KEY
                                + ", " + Constants.CARD_HOLDER_NAME_INPUT_KEY+") headings. Exiting.");
                    }else {

                        // 1st column
                        if (values[0].equals(Constants.CARD_NUMBER_INPUT_KEY)) {
                            card_number_input_key_index = 0;
                            valid_column_count++;
                        }else if (values[0].equals(Constants.CARD_EXPIRATION_DATE_INPUT_KEY)) {
                            card_expiration_date_input_key_index = 0;
                            valid_column_count++;
                        }else if (values[0].equals(Constants.CARD_HOLDER_NAME_INPUT_KEY)) {
                            card_holder_name_input_key_index = 0;
                            valid_column_count++;
                        }

                        // 2nd column
                        if (values[1].equals(Constants.CARD_NUMBER_INPUT_KEY)) {
                            card_number_input_key_index = 1;
                            valid_column_count++;
                        }else if (values[1].equals(Constants.CARD_EXPIRATION_DATE_INPUT_KEY)) {
                            card_expiration_date_input_key_index = 1;
                            valid_column_count++;
                        }else if (values[1].equals(Constants.CARD_HOLDER_NAME_INPUT_KEY)) {
                            card_holder_name_input_key_index = 1;
                            valid_column_count++;
                        }

                        // 3rd column
                        if (values[2].equals(Constants.CARD_NUMBER_INPUT_KEY)) {
                            card_number_input_key_index = 2;
                            valid_column_count++;
                        }else if (values[2].equals(Constants.CARD_EXPIRATION_DATE_INPUT_KEY)) {
                            card_expiration_date_input_key_index = 2;
                            valid_column_count++;
                        }else if (values[2].equals(Constants.CARD_HOLDER_NAME_INPUT_KEY)) {
                            card_holder_name_input_key_index = 2;
                            valid_column_count++;
                        }
                        if(valid_column_count != 3){
                            throw new IOException("Invalid Input CSV file. CSV File should have exactly three columns headings("
                                    + Constants.CARD_NUMBER_INPUT_KEY +", " + Constants.CARD_EXPIRATION_DATE_INPUT_KEY
                                    + ", " + Constants.CARD_HOLDER_NAME_INPUT_KEY+"). Exiting.");
                        }
                        first_row = false;
                        continue;
                    }
                }
                String number = "";
                String expiry = "";
                String holdername = "";

                if(values.length == 1){
                    if(card_number_input_key_index == 0){
                        number = values[0];
                    }else if(card_expiration_date_input_key_index == 0){
                        expiry = values[0];
                    }else if(card_holder_name_input_key_index == 0){
                        holdername = values[0];
                    }
                }
                if(values.length == 2){
                    if(card_number_input_key_index == 0){
                        number = values[0];
                    }
                    if(card_number_input_key_index == 1){
                        number = values[1];
                    }
                    if(card_expiration_date_input_key_index == 0){
                        expiry = values[0];
                    }
                    if(card_expiration_date_input_key_index == 1){
                        expiry = values[1];
                    }
                    if(card_holder_name_input_key_index == 0){
                        holdername = values[0];
                    }
                    if(card_holder_name_input_key_index == 1){
                        holdername = values[1];
                    }
                }
                if(values.length == 3){
                    number = values[card_number_input_key_index];
                    expiry = values[card_expiration_date_input_key_index];
                    holdername = values[card_holder_name_input_key_index];
                }
                ccList.add(new CreditCard(number, expiry, holdername));
            }
        }
        csvReader.close();
        return ccList;
    }

    public void write(List<CreditCard> ccList) throws IOException {
        FileWriter csvWriter = new FileWriter(file, true);
        BufferedReader csvReader = new BufferedReader(new FileReader(file));

        ArrayList<String> columnHeadingsIndexList = new ArrayList<String>();

        // Check for column headers first.
        // Add one if not present already in output file.
        String row;
        if((row = csvReader.readLine()) == null) {
            csvWriter.write(Constants.CARD_NUMBER_OUTPUT_KEY
                + "," + Constants.CARD_TYPE_OUTPUT_KEY
                + "," + Constants.CARD_ERROR_OUTPUT_KEY + "\n");
            columnHeadingsIndexList.add(Constants.CARD_NUMBER_OUTPUT_KEY);
            columnHeadingsIndexList.add(Constants.CARD_TYPE_OUTPUT_KEY);
            columnHeadingsIndexList.add(Constants.CARD_ERROR_OUTPUT_KEY);
        }else {
            // Column headers already present in file.
            // Identify the order of headers.
            if (!row.equalsIgnoreCase("")) {
                String[] values = row.split(",");
                if (values.length != 3) {
                    throw new IOException("Invalid output CSV file. Output CSV file should have exactly three columns ("
                            + Constants.CARD_NUMBER_OUTPUT_KEY + ", " + Constants.CARD_TYPE_OUTPUT_KEY
                            + ", " + Constants.CARD_ERROR_OUTPUT_KEY + ") headings. Exiting.");
                } else {

                    // 1st column
                    if (values[0].equals(Constants.CARD_NUMBER_OUTPUT_KEY)) {
                        columnHeadingsIndexList.add(Constants.CARD_NUMBER_OUTPUT_KEY);
                    } else if (values[0].equals(Constants.CARD_TYPE_OUTPUT_KEY)) {
                        columnHeadingsIndexList.add(Constants.CARD_TYPE_OUTPUT_KEY);
                    } else if (values[0].equals(Constants.CARD_ERROR_OUTPUT_KEY)) {
                        columnHeadingsIndexList.add(Constants.CARD_ERROR_OUTPUT_KEY);
                    }

                    // 2nd column
                    if (values[1].equals(Constants.CARD_NUMBER_OUTPUT_KEY)) {
                        columnHeadingsIndexList.add(Constants.CARD_NUMBER_OUTPUT_KEY);
                    } else if (values[1].equals(Constants.CARD_TYPE_OUTPUT_KEY)) {
                        columnHeadingsIndexList.add(Constants.CARD_TYPE_OUTPUT_KEY);
                    } else if (values[1].equals(Constants.CARD_ERROR_OUTPUT_KEY)) {
                        columnHeadingsIndexList.add(Constants.CARD_ERROR_OUTPUT_KEY);
                    }

                    // 3rd column
                    if (values[2].equals(Constants.CARD_NUMBER_OUTPUT_KEY)) {
                        columnHeadingsIndexList.add(Constants.CARD_NUMBER_OUTPUT_KEY);
                    } else if (values[2].equals(Constants.CARD_TYPE_OUTPUT_KEY)) {
                        columnHeadingsIndexList.add(Constants.CARD_TYPE_OUTPUT_KEY);
                    } else if (values[2].equals(Constants.CARD_ERROR_OUTPUT_KEY)) {
                        columnHeadingsIndexList.add(Constants.CARD_ERROR_OUTPUT_KEY);
                    }

                    if (columnHeadingsIndexList.size() != 3) {
                        throw new IOException("Invalid output CSV file. Output CSV file should have exactly three columns headings("
                                + Constants.CARD_NUMBER_OUTPUT_KEY + ", " + Constants.CARD_TYPE_OUTPUT_KEY
                                + ", " + Constants.CARD_ERROR_OUTPUT_KEY + "). Exiting.");
                    }
                }
            }
        }
        csvReader.close();

        for(CreditCard cc : ccList){
            String error = Constants.MESSAGE_NONE;
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

            // Build the output string
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (String columnHeading : columnHeadingsIndexList){
                count++;
                if(Constants.CARD_NUMBER_OUTPUT_KEY.equalsIgnoreCase(columnHeading)){
                    sb.append(cc.getCcNumber());
                }else if(Constants.CARD_TYPE_OUTPUT_KEY.equalsIgnoreCase(columnHeading)){
                    sb.append(cc.getType().toString());
                }else if(Constants.CARD_ERROR_OUTPUT_KEY.equalsIgnoreCase(columnHeading)){
                    sb.append(error);
                }

                if(count != columnHeadingsIndexList.size()) {
                    sb.append(",");
                }else{
                    sb.append("\n");
                }
            }
            csvWriter.write(sb.toString());
        }
        csvWriter.flush();
        csvWriter.close();
        System.out.println(Constants.SUCCESS_MESSAGE_OUTPUT_FILE_GENERATED + file.getAbsolutePath());
    }
}