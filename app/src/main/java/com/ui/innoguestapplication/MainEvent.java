package com.ui.innoguestapplication;

import com.google.gson.annotations.SerializedName;

public class MainEvent {
    private String title;
    private String description;
    private String faq;
    private int groups_amount;
    private String start_date; //yyyy-mm-dd
    private String end_date; //yyyy-mm-dd

    public MainEvent(String title, String description, String faq, int groups_amount,
                     String start_date, String end_date){
        this.title = title;
        this.description = description;
        this.faq = faq;
        this.groups_amount = groups_amount;
        this.start_date = start_date;
        this.end_date = end_date;
    }


}
