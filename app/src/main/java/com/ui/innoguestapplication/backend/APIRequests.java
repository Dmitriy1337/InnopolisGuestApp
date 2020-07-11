package com.ui.innoguestapplication.backend;

import android.util.Log;

import com.ui.innoguestapplication.Event;
import com.ui.innoguestapplication.EventList;
import com.ui.innoguestapplication.FaqElem;
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
            return new EventList(getMainEvent(response), list, null);
        } return null;
    }

    public static ArrayList<FaqElem> getFaqList(ResponseRest response){
        if (validateData(response) == DataState.NO_ERROR) {
            return response.getBody().getData().getFaq();
        } return null;
    }

    public static UserProfileData getUserProfileData(ResponseRest response) {
        //not implemented yet
        if (validateData(response) == DataState.NO_ERROR) {
            RespUser user = response.getBody().getData().getUser();
            return new UserProfileData(user.getName(), user.getName(), user.getEmail());
        } return null;
    }

    public static String getToken(ResponseRest response){
        if (validateAuth(response) == LoginState.NO_ERRORS) {
            return response.getBody().getData().getToken();
        } return null;
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
