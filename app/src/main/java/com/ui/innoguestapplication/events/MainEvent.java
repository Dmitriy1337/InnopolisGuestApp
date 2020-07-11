package com.ui.innoguestapplication.events;

import com.google.gson.annotations.SerializedName;

public class MainEvent {
    private String title;
    private String description;
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

    public int getGroups_amount() {
        return groups_amount;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }



    public MainEvent(String title, String description, int groups_amount,
                     String start_date, String end_date){
        this.title = title;
        this.description = description;
        this.groups_amount = groups_amount;
        this.start_date = start_date;
        this.end_date = end_date;
    }


}
