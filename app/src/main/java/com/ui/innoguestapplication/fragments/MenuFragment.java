package com.ui.innoguestapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.ui.innoguestapplication.Event;
import com.ui.innoguestapplication.EventList;
import com.ui.innoguestapplication.EventListStorage;
import com.ui.innoguestapplication.R;

public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the settings_bg for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        TextView eventName = view.findViewById(R.id.event_name);
        TextView eventDates = view.findViewById(R.id.event_dates);
        TextView upcomingName = view.findViewById(R.id.upcoming_name);
        TextView upcomingTime = view.findViewById(R.id.upcoming_time);
        TextView barcode = view.findViewById(R.id.barcode_text);
        eventName.setText(EventListStorage.eventList.getMainEvent().getTitle());
        eventDates.setText(EventListStorage.eventList.getMainEvent().getStart_date()+" "
                +EventListStorage.eventList.getMainEvent().getEnd_date());
        Event currentEvent = EventListStorage.eventList.getFollowingEvent();

        upcomingName.setText(currentEvent.getEventName());
        upcomingTime.setText(currentEvent.getEventTimeStart()+" - "+currentEvent.getEventTimeEnd());
    }
}
