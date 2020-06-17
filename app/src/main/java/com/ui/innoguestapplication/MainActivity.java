package com.ui.innoguestapplication;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.ui.innoguestapplication.fragments.FAQFragment;
import com.ui.innoguestapplication.fragments.MapFragment;
import com.ui.innoguestapplication.fragments.MenuFragment;
import com.ui.innoguestapplication.fragments.ProfileFragment;
import com.ui.innoguestapplication.fragments.SceduleFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);


    }


    public void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;


        switch (item.getItemId()){
            case R.id.navigation_schedule:fragment =new SceduleFragment();
            break;
            case R.id.navigation_faq:fragment =new FAQFragment();
                break;
            case R.id.navigation_home:fragment =new MenuFragment();
                break;
            case R.id.navigation_map:fragment =new MapFragment();
                break;
            case R.id.navigation_profile:fragment =new ProfileFragment();
                break;
        }

        loadFragment(fragment);
        return false;
    }
}
