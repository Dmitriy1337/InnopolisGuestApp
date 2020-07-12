package com.ui.innoguestapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.ui.innoguestapplication.R;
import com.ui.innoguestapplication.backend.APIRequests;
import com.ui.innoguestapplication.backend.ResponseRest;
import com.ui.innoguestapplication.events.EventList;
import com.ui.innoguestapplication.events.EventListStorage;
import com.ui.innoguestapplication.sqlite_database.LocalLoginStorage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        //Going to Login screen

        loginWithNoErrors();



    }




    private  void loginWithNoErrors(){
        APIRequests.getData(LocalLoginStorage.getInstance(getBaseContext()).getToken(), new Callback<ResponseRest>(){

            @Override
            public void onResponse(Call<ResponseRest> call, Response<ResponseRest> response) {
                switch (APIRequests.validateData(response.body())){
                    case NO_ERROR:
                        Log.d("getData splash", "no error");
                        //Log.d("getData splash", ""+);
                        EventList newEventList = APIRequests.getEventList(response.body());
                        newEventList.faqElems = response.body().getBody().getData().getFaq();
                        EventListStorage.setEventList(newEventList);
                        Log.d("descEventStorageOnREsp",""+EventListStorage.eventList.getMainEvent().getDescription());
                        Intent intent = new Intent(getApplicationContext(), BottomNavigatorControllerActivity.class);
                        String intentAction = getIntent().getAction();
                        //Toast.makeText(getApplicationContext(), "Success:"+response.body().getBody().getData().getToken(), Toast.LENGTH_SHORT).show();
                        intent.setAction(intentAction);
                        startActivity(intent);
                        break;
                    case NO_TOKEN:
                        Log.d("getData splash", "no token");
                        break;
                    case WRONG_TOKEN:
                        Log.d("getData splash", "wrong token");
                        break;
                    case USER_NOT_EXIST:
                        Log.d("getData splash", "user not exist");
                        break;
                    case ERROR:
                        Log.d("getData splash", "error");
                        break;
                }
            }

            @Override
            public void onFailure(Call<ResponseRest> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    @Override
    public void onBackPressed() {
        //do nothing
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

}
