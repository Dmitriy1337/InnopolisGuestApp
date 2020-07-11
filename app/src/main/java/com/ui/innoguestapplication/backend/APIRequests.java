package com.ui.innoguestapplication.backend;

import android.content.Context;
import android.util.Log;

import com.ui.innoguestapplication.events.FaqElem;
import com.ui.innoguestapplication.events.Event;
import com.ui.innoguestapplication.events.EventList;
import com.ui.innoguestapplication.events.MainEvent;
import com.ui.innoguestapplication.sqlite_database.LocalLoginStorage;
import com.ui.innoguestapplication.sqlite_database.LoginData;
import com.ui.innoguestapplication.sqlite_database.LoginLocalDatabase;


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

    public enum DataState {
        NO_ERROR,
        WRONG_TOKEN,//error:1
        USER_NOT_EXIST, //error:2
        NO_TOKEN, //error_request:1
        ERROR//just error
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
        if (validateData(response) == DataState.NO_ERROR) {
            return response.getBody().getData().getEvent();
        } return null;
    }

    public static EventList getEventList(ResponseRest response){
        if (validateData(response) == DataState.NO_ERROR){
            ArrayList<Event> list = response.getBody().getData().getSchedule();
            return new EventList(getMainEvent(response), list);
        } return null;
    }

    public static ArrayList<FaqElem> getFaqList(ResponseRest response){
        if (validateData(response) == DataState.NO_ERROR) {
            return response.getBody().getData().getFaq();
        } return null;
    }


    public static String getToken(ResponseRest response){
        if (validateAuth(response) == LoginState.NO_ERRORS) {
            return response.getBody().getData().getToken();
        } return null;
    }

    public static void storeUserInfo(Context context, ResponseRest resp){
        String token = resp.getBody().getData().getToken();
        RespUser user = resp.getBody().getData().getUser();
        LoginLocalDatabase.getLoginLocalDatabase(context).setToken(token);
        LocalLoginStorage.getInstance(context,user.getEmail(),token);
        LocalLoginStorage.getInstance(context).setToken(token);
    }

    public static DataState validateData(ResponseRest response){
        if (response != null)
            return BackendKt.validData(response);
        else return DataState.ERROR;
    }

    //checks auth response
    public static LoginState validateAuth(ResponseRest response) {
        if (response != null)
            return BackendKt.validAuth(response);
        else return LoginState.ERROR;
    }
}
