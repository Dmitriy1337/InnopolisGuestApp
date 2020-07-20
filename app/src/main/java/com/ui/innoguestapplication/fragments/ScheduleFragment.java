package com.ui.innoguestapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.ui.innoguestapplication.R;
import com.ui.innoguestapplication.activities.BottomNavigatorControllerActivity;
import com.ui.innoguestapplication.adapters.EventList_adapter;
import com.ui.innoguestapplication.adapters.ScheduleViewAdapter;
import com.ui.innoguestapplication.backend.APIRequests;
import com.ui.innoguestapplication.backend.ResponseRest;
import com.ui.innoguestapplication.events.Event;
import com.ui.innoguestapplication.events.EventList;
import com.ui.innoguestapplication.events.EventListStorage;
import com.ui.innoguestapplication.sqlite_database.LoginLocalDatabase;

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

    ConstraintLayout slideUp;
    ExtendedFloatingActionButton fab;
    RecyclerView slide_list;

    boolean hasFinished = false;
    String groupLabel = "1";

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
        fab = thisView.findViewById(R.id.events_fab);
        slideUp = thisView.findViewById(R.id.schedule_slide_up);
        slide_list = thisView.findViewById(R.id.all_events_list);
        //slideUp.setVisibility(View.INVISIBLE);
        vp.setOffscreenPageLimit(2);

        ArrayList<Event> slide_dataset = new ArrayList<>();
        //insert events here
        EventList_adapter adapter = new EventList_adapter(slide_dataset, slide_list, getActivity());
        slide_list.setAdapter(adapter);
        slide_list.setLayoutManager(new LinearLayoutManager(getContext()));

        list2 = new ArrayList<>();

        if (EventListStorage.eventList == null)
            Log.d("scheduleEvent", "null");

        if (EventListStorage.eventList == null) {
            loadSchedule();

        } else {
            updateUI();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        editGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(getContext(), getActivity().findViewById(R.id.edit_group), Gravity.CENTER);
                int groupAmount = EventListStorage.eventList.getMainEvent().getGroups_amount();
                for (int i = 1; i <= groupAmount; i++) {
                    menu.getMenu().add(Menu.NONE, i, i, "Group " + i);
                }


                menu.setGravity(Gravity.CENTER_VERTICAL);
                menu.show();
//                menu.setGravity(Gravity.CENTER);

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int i = menuItem.getItemId();
                        groupLabel = i + "";
                        updateUI();
                        return false;
                    }
                });

            }
        });


        return thisView;
    }


    private void loadSchedule() {
        APIRequests.getData(LoginLocalDatabase.getLoginLocalDatabase(getContext()).getToken(), getContext(), new Callback<ResponseRest>() {

            @Override
            public void onResponse(Call<ResponseRest> call, Response<ResponseRest> response) {
                switch (APIRequests.validateData(response.body())) {
                    case NO_ERROR:
                        EventList newEventList = APIRequests.getEventList(response.body());
                        EventListStorage.setEventList(newEventList);
                        updateUI();
                        break;
                    case NO_TOKEN:
                        Log.d("getData schedFr", "no token");
                        break;
                    case WRONG_TOKEN:
                        Log.d("getData schedFr", "wrong token");
                        break;
                    case USER_NOT_EXIST:
                        Log.d("getData schedFr", "user not exist");
                        break;
                    case ERROR:
                        Log.d("getData schedFr", "error");
                        break;
                }

            }

            @Override
            public void onFailure(Call<ResponseRest> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public void updateUI() {
        list2.clear();
        splitEventsByDate(list2, EventListStorage.eventList, groupLabel);
        group.setText(String.format("Group %s", groupLabel));
        createTabFragment();
    }

    public void updateShcedule() {
        loadSchedule();
    }

    private static void splitEventsByDate(ArrayList<EventList> list, EventList newEventList, String group) {


        ArrayList<ArrayList<Event>> listOfSchedules = new ArrayList<>();
        ArrayList<Event> first = new ArrayList<>();
        first.add(newEventList.getEventList().get(0));
        for (int i = 1; i < newEventList.getEventList().size(); i++) {
            if (newEventList.getEventList().get(i - 1).getEventDate()
                    .equals(newEventList.getEventList().get(i).getEventDate())) {
                if (newEventList.getEventList().get(i).getEventGroupId().equals(group)) {
                    first.add(newEventList.getEventList().get(i));
                }
            } else {
                if (newEventList.getEventList().get(i).getEventGroupId().equals(group)) {
                    listOfSchedules.add(first);
                    first = new ArrayList<>();
                    first.add(newEventList.getEventList().get(i));
                }


            }
        }
        listOfSchedules.add(first);
        for (ArrayList<Event> l : listOfSchedules) {

            EventList eventList = new EventList(newEventList.getMainEvent(), l);
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
