package com.ui.innoguestapplication;

import android.content.Intent;
import android.os.Bundle;

import com.ui.innoguestapplication.sqlite_database.LoginData;
import com.ui.innoguestapplication.sqlite_database.LoginLocalDatabase;

import androidx.appcompat.app.AppCompatActivity;


import android.util.Log;

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
