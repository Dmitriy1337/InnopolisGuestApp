package com.ui.innoguestapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.ui.innoguestapplication.R;

public class ScheduleFragment extends Fragment {

    TabLayout tabs;
    RecyclerView rv;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_schedule, container, false);
        tabs = thisView.findViewById(R.id.scheduleTabs);
        rv = thisView.findViewById(R.id.schedule_list);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        tabs.addTab(tabs.newTab(), 0);
        tabs.getTabAt(0).setText("Tab 1");


        return thisView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
