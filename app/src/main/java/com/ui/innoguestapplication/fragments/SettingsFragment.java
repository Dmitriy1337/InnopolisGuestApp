package com.ui.innoguestapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.github.kmenager.materialanimatedswitch.MaterialAnimatedSwitch;
import com.ui.innoguestapplication.BottomNavigatorControllerActivity;
import com.ui.innoguestapplication.LoginActivity;
import com.ui.innoguestapplication.R;
import com.ui.innoguestapplication.sqlite_database.Language;
import com.ui.innoguestapplication.sqlite_database.LocalSettingsStorage;
import com.ui.innoguestapplication.sqlite_database.LoginData;
import com.ui.innoguestapplication.sqlite_database.LoginLocalDatabase;
import com.ui.innoguestapplication.sqlite_database.Notification;
import com.ui.innoguestapplication.sqlite_database.NotifySound;
import com.ui.innoguestapplication.sqlite_database.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SettingsFragment extends Fragment {

    MaterialAnimatedSwitch language;
    MaterialAnimatedSwitch force_dark_mode;
    MaterialAnimatedSwitch alerts;
    ConstraintLayout logout_view;
    Language languageS;
    Theme theme;
    NotifySound notifySound;
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


        boolean alertsActive = false;
        //access databese here
        boolean force_Dark_Theme = false;
        //access databese here

         languageS = LocalSettingsStorage.getLocalSettingsStorage(getContext()).getLanguage();
         theme = LocalSettingsStorage.getLocalSettingsStorage(getContext()).getTheme();
         notifySound = LocalSettingsStorage.getLocalSettingsStorage(getContext()).getSound();


        if (languageS == Language.RU){
            Log.d("lanCheck",languageS.toString());

            language.setActivated(true);

            language.setEnabled(true);
        }
        if(theme ==Theme.DARK){
            force_dark_mode.setEnabled(true);
        }
        if(notifySound ==NotifySound.OFF){

            alerts.setEnabled(true);
        }



        language.setOnCheckedChangeListener(new MaterialAnimatedSwitch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(boolean isChecked) {

                if (isChecked) {
                    LocalSettingsStorage.getLocalSettingsStorage(getContext()).setLanguage(Language.RU);
                    Log.d("lan",LocalSettingsStorage.getLocalSettingsStorage(getContext()).getLanguage().toString());
                }else{
                    LocalSettingsStorage.getLocalSettingsStorage(getContext()).setLanguage(Language.EN);
                    Log.d("lan",LocalSettingsStorage.getLocalSettingsStorage(getContext()).getLanguage().toString());
                }
                Log.d("language",LocalSettingsStorage.getLocalSettingsStorage(getContext()).getLanguage().toString());

            }
        });

        force_dark_mode.setOnCheckedChangeListener(new MaterialAnimatedSwitch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(boolean isChecked) {
                if (isChecked) {

                    LocalSettingsStorage.getLocalSettingsStorage(getContext()).setTheme(Theme.DARK);

                    Intent logout = new Intent((BottomNavigatorControllerActivity)getActivity(), LoginActivity.class);
                    startActivity(logout);

                } else
                    LocalSettingsStorage.getLocalSettingsStorage(getContext()).setTheme(Theme.LIGHT);

               Intent logout = new Intent(getContext(), LoginActivity.class);
               startActivity(logout);

            }
        });
        alerts.setOnCheckedChangeListener(new MaterialAnimatedSwitch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(boolean isChecked) {
                if (isChecked) {
                    LocalSettingsStorage.getLocalSettingsStorage(getContext()).setSound(NotifySound.ON);
                }else{
                    LocalSettingsStorage.getLocalSettingsStorage(getContext()).setSound(NotifySound.OFF);

                }


                String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                ((BottomNavigatorControllerActivity)getActivity()).addNotification(new Notification("New notofication",currentTime),getContext());

                //write to database
                //check if alert status is different, restart app if needed
            }
        });

        logout_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginLocalDatabase.getLoginLocalDatabase(getContext()).setLoginData(new LoginData("",""));
                Intent logout = new Intent((BottomNavigatorControllerActivity)getActivity(), LoginActivity.class);
                startActivity(logout);
            }
        });


        return thisView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
