package com.ui.innoguestapplication.sqlite_database;

public class Notification   {
    private String text;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private  String description;
    private String date_or_time;

    public String getText() {
        return text;
    }

    public String getDate_or_time() {
        return date_or_time;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate_or_time(String date_or_time) {
        this.date_or_time = date_or_time;
    }

    public Notification(String text, String date_or_time,String description) {
        this.text = text;
        this.date_or_time = date_or_time;
        this.description = description;
    }
}
