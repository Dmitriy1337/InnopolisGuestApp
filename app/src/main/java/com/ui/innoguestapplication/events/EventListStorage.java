package com.ui.innoguestapplication.events;

import androidx.annotation.Keep;

@Keep
public class EventListStorage {

    public synchronized static void setEventList(EventList eventList) {
        EventListStorage.eventList = eventList;
    }

    public static EventList eventList = null;


}
