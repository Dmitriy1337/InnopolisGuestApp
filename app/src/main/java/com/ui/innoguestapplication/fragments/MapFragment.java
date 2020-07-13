package com.ui.innoguestapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.ui.innoguestapplication.R;

public class MapFragment extends Fragment {


    ImageView mapPic;
    TabLayout tabs;
    ConstraintLayout root_l;
    CoordinatorLayout coordinatorLayout;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the settings_bg for this fragment
        View thisView = inflater.inflate(R.layout.fragment_map, container, false);
        mapPic = thisView.findViewById(R.id.mapImage);
        tabs = thisView.findViewById(R.id.map_tabs);
        root_l = thisView.findViewById(R.id.map_root);
        coordinatorLayout = thisView.findViewById(R.id.coordinator);

        return thisView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapPic.setImageResource(R.drawable.en_floor1);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, R.string.zoom_in_using_two_fingers, Snackbar.LENGTH_SHORT);
        snackbar.show();


        mapPic.setOnTouchListener(new ImageMatrixTouchHandler(view.getContext()));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0: {
                        mapPic.setImageResource(R.drawable.en_floor1);
                        break;
                    }
                    case 1: {
                        mapPic.setImageResource(R.drawable.en_floor2);
                        break;
                    }
                    case 2: {
                        mapPic.setImageResource(R.drawable.en_floor3);
                        break;
                    }
                    default: {
                        mapPic.setImageResource(R.drawable.icon);
                        break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}
