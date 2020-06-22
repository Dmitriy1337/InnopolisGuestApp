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

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

}
