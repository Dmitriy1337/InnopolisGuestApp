package com.ui.innoguestapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ui.innoguestapplication.R;
import com.ui.innoguestapplication.adapters.EventList_adapter;
import com.ui.innoguestapplication.adapters.ScheduleViewAdapter;
import com.ui.innoguestapplication.events.Event;
import com.ui.innoguestapplication.events.EventList;

import java.util.ArrayList;


public class ScheduleListFragment extends Fragment {

    private Context context;
    private RecyclerView recyclerView;
    private ScheduleViewAdapter scheduleViewAdapter;
    private ArrayList<Event> list_of_events;
    SwipeRefreshLayout refresh;

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
        EventList_adapter adapter = new EventList_adapter(list_of_events, recyclerView, this);
        recyclerView.setAdapter(adapter);
        refresh = view.findViewById(R.id.refresh_schedule);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "Refresh", Toast.LENGTH_LONG).show();
                refreshData();
            }
        });


    }
    private void refreshData() {
        ScheduleFragment scheduleFragment = (ScheduleFragment) getParentFragment();
        scheduleFragment.updateShcedule();

        //update data here, then pass it to recyclerview and call datasetchanged method to the adapter
        refresh.setRefreshing(false);
    }
}