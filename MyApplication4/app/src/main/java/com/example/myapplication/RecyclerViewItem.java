package com.example.myapplication;

import java.util.ArrayList;

public class RecyclerViewItem {
    private String time;
    private String fname;
    private ArrayList<TransferItem> transferItems = null;

    public ArrayList<TransferItem> getTransferItems() {
        return transferItems;
    }

    public void setTransferItems(ArrayList<TransferItem> transferItems) {
        this.transferItems = transferItems;
    }

    public String getFname() {
        return fname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
}
