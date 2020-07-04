package com.ui.innoguestapplication.backend;

import android.util.Log;

import com.ui.innoguestapplication.Event;
import com.ui.innoguestapplication.EventList;
import com.ui.innoguestapplication.MainEvent;
import com.ui.innoguestapplication.UserProfileData;
import com.ui.innoguestapplication.sqlite_database.LoginData;


import java.util.ArrayList;

import retrofit2.Callback;

/*
 * All methods will be void & asynchronous(except methods
 * which process response data). Example: getUserProfileData, checkValidity)
 * All the logic must be done in callbacks
 * */
public class APIRequests {

    public enum LoginState {
        NO_ERRORS, WRONG_LOGIN, WRONG_PASSWORD, ERROR
    }

    public static void checkValidityOfUser(LoginData loginData, Callback<ResponseRest> callback) {
        //Returns ResponseRest(token & userdata) on Response
        Backend.INSTANCE.auth(loginData, callback);
    }
    public static void getData(String token, Callback<ResponseRest> callback) {
        //Returns ResponseRest(userdata & event & schedule[]) on Response
        Backend.INSTANCE.getData(token, callback);
    }


    public static MainEvent getMainEvent(ResponseRest response) {
        if (true) {
            return response.getBody().getData().getEvent();
        } return null;
    }

    public static EventList getEventList(ResponseRest response){
        if (true){
            ArrayList<Event> list = response.getBody().getData().getSchedule();
            return new EventList(getMainEvent(response), list, null);
        } return null;
    }

    public static UserProfileData getUserProfileData(ResponseRest response) {
        //not implemented yet
        if (validateAuth(response) == LoginState.NO_ERRORS) {
            RespUser user = response.getBody().getData().getUser();
            return new UserProfileData(null, null, null);
        } return null;
    }

    //checks auth response
    public static LoginState validateAuth(ResponseRest response) {
        if (response != null) {
            try {
                if (response.getError_request() == 1) { // bad request
                    Log.d("LOGVAL", "bad request");
                    return LoginState.ERROR;
                } else {
                    switch (response.getBody().getSuccess()) {
                        case 0: {  //onError
                            switch (response.getBody().getError()) {
                                case 1: { // email not found
                                    Log.d("LOGVAL", "email nnot found");
                                    return LoginState.WRONG_LOGIN;
                                }
                                case 2: { // incorrect password
                                    Log.d("LOGVAL", "incorrect pass");
                                    return LoginState.WRONG_PASSWORD;
                                }
                            }
                        }
                        case 1: { //onSuccess
                            String token = response.getBody().getData().getToken();
                            RespUser user = response.getBody().getData().getUser();
                            //TODO
                            //save token & userData(only if they aren't the same)
                            Log.d("LOGVAL", "Ok");
                            return LoginState.NO_ERRORS;
                        }
                    }
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
                Log.d("LOGVAL", "exception & ");
                return LoginState.ERROR;
            }
        } else {
            // network error
            Log.d("LOGVAL", "network err");
            return LoginState.ERROR;
        }

        Log.d("LOGVAL", "nothing");
        return LoginState.ERROR;
    }
}
