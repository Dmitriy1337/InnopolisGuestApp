package com.ui.innoguestapplication;

import android.content.Intent;
import android.os.Bundle;

import com.ui.innoguestapplication.sqlite_database.LoginData;
import com.ui.innoguestapplication.sqlite_database.LoginLocalDatabase;

import androidx.appcompat.app.AppCompatActivity;


import android.util.Log;

public class MainActivity extends AppCompatActivity  {
    LoginLocalDatabase loginLocalDatabase;
    String text = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


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
    protected void onDestroy() {
        loginLocalDatabase.getDbHelper().close();
        super.onDestroy();
    }

}
