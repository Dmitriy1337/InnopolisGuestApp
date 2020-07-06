package com.ui.innoguestapplication;

import android.util.Log;

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
            Log.d("EventList",now+"/"+convertDateToInt(e.getEventTimeStart()));
            i++;
            if(i<eventList.size()){
                e = eventList.get(i);
            }else{

                return  new Event("There is no other events for today","","","-/-","-/-");
            }
        }
        return e;
    }

    public int convertDateToInt(String date){
        String[] current = date.split(":");
        return Integer.parseInt(current[0])*60+Integer.parseInt(current[1]);
    }

    private String getCurrentTimeFormat(){
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
