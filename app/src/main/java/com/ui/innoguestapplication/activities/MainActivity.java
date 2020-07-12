package com.ui.innoguestapplication.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.ui.innoguestapplication.R;
import com.ui.innoguestapplication.backend.APIRequests;
import com.ui.innoguestapplication.backend.ResponseRest;
import com.ui.innoguestapplication.events.EventList;
import com.ui.innoguestapplication.events.EventListStorage;
import com.ui.innoguestapplication.sqlite_database.LoginData;
import com.ui.innoguestapplication.sqlite_database.LoginLocalDatabase;

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

            loginWithPreloaded(preloadedData);

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





    private void loginWithPreloaded(LoginData preloadData) {

        APIRequests.getData(LoginLocalDatabase.getLoginLocalDatabase(getBaseContext()).getToken(), new Callback<ResponseRest>(){
            @Override
            public void onResponse(Call<ResponseRest> call, Response<ResponseRest> response) {
                switch (APIRequests.validateData(response.body())){
                    case NO_ERROR:
                        Log.d("getData Main", "no error");
                        Log.d("getData Main", "no error"+APIRequests.getMainEvent(response.body()).getTitle());
                        EventList newEventList = APIRequests.getEventList(response.body());
                        newEventList.faqElems = response.body().getBody().getData().getFaq();
                        EventListStorage.setEventList(newEventList);
                        Intent intent = new Intent(getApplicationContext(), BottomNavigatorControllerActivity.class);

                        Log.d("Intent",  response.body().getBody().getData().getUser().getName()+"check");
                        LoginLocalDatabase.getLoginLocalDatabase(getApplicationContext()).setName(response.body().getBody().getData().getUser().getName());
                        String intentAction = getIntent().getAction();
                        intent.setAction(intentAction);
                        startActivity(intent);
                        break;
                    case NO_TOKEN:
                        Log.d("getData Main", "no token");
                        tryToLogin(preloadData);
                        break;
                    case WRONG_TOKEN:
                        Log.d("getData Main", "wrong token");
                        tryToLogin(preloadData);
                        break;
                    case USER_NOT_EXIST:
                        Log.d("getData Main", "user not exist");
                        tryToLogin(preloadData);
                        break;
                    case ERROR:
                        Log.d("getData Main", "error");
                        goToLogin();
                        break;
                }
            }

            @Override
            public void onFailure(Call<ResponseRest> call, Throwable t) {
                t.printStackTrace();
                goToLogin();
            }
        });
    }

    private void tryToLogin(LoginData loginData){
        APIRequests.checkValidityOfUser(loginData, new Callback<ResponseRest>() {
            @Override
            public void onResponse(Call<ResponseRest> call, Response<ResponseRest> response) {
                switch (APIRequests.validateAuth(response.body())){
                    case ERROR:
                    case WRONG_LOGIN:
                    case WRONG_PASSWORD: {
                        goToLogin();
                        break;
                    }
                    case NO_ERRORS: {
                        Log.d("before store", "yes");
                        APIRequests.storeUserInfo(getBaseContext(), response.body()); //store user data
                        Log.d("after store", "yes");

                        LoginLocalDatabase.getLoginLocalDatabase(getBaseContext()).setName(response.body().getBody().getData().getUser().getName());
                        LoginLocalDatabase.getLoginLocalDatabase(getBaseContext()).setBarcode(response.body().getBody().getData().getUser().getBarcode());

                        loginWithPreloaded(LoginLocalDatabase.getLoginLocalDatabase(getBaseContext()).getLoginDataOrNull());
                        break;
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRest> call, Throwable t) {
                t.printStackTrace();
                goToLogin();
                //also error
            }
        });
    }
    private void goToLogin(){
        startActivity(new Intent(this, LoginActivity.class));
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

}
