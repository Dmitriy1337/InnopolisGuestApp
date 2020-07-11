package com.ui.innoguestapplication;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MainEvent {
    public String title;
    public String description;
    public int groups_amount;
    public String start_date; //yyyy-mm-dd
    public String end_date; //yyyy-mm-dd

    public MainEvent(String title, String description, int groups_amount,
                     String start_date, String end_date){
        this.title = title;
        this.description = description;
        this.groups_amount = groups_amount;
        this.start_date = start_date;
        this.end_date = end_date;
    }

}
