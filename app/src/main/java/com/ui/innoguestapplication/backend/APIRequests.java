package com.ui.innoguestapplication.backend;

import android.util.Log;

import com.ui.innoguestapplication.Event;
import com.ui.innoguestapplication.EventList;
import com.ui.innoguestapplication.UserProfileData;
import com.ui.innoguestapplication.sqlite_database.LoginData;

import java.util.List;

import retrofit2.Callback;

/*
* All methods will be void & asynchronous*/
public class APIRequests {


    public static void checkValidityOfUser(LoginData loginData, Callback<ResponseRest> callback){
        Backend.INSTANCE.auth(loginData, callback);

    }

    public static void getUserData(String email, Callback<UserProfileData> callback){
        //TODO
    }
    public static void getEventListData(Callback<List<Event>> callback){
        //TODO
    }




}
