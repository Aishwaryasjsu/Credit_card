package com.cmpe202.aish.creditcard.filehandler.json;

import com.cmpe202.aish.creditcard.CCType;
import com.cmpe202.aish.creditcard.Constants;
import com.cmpe202.aish.creditcard.CreditCard;
import com.cmpe202.aish.creditcard.filehandler.FileHandlingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class JSONHandlingStrategy implements FileHandlingStrategy {
    File file;

    public JSONHandlingStrategy(File file){
        this.file = file;
    }

    public List<CreditCard> parse() throws IOException {
        ArrayList<CreditCard> ccList = new ArrayList<CreditCard>();

        try
        {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            
            java.lang.reflect.Type cardListType = new TypeToken<InputJSONDataModel[]>() {}.getType();
            
            JsonObject jsonObject = gson.fromJson(br, JsonObject.class);
            
            JsonArray jsonArray = jsonObject.getAsJsonArray("cards");
           
            InputJSONDataModel[] inputCardList = gson.fromJson(jsonArray, cardListType);
            

            for (InputJSONDataModel inputCC : inputCardList){
                ccList.add(new CreditCard(inputCC.getCardNumber(),
                        inputCC.getExpirationDate(),
                        inputCC.getNameOfCardholder()));
            }

        } catch (IOException e) {
            throw new IOException("Failed to parse input json file." + e.getMessage());
        }
        return ccList;
    }

    public void write(List<CreditCard> ccList) throws IOException {
        List<OutputJSONDataModel> outputCardJsonList = new ArrayList<OutputJSONDataModel>();

        for(CreditCard cc : ccList){

            OutputJSONDataModel outputJSONDataModel = new OutputJSONDataModel();

            outputJSONDataModel.setCardNumber(cc.getCcNumber());
            outputJSONDataModel.setCardType(cc.getType().toString());
            outputCardJsonList.add(outputJSONDataModel);
        }

        try{
            Gson json = new GsonBuilder().setPrettyPrinting().create();
            json.toJson(outputCardJsonList);
            FileWriter jsonWriter = new FileWriter(file);
            jsonWriter.write(json.toJson(outputCardJsonList));
            jsonWriter.close();
        }catch (IOException e){
            throw new IOException("Failed to write output json file." + e.getMessage());
        }
        System.out.println(Constants.SUCCESS_MESSAGE_OUTPUT_FILE_GENERATED + file.getAbsolutePath());
    }
}