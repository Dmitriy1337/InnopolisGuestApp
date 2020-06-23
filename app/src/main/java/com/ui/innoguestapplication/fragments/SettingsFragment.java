package com.ui.innoguestapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.github.kmenager.materialanimatedswitch.MaterialAnimatedSwitch;
import com.ui.innoguestapplication.BottomNavigatorControllerActivity;
import com.ui.innoguestapplication.R;

public class SettingsFragment extends Fragment {

    MaterialAnimatedSwitch language;
    MaterialAnimatedSwitch force_dark_mode;
    MaterialAnimatedSwitch alerts;
    ConstraintLayout logout_view;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the settings_bg for this fragment
        View thisView = inflater.inflate(R.layout.fragment_settings, container, false);
        language = thisView.findViewById(R.id.language_switch);
        force_dark_mode = thisView.findViewById(R.id.dark_theme_switch);
        alerts = thisView.findViewById(R.id.notifications_switch);
        logout_view = thisView.findViewById(R.id.logout_card);


        boolean alertsActive = !true;
        //access databese here
        boolean force_Dark_Theme = false;
        //access databese here

        force_dark_mode.setActivated(force_Dark_Theme);
        alerts.setActivated(alertsActive);


        language.setOnCheckedChangeListener(new MaterialAnimatedSwitch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(boolean isChecked) {
                //decided to mirror phone locale
            }
        });

        force_dark_mode.setOnCheckedChangeListener(new MaterialAnimatedSwitch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(boolean isChecked) {
                if (isChecked) {

                    ((BottomNavigatorControllerActivity) getActivity()).setDark();
                } else

                    ((BottomNavigatorControllerActivity) getActivity()).setLight();
                //write to database

            }
        });
        alerts.setOnCheckedChangeListener(new MaterialAnimatedSwitch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(boolean isChecked) {
                isChecked = !isChecked;
                //write to database
                //check if alert status is different, restart app if needed
            }
        });

        logout_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //logout when pressed
            }
        });


        return thisView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
