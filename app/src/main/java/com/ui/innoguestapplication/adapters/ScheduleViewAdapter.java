package com.ui.innoguestapplication.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;
import com.ui.innoguestapplication.EventList;
import com.ui.innoguestapplication.fragments.ScheduleListFragment;

import java.util.ArrayList;

public class ScheduleViewAdapter extends FragmentStatePagerAdapter {


    TabLayout tabLayout;
    ArrayList<EventList> list;

    public ScheduleViewAdapter(FragmentManager fm, TabLayout _tabLayout, ArrayList<EventList> list) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabLayout = _tabLayout;
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new ScheduleListFragment(list.get(position));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getDate();
    }
}
