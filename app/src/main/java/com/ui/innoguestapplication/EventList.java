package com.ui.innoguestapplication;

import java.util.ArrayList;

public class EventList {
    private ArrayList<Event> eventList;

    EventList(){
        eventList = new ArrayList<Event>();
    }
    public void addEvent(Event e){
        eventList.add(e);
    }


}
