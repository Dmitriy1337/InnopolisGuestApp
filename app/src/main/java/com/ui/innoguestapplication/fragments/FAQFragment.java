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
import com.ui.innoguestapplication.events.EventListStorage;
import com.ui.innoguestapplication.events.FaqElem;

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
        int faqSize = EventListStorage.eventList.faqElems.size();
        String[] testListQ;
        String[] testListA;
        if(faqSize>0) {
            testListQ = new String[faqSize];
            testListA = new String[faqSize];
            int i = 0;
            for(FaqElem elem:EventListStorage.eventList.faqElems){
                testListQ[i] = elem.getQuestion();
                testListA[i] = elem.getAnswer();
                i++;
            }

        }else{
            testListQ = new String[1];
            testListA = new String[1];
            testListA[0] = "Sample Question";
            testListA[0] = "Sample Answer is provided, because event organizers have not provided any FAQ list yet";
        }
        FAQList_adapter adapter = new FAQList_adapter(testListQ, testListA, recycler_list);
        recycler_list.setAdapter(adapter);


        return thisView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
