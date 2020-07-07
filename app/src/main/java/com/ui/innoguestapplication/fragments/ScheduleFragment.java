package com.ui.innoguestapplication.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ui.innoguestapplication.events.Event;
import com.ui.innoguestapplication.events.EventList;
import com.ui.innoguestapplication.events.EventListStorage;
import com.ui.innoguestapplication.R;
import com.ui.innoguestapplication.adapters.ScheduleViewAdapter;
import com.ui.innoguestapplication.backend.APIRequests;
import com.ui.innoguestapplication.backend.ResponseRest;
import com.ui.innoguestapplication.sqlite_database.LocalLoginStorage;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleFragment extends Fragment {

    TabLayout tabs;
    ViewPager vp;
    ScheduleViewAdapter adapter;
    ArrayList<EventList> list2;
    TextView group;
    ImageButton editGroup;
    boolean hasFinished = false;

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
        editGroup = thisView.findViewById(R.id.edit_group);
        vp.setOffscreenPageLimit(2);

        list2 = new ArrayList<>();



        Log.d("tokenAPI",LocalLoginStorage.getInstance(getContext()).getToken());
        if(EventListStorage.eventList==null){
        APIRequests.getData(LocalLoginStorage.getInstance(getContext()).getToken(),new Callback<ResponseRest>(){

            @Override
            public void onResponse(Call<ResponseRest> call, Response<ResponseRest> response) {


                EventList newEventList = APIRequests.getEventList(response.body());
                EventListStorage.setEventList(newEventList);
                EventListStorage.eventList.getEventList().add(new Event("Calculus Lecture",
                        "2020-07-04","14:23:00","15:40:00","106",
                        true,"1","EN"));
                splitEventsByDate(list2,EventListStorage.eventList);



                group.setText(EventListStorage.eventList.getMainEvent().getGroups_amount()+"");


                createTabFragment();
            }

            @Override
            public void onFailure(Call<ResponseRest> call, Throwable t) {

            }
        });

        }else{
            EventListStorage.eventList.getEventList().add(new Event("Calculus Lecture",
                    "108","2020-07-03","01:56:00","23:40:00"));


            EventListStorage.eventList.getEventList().add(new Event("Calculus Lecture",
                    "108","2020-07-05","14:30:00","15:40:00"));



            EventListStorage.eventList.getEventList().add(new Event("Calculus Lecture",
                    "108","2020-07-06","14:30:00","15:40:00"));



            EventListStorage.eventList.getEventList().add(new Event("Calculus Lecture",
                    "108","2020-07-07","18:01:00","15:40:00"));


            splitEventsByDate(list2, EventListStorage.eventList);


            group.setText("Group " + EventListStorage.eventList.getMainEvent().getGroups_amount() + "");


            createTabFragment();
        }

        editGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(getContext(), getActivity().findViewById(R.id.edit_group), Gravity.CENTER);
                menu.getMenu().add(Menu.NONE, 1, 1, "Item 1");
                menu.getMenu().add(Menu.NONE, 2, 2, "Item 2");
                menu.setGravity(Gravity.CENTER_VERTICAL);
                menu.show();
//                menu.setGravity(Gravity.CENTER);

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int i = menuItem.getItemId();
                        String text = (String) menuItem.getTitle();
                        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                        //handle


                        return false;
                    }
                });

            }
        });


        return thisView;
    }

    private void splitEventsByDate(ArrayList<EventList> list,EventList newEventList){


        ArrayList<ArrayList<Event>> listOfSchedules = new ArrayList<>();
        ArrayList<Event> first = new ArrayList<>();
        first.add(newEventList.getEventList().get(0));
        for(int i = 1;i<newEventList.getEventList().size();i++){
               if(newEventList.getEventList().get(i-1).getEventDate()
                       .equals(newEventList.getEventList().get(i).getEventDate())){
                   first.add(newEventList.getEventList().get(i));
               }else{
                   listOfSchedules.add(first);
                   first = new ArrayList<>();
                   first.add(newEventList.getEventList().get(i));


               }
       }
        listOfSchedules.add(first);
        for(ArrayList<Event> l: listOfSchedules){

            EventList eventList = new EventList(newEventList.getMainEvent(),l);
            eventList.getMainEvent().setStart_date(l.get(0).getEventDate());
            list.add(eventList);
        }


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
