package com.ui.innoguestapplication.events;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EventList {
    MainEvent mainEvent;
    ArrayList<Event> eventList;


    public EventList(MainEvent mainEvent, ArrayList<Event> eventList){

        this.mainEvent = mainEvent;
        for(Event e:eventList){
            e.setFormattedTime();
        }
        this.eventList = eventList;

    }
    public Event getFollowingEvent(){
        int i = 0;

        int now =convertDateToInt(getCurrentTimeFormat());
        Event e = eventList.get(i);
        while (now>=convertDateToInt(e.getEventTimeStart())){

            if(i==eventList.size()){
                break;
            }
            e = eventList.get(i);
            i++;
        }
            if(i>=eventList.size()){
                return  new Event("There is no other events for today",""
                        ,"","-/-","-/-");

        }
        return e;
    }

    public static int convertDateToInt(String date){
        String[] current = date.split(":");
        return Integer.parseInt(current[0])*60+Integer.parseInt(current[1]);
    }

    public static String getCurrentTimeFormat(){
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
        String time = localDateFormat.format(new Date());
        return  time;
    }
    public void setEventList(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }


    public MainEvent getMainEvent(){return  this.mainEvent;}
    public ArrayList<Event> getEventList() {
        return eventList;
    }


}
