package com.cmpe202.aish.creditcard.filehandler.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InputJSONCardsModel {

    @SerializedName("cardsdata")
    @Expose
    private InputJSONDataModel[] cardsData = null;


    public InputJSONDataModel[] getCardsdata() {
        return cardsData;
    }

    public void setOffersdata(InputJSONDataModel[] cardsData) {
        this.cardsData = cardsData;
    }

}