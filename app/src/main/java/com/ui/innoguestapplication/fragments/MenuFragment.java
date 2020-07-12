package com.ui.innoguestapplication.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ui.innoguestapplication.R;
import com.ui.innoguestapplication.events.Event;
import com.ui.innoguestapplication.events.EventListStorage;
import com.ui.innoguestapplication.sqlite_database.LoginLocalDatabase;

public class MenuFragment extends Fragment {
    TextView eventName;
    TextView eventDates;
    TextView upcomingName;
    TextView upcomingTime;
    TextView barcode;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the settings_bg for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        eventName.setText(EventListStorage.eventList.getMainEvent().getTitle());
        eventDates.setText(EventListStorage.eventList.getMainEvent().getStart_date()+" "
                +EventListStorage.eventList.getMainEvent().getEnd_date());
        Event currentEvent = EventListStorage.eventList.getFollowingEvent();
        Log.d("currentEvent",currentEvent.getEventDate()+" / "+currentEvent.getEventName());
        upcomingName.setText(currentEvent.getEventName());
        upcomingTime.setText(currentEvent.getEventTimeStart()+" - "+currentEvent.getEventTimeEnd());
        barcode.setText(LoginLocalDatabase.getLoginLocalDatabase(getContext()).getBarcode());
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



         eventName = view.findViewById(R.id.event_name);
         eventDates = view.findViewById(R.id.event_dates);
         upcomingName = view.findViewById(R.id.upcoming_name);
         upcomingTime = view.findViewById(R.id.upcoming_time);
         barcode = view.findViewById(R.id.barcode_text);
         Log.d("onViewCrea","eventlist"+EventListStorage.eventList.toString());
         Log.d("onViewCrea","mainEvent"+EventListStorage.eventList.getMainEvent().toString());
         Log.d("onViewCrea","title"+EventListStorage.eventList.getMainEvent().getTitle());
        eventName.setText(EventListStorage.eventList.getMainEvent().getTitle());
        eventDates.setText(EventListStorage.eventList.getMainEvent().getStart_date()+" "
                +EventListStorage.eventList.getMainEvent().getEnd_date());
        Event currentEvent = EventListStorage.eventList.getFollowingEvent();

        upcomingName.setText(currentEvent.getEventName());
        upcomingTime.setText(currentEvent.getEventTimeStart()+" - "+currentEvent.getEventTimeEnd());
    }
}
