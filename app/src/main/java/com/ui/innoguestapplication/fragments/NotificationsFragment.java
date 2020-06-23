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
import com.ui.innoguestapplication.adapters.Notification;
import com.ui.innoguestapplication.adapters.Notifications_adapter;

import java.util.ArrayList;


public class NotificationsFragment extends Fragment {
    RecyclerView rv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_notifications, container, false);
        rv = thisView.findViewById(R.id.notifications_list);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Notification> list = new ArrayList<>();
        Notification test = new Notification("Ah yes", "just came in");
        list.add(test);
        list.add(test);

        Log.d("notifications", list.get(0).getText());

        Notifications_adapter adapter = new Notifications_adapter(list);
        rv.setAdapter(adapter);

        return thisView;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}