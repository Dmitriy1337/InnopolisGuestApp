package com.ui.innoguestapplication.adapters;

public class Notification {
    private String text;
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

    public Notification(String text, String date_or_time) {
        this.text = text;
        this.date_or_time = date_or_time;
    }
}
