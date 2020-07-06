package com.ui.innoguestapplication.events;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class Event implements Comparable<Event> {
    @SerializedName("title")
    private String eventName;
    @SerializedName("location")
    private String eventLocation;
    @SerializedName("must_visit")
    private boolean eventMustVisit;
    @SerializedName("group_id")
    private String eventGroupId;
    @SerializedName("lang")
    private String eventLang;
    //Date in format of "dd.mm.yy"
    //Time in format of "hours:minutes"

    @SerializedName("date_time")
    private String eventDate;
    @SerializedName("start_time")
    private String eventTimeStart;
    @SerializedName("end_time")
    private String eventTimeEnd;

    private int month = 0;
    private int day = 0;
    private int hoursStart = 0;
    private int minutesStart = 0;
    private int hoursEnd = 0;
    private int minutesEnd = 0;


    public Event(String eventName, String eventLocation, String eventDate, String eventTimeStart, String eventTimeEnd) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventTimeStart = formatTime(eventTimeStart);
        this.eventTimeEnd = formatTime(eventTimeEnd);
    }
    public Event(String title, String dateTime, String startTime, String endTime, String location, boolean mustVisit, String groupId, String lang) {
        this.eventName = title;
        this.eventLocation = location;
        this.eventDate = dateTime;
        this.eventTimeStart = formatTime(startTime);
        this.eventTimeEnd = formatTime(endTime);
        eventMustVisit = mustVisit;
        eventGroupId = groupId;
        eventLang = lang;
    }

    public String formatTime(String time){
        if(time.length()>5){
            return time.substring(0,time.length()-3);
        }else{
            return time;
        }


    }
    public void setFormattedTime(){
        eventTimeEnd = formatTime(eventTimeEnd);
        eventTimeStart = formatTime(eventTimeStart);
    }

    private void parseIntValuesFromInputStrings() {
        try {
            String[] monthAndDay = eventDate.split(".");
            month = Integer.parseInt(monthAndDay[0]);
            day = Integer.parseInt(monthAndDay[1]);

            String[] hoursAndMinsStart = eventTimeStart.split(":");
            hoursStart = Integer.parseInt(hoursAndMinsStart[0]);
            minutesStart = Integer.parseInt(hoursAndMinsStart[1]);

            String[] hoursAndMinsEnd = eventTimeStart.split(":");
            hoursStart = Integer.parseInt(hoursAndMinsEnd[0]);
            minutesStart = Integer.parseInt(hoursAndMinsEnd[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.e("Event ", "ArrayIndexOutOfBoundsException in Event constructor, wrong parsing");
        }
    }


    public String getEventName() {
        return eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventTimeStart() {
        return eventTimeStart;
    }

    public String getEventTimeEnd() {
        return eventTimeEnd;
    }


    @Override
    public int compareTo(Event otherEvent) {
        return Integer.compare(this.hoursStart * 60 + minutesStart, otherEvent.hoursStart * 60 + otherEvent.minutesStart);
    }
}
