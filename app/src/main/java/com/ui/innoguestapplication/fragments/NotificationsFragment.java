package com.ui.innoguestapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ui.innoguestapplication.R;
import com.ui.innoguestapplication.sqlite_database.Notification;
import com.ui.innoguestapplication.adapters.Notifications_adapter;
import com.ui.innoguestapplication.sqlite_database.NotificationStorage;

import java.util.ArrayList;


public class NotificationsFragment extends Fragment {
    RecyclerView rv;
    private Notifications_adapter currentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_notifications, container, false);
        rv = thisView.findViewById(R.id.notifications_list);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Notification> list = NotificationStorage.getNotificationStorage(getContext()).getNotifications();

        currentAdapter = new Notifications_adapter(list);

        rv.setAdapter(currentAdapter);

        return thisView;
    }


    public Notifications_adapter getCurrentAdapter() {

        return currentAdapter;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

}