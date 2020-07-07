package com.ui.innoguestapplication.events;

import com.google.gson.annotations.SerializedName;

public class Event {
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


    private boolean wasNotified = false;


    public Event(String eventName, String eventLocation, String eventDate, String eventTimeStart, String eventTimeEnd,boolean wasChecked) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventTimeStart = formatTime(eventTimeStart);
        this.eventTimeEnd = formatTime(eventTimeEnd);
        wasNotified = wasChecked;
    }

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



    public boolean isWasNotified() {
        return wasNotified;
    }

    public void setWasNotified(boolean wasNotified) {
        this.wasNotified = wasNotified;
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



}
