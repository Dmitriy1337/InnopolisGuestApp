package com.ui.innoguestapplication;

import java.util.ArrayList;

public class EventList {
    ArrayList<Event> eventList;
    String date;

    public void setEventList(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Event> getEventList() {
        return eventList;
    }

    public String getDate() {
        return date;
    }
}
