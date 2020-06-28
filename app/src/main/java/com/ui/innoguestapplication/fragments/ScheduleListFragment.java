package com.ui.innoguestapplication.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ui.innoguestapplication.Event;
import com.ui.innoguestapplication.EventList;
import com.ui.innoguestapplication.R;
import com.ui.innoguestapplication.adapters.EventList_adapter;
import com.ui.innoguestapplication.adapters.ScheduleViewAdapter;

import java.util.ArrayList;


public class ScheduleListFragment extends Fragment {

    private Context context;
    private RecyclerView recyclerView;
    private ScheduleViewAdapter scheduleViewAdapter;
    private ArrayList<Event> list_of_events;

    public ScheduleListFragment(EventList eventList) {
        list_of_events = eventList.getEventList();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the settings_bg for this fragment
        return inflater.inflate(R.layout.schedule_list, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context = getContext();
        recyclerView = view.findViewById(R.id.schedule_fragment_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        EventList_adapter adapter = new EventList_adapter(list_of_events, recyclerView);
        recyclerView.setAdapter(adapter);


    }
}