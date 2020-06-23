package com.ui.innoguestapplication.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ui.innoguestapplication.Event;
import com.ui.innoguestapplication.EventList;
import com.ui.innoguestapplication.R;
import com.ui.innoguestapplication.adapters.ScheduleViewAdapter;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {

    TabLayout tabs;
    ViewPager vp;
    ScheduleViewAdapter adapter;
    ArrayList<EventList> list2;
    TextView group;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the settings_bg for this fragment
        View thisView = inflater.inflate(R.layout.fragment_schedule, container, false);
        tabs = thisView.findViewById(R.id.scheduleTabs);
        vp = thisView.findViewById(R.id.schedule_viewpager2);
        group = thisView.findViewById(R.id.schedule_group);
        vp.setOffscreenPageLimit(2);


        //insert API here

        EventList list = new EventList();
        ArrayList<Event> events_list = new ArrayList<>();
        events_list.add(new Event("title", "room 108", "date", "start", "end"));
        events_list.add(new Event("title2", "room 109", "date2", "start2", "end2"));
        list.setDate("testDate");
        list.setEventList(events_list);
        list2 = new ArrayList<>();
        list2.add(list);
        list.setDate("testdate2");
        list2.add(list);

        group.setText("Group 12");


        createTabFragment();
        return thisView;
    }

    private void createTabFragment() {
        adapter = new ScheduleViewAdapter(getChildFragmentManager(), tabs, list2);
        vp.setAdapter(adapter);
        tabs.setupWithViewPager(vp);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
