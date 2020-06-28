package com.ui.innoguestapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ui.innoguestapplication.R;
import com.ui.innoguestapplication.adapters.FAQList_adapter;

public class FAQFragment extends Fragment {

    RecyclerView recycler_list;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the settings_bg for this fragment
        View thisView = inflater.inflate(R.layout.fragment_faq, container, false);

        recycler_list = thisView.findViewById(R.id.faq_list);
        recycler_list.setLayoutManager(new LinearLayoutManager(getActivity()));

        String[] testListQ = {"In April 2018, Russia’s telecom regulator Roskomnadzor blocked Telegram on the country’s territory.", "The first week of the ban was challenging, and many of our users in Russia had connection issues."};
        String[] testListA = {"We knew it was coming, so by the time the block went live, we had already upgraded the Telegram apps with support for rotating proxy servers, ways to hide traffic and other anti-censorship tools. We were joined by thousands of Russian engineers that set up their own proxies for Telegram users, forming a decentralised movement called Digital Resistance.", "In an attempt to prevent users from accessing Telegram, Roskomnadzor blacklisted millions of IP addresses. However, thanks to Digital Resistance, after May 2018 Telegram became largely accessible in Russia."};
        FAQList_adapter adapter = new FAQList_adapter(testListQ, testListA, recycler_list);
        recycler_list.setAdapter(adapter);


        return thisView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
