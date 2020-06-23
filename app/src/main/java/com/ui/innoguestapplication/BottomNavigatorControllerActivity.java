package com.ui.innoguestapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ui.innoguestapplication.fragments.FAQFragment;
import com.ui.innoguestapplication.fragments.MapFragment;
import com.ui.innoguestapplication.fragments.MenuFragment;
import com.ui.innoguestapplication.fragments.ScheduleFragment;
import com.ui.innoguestapplication.fragments.SettingsFragment;

public class BottomNavigatorControllerActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String START_SETTINGS = "com.ui.innoguestapplication.START_SETTINGS";
    private static final String START_SCHEDULE = "com.ui.innoguestapplication.START_SCHEDULE";
    private static final String START_FAQ = "com.ui.innoguestapplication.START_FAQ";
    private static final String START_HOME = "com.ui.innoguestapplication.START_HOME";
    private static final String START_LOCATION = "com.ui.innoguestapplication.START_LOCATION";

    TextView labelTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //check database for theme mode
        /*
         * if theme is auto, check system theme and apply
         *
         *
         * */

        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                //Toast.makeText(getApplicationContext(),"light_active",Toast.LENGTH_SHORT).show();
                setTheme(R.style.DarkTheme);

                break;
            case Configuration.UI_MODE_NIGHT_NO:
                //Toast.makeText(getApplicationContext(),"dark_active",Toast.LENGTH_SHORT).show();
                setTheme(R.style.LightTheme);

                break;
        }


        setContentView(R.layout.activity_main);


        loadFragment(new MenuFragment());
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        labelTop = findViewById(R.id.label_top);

        try {
            switch (getIntent().getAction()) {
                case START_SETTINGS:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                    navigation.setSelectedItemId(R.id.navigation_settings);
                    break;

                case START_SCHEDULE:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ScheduleFragment()).commit();
                    navigation.setSelectedItemId(R.id.navigation_schedule);
                    break;
                case START_FAQ:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FAQFragment()).commit();
                    navigation.setSelectedItemId(R.id.navigation_faq);
                    break;
                case START_HOME:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MenuFragment()).commit();
                    navigation.setSelectedItemId(R.id.navigation_home);
                    break;
                case START_LOCATION:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MapFragment()).commit();
                    navigation.setSelectedItemId(R.id.navigation_map);
                    break;

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }


    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

    }

    public boolean setDark() {
        Toast.makeText(getApplicationContext(), "dark_active", Toast.LENGTH_SHORT).show();

        setTheme(R.style.DarkTheme);
        finish();
        Intent intent = new Intent();
        intent.setAction(START_SETTINGS);
        startActivity(intent);
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
        return true;
    }

    public boolean setLight() {
        Toast.makeText(getApplicationContext(), "light_active", Toast.LENGTH_SHORT).show();
        setTheme(R.style.LightTheme);
        finish();
        Intent intent = new Intent();
        intent.setAction(START_SETTINGS);
        startActivity(intent);
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;


        switch (item.getItemId()) {
            case R.id.navigation_schedule:
                fragment = new ScheduleFragment();
                labelTop.setText(R.string.title_schedule);
                break;
            case R.id.navigation_faq:
                fragment = new FAQFragment();
                labelTop.setText(R.string.title_faq);
                break;
            case R.id.navigation_home:
                fragment = new MenuFragment();
                labelTop.setText(R.string.title_home);
                break;
            case R.id.navigation_map:
                fragment = new MapFragment();
                labelTop.setText(R.string.title_map);
                break;
            case R.id.navigation_settings:
                fragment = new SettingsFragment();
                labelTop.setText(R.string.title_settings);
                break;
        }

        loadFragment(fragment);
        return false;
    }
    @Override
    public void onBackPressed() {
        // do nothing
    }
}
