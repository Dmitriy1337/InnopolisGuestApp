package com.ui.innoguestapplication;

public class Event {
    private String eventName;
    private String eventLocation;
    private String eventDate;
    private String eventTimeStart;
    private String eventTimeEnd;

    public Event(String eventName, String eventLocation, String eventDate, String eventTimeStart, String eventTimeEnd) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventTimeStart = eventTimeStart;
        this.eventTimeEnd = eventTimeEnd;
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
