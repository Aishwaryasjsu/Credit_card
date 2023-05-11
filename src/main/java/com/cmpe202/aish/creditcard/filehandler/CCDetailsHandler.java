package com.cmpe202.aish.creditcard.filehandler;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.cmpe202.aish.creditcard.CreditCard;
import com.cmpe202.aish.creditcard.filehandler.csv.CSVHandlingStrategy;
import com.cmpe202.aish.creditcard.filehandler.json.JSONHandlingStrategy;
import com.cmpe202.aish.creditcard.filehandler.xml.XMLHandlingStrategy;

public class CCDetailsHandler {

    String input_file = null;
    String output_file = null;
    FileType fileType = null;
    public CCDetailsHandler(String input_file, String output_file){
        this.input_file = input_file;
        this.output_file = output_file;
    }

    public void setFileType(FileType fileType){
        this.fileType = fileType;
    }

    public List<CreditCard> parse() throws IOException {
        File file = new File(input_file);
        FileHandlingStrategy strategy = null;
        // Select parsing strategy based on input file extension.
        if(fileType == FileType.CSV){
            strategy = new CSVHandlingStrategy(file);
        }else if(fileType == FileType.JSON){
            strategy = new JSONHandlingStrategy(file);
        }else if(fileType == FileType.XML){
            strategy = new XMLHandlingStrategy(file);
        }
        return strategy.parse();
    }

    public void write(List<CreditCard> ccList) throws IOException {
        FileHandlingStrategy strategy = null;
        File file = new File(output_file);
        // Select writing strategy based on input file extension.
        if(fileType == FileType.CSV){
            strategy = new CSVHandlingStrategy(file);
        }else if(fileType == FileType.JSON){
            strategy = new JSONHandlingStrategy(file);
        }else if(fileType == FileType.XML){
            strategy = new XMLHandlingStrategy(file);
        }
        strategy.write(ccList);
    }
}