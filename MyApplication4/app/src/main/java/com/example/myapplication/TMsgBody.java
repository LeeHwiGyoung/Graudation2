package com.example.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TMsgBody {
    @SerializedName("itemList")
    @Expose
    private ArrayList<TransferItem> transferItemList = null;

    public ArrayList<TransferItem> getItemList() {
        return transferItemList;
    }

    public void setItemList(ArrayList<TransferItem> transferItemList) {
        this.transferItemList = transferItemList;
    }
}