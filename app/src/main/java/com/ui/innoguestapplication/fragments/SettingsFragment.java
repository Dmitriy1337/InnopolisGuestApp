package com.ui.innoguestapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.ui.innoguestapplication.R;
import com.ui.innoguestapplication.activities.BottomNavigatorControllerActivity;
import com.ui.innoguestapplication.activities.LoginActivity;
import com.ui.innoguestapplication.sqlite_database.Language;
import com.ui.innoguestapplication.sqlite_database.LocalLoginStorage;
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

    Switch language;
    Switch force_dark_mode;
    Switch alerts;
    ConstraintLayout logout_view;
    TextView userName;
    TextView userEmail;
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
        userEmail = thisView.findViewById(R.id.user_email);


         languageS = LocalSettingsStorage.getLocalSettingsStorage(getContext()).getLanguage();
         theme = LocalSettingsStorage.getLocalSettingsStorage(getContext()).getTheme();
         notifySound = LocalSettingsStorage.getLocalSettingsStorage(getContext()).getSound();

        Log.d("email",LocalLoginStorage.getInstance(getContext()).getEmail());
        userEmail.setText(LoginLocalDatabase.getLoginLocalDatabase(getContext()).getLoginDataOrNull().getEmail());

        if(theme==Theme.DARK){

            force_dark_mode.toggle();
        }
        if(notifySound==NotifySound.OFF){

            alerts.toggle();
        }

//        language.setOnCheckedChangeListener(new MaterialAnimatedSwitch.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(boolean isChecked) {
//
//                if (isChecked) {
//                    LocalSettingsStorage.getLocalSettingsStorage(getContext()).setLanguage(Language.RU);
//                    Log.d("lan",LocalSettingsStorage.getLocalSettingsStorage(getContext()).getLanguage().toString());
//                }else{
//                    LocalSettingsStorage.getLocalSettingsStorage(getContext()).setLanguage(Language.EN);
//                    Log.d("lan",LocalSettingsStorage.getLocalSettingsStorage(getContext()).getLanguage().toString());
//                }
//                Log.d("language",LocalSettingsStorage.getLocalSettingsStorage(getContext()).getLanguage().toString());
//
//            }
//        });

        force_dark_mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton b,boolean isChecked) {
                if (isChecked) {

                    LocalSettingsStorage.getLocalSettingsStorage(getContext()).setTheme(Theme.DARK);

                    Intent logout = new Intent(getContext(), BottomNavigatorControllerActivity.class);
                    startActivity(logout);

                } else
                    LocalSettingsStorage.getLocalSettingsStorage(getContext()).setTheme(Theme.LIGHT);

               Intent logout = new Intent(getContext(), BottomNavigatorControllerActivity.class);
               startActivity(logout);

            }
        });
        alerts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton b,boolean isChecked) {
                if (isChecked) {
                    LocalSettingsStorage.getLocalSettingsStorage(getContext()).setSound(NotifySound.ON);
                }else{
                    LocalSettingsStorage.getLocalSettingsStorage(getContext()).setSound(NotifySound.OFF);

                }


                String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                ((BottomNavigatorControllerActivity)getActivity()).addNotification(new Notification("New notofication",currentTime,"Descr"),getContext());

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


        Log.d("onCreateView",thisView.toString());
        return thisView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("onViewCreated",view.toString());

    }
}
