package com.ui.innoguestapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

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

    TextView labelTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new MenuFragment());
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        labelTop = findViewById(R.id.label_top);

    }


    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

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
