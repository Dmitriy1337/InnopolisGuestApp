package com.ui.innoguestapplication.events;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;

@Keep
public class MainEvent {
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;

    private String faq;
    @SerializedName("groups_amount")
    private int groups_amount;
    @SerializedName("start_date")
    private String start_date; //yyyy-mm-dd
    @SerializedName("end_date")
    private String end_date; //yyyy-mm-dd

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

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



    public MainEvent(String title, String description, int groups_amount,
                     String start_date, String end_date){
        this.title = title;

        this.description = description;
        this.groups_amount = groups_amount;
        this.start_date = start_date;
        this.end_date = end_date;
    }


}
