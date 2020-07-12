package com.ui.innoguestapplication.events;

import android.os.Build;

import androidx.annotation.Keep;
import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

@Keep
public class EventList {
    MainEvent mainEvent;
    ArrayList<Event> eventList;
    public ArrayList<FaqElem>faqElems;


    public EventList(MainEvent mainEvent, ArrayList<Event> eventList){

        this.mainEvent = mainEvent;
        for(Event e:eventList){
            e.setFormattedTime();
        }
        this.eventList = eventList;
        this.faqElems =new ArrayList<>();



    }

    public Event getFollowingEvent(){
        int i = 0;

        int now =convertDateToInt(getCurrentTimeFormat());


        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        String currentDate = new SimpleDateFormat("dd.MM", Locale.getDefault()).format(new Date());
        ArrayList<Event> eventListTemp = new ArrayList<>();
        for(Event e: eventList){
            if(e.getEventDate().equals(currentDate)){
                eventListTemp.add(e);
            }
        }




        if(eventListTemp.isEmpty()){
            return  new Event("There is no other events for today",""
                    ,"","-/-","-/-",true);
        }

        Event e = eventListTemp.get(i);
        while (now>=convertDateToInt(e.getEventTimeStart())){

            if(i==eventListTemp.size()){
                break;
            }
            e = eventListTemp.get(i);
            i++;
        }
            if(i>eventListTemp.size()){
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


    private HashMap<String,ArrayList<Event>> splitEventsByDate( EventList newEventList){


        HashMap<String,ArrayList<Event>> listOfSchedules = new HashMap<>();
        ArrayList<Event> first = new ArrayList<>();
        first.add(newEventList.getEventList().get(0));
        for(int i = 1;i<newEventList.getEventList().size();i++){
            if(newEventList.getEventList().get(i-1).getEventDate()
                    .equals(newEventList.getEventList().get(i).getEventDate())){
                first.add(newEventList.getEventList().get(i));
            }else{
                listOfSchedules.put(first.get(0).getEventDate(),first);
                first = new ArrayList<>();
                first.add(newEventList.getEventList().get(i));


            }
        }
        listOfSchedules.put(first.get(0).getEventDate(),first);
        return listOfSchedules;
//        for(ArrayList<Event> l: listOfSchedules){
//
//            EventList eventList = new EventList(newEventList.getMainEvent(),l);
//            eventList.getMainEvent().setStart_date(l.get(0).getEventDate());
//            list.add(eventList);
//        }

    }


    public void setEventList(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }


    public MainEvent getMainEvent(){return  this.mainEvent;}
    public ArrayList<Event> getEventList() {
        return eventList;
    }


}
