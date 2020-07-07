package com.ui.innoguestapplication.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ui.innoguestapplication.events.EventList;
import com.ui.innoguestapplication.events.EventListStorage;
import com.ui.innoguestapplication.R;
import com.ui.innoguestapplication.backend.APIRequests;
import com.ui.innoguestapplication.backend.ResponseRest;
import com.ui.innoguestapplication.sqlite_database.LoginData;
import com.ui.innoguestapplication.sqlite_database.LoginLocalDatabase;

import androidx.appcompat.app.AppCompatActivity;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {
    LoginLocalDatabase loginLocalDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loginLocalDatabase =  LoginLocalDatabase.getLoginLocalDatabase(getBaseContext());
        LoginData preloadedData = loginLocalDatabase.getLoginDataOrNull();

        if(preloadedData!=null){

            loginWithPreloaded();

        }

        else {


            Intent intent = new Intent(this, LoginActivity.class);
            String intentAction = getIntent().getAction();


            intent.setAction(intentAction);
            Log.d("Intent", "Main caught");
            //Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_LONG).show();
            startActivity(intent);
        }

    }





    private void loginWithPreloaded() {

        APIRequests.getData(LoginLocalDatabase.getLoginLocalDatabase(getBaseContext()).getToken(), new Callback<ResponseRest>(){

            @Override
            public void onResponse(Call<ResponseRest> call, Response<ResponseRest> response) {
                EventList newEventList = APIRequests.getEventList(response.body());
                EventListStorage.setEventList(newEventList);
                Intent intent = new Intent(getApplicationContext(), BottomNavigatorControllerActivity.class);
                String intentAction = getIntent().getAction();
                intent.setAction(intentAction);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseRest> call, Throwable t) {

            }
        });
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

}
