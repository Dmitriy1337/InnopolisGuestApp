package com.ui.innoguestapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ui.innoguestapplication.fragments.FAQFragment;
import com.ui.innoguestapplication.fragments.MapFragment;
import com.ui.innoguestapplication.fragments.MenuFragment;
import com.ui.innoguestapplication.fragments.SettingsFragment;
import com.ui.innoguestapplication.fragments.SceduleFragment;
import com.ui.innoguestapplication.sqlite_database.LoginData;
import com.ui.innoguestapplication.sqlite_database.LoginLocalDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.View;
import android.view.Menu;

import android.util.Log;
import android.view.View;
import android.view.Menu;

import android.view.MenuItem;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    LoginLocalDatabase loginLocalDatabase;
    String text = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        testSQLite();

    }

    public void testSQLite(){
         loginLocalDatabase = new LoginLocalDatabase(getBaseContext());
       loginLocalDatabase.setLoginData(new LoginData("test@email.com","123456sgfdsfg"));
       text = loginLocalDatabase.getLoginDataOrNull().toString();
        Log.e("get ",text);


        //Going to Login screen
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);

    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;


        switch (item.getItemId()) {
            case R.id.navigation_schedule:
                fragment = new SceduleFragment();
                break;
            case R.id.navigation_faq:
                fragment = new FAQFragment();
                break;
            case R.id.navigation_home:
                fragment = new MenuFragment();
                break;
            case R.id.navigation_map:
                fragment = new MapFragment();
                break;
            case R.id.navigation_settings:
                fragment = new SettingsFragment();
                break;
        }

        loadFragment(fragment);
        return false;
    }



   /* @Override
    protected void onDestroy() {
        loginLocalDatabase.getDbHelper().close();
        super.onDestroy();
    }*/

}
