package com.ui.innoguestapplication.events;

import com.google.gson.annotations.SerializedName;

public class MainEvent {
    private String title;
    private String description;
    private String faq;
    private int groups_amount;
    private String start_date; //yyyy-mm-dd

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    private String end_date; //yyyy-mm-dd

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getFaq() {
        return faq;
    }

    public int getGroups_amount() {
        return groups_amount;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }



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
