package com.ui.innoguestapplication;

import java.util.ArrayList;

public class EventList {
    MainEvent mainEvent;
    ArrayList<Event> eventList;
    String date; //don't know why do we need it :)

    public EventList(MainEvent mainEvent, ArrayList<Event> eventList, String date){
        this.mainEvent = mainEvent;
        this.eventList = eventList;
        this.date = date;
    }
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
