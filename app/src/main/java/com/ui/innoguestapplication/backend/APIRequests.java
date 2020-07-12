package com.ui.innoguestapplication.backend;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.util.Log;



import com.ui.innoguestapplication.events.FaqElem;
import com.ui.innoguestapplication.events.Event;
import com.ui.innoguestapplication.events.EventList;
import com.ui.innoguestapplication.events.MainEvent;
import com.ui.innoguestapplication.sqlite_database.LocalLoginStorage;
import com.ui.innoguestapplication.sqlite_database.LoginData;
import com.ui.innoguestapplication.sqlite_database.LoginLocalDatabase;


import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Callback;

/*
 * All methods will be void & asynchronous(except methods
 * which process response data). Example: getUserProfileData, checkValidity)
 * All the logic must be done in callbacks
 * */
public class APIRequests {
    private static String NO_NETWORK_TITLE = "No Internet";
    private static String NO_NETWORK_MSG = "Check your connection";

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

    public static void checkValidityOfUser(LoginData loginData, Context context, AlertDialog dialog, Callback<ResponseRest> callback) {
        //Returns ResponseRest(token & userdata) on Response
        if (isConnected(context)){
            dialog.show();
            Backend.INSTANCE.auth(loginData, callback);
        } else {
            showMsg(context);
        }
    }
    public static void getData(String token, Context context, Callback<ResponseRest> callback) {
        //Returns ResponseRest(userdata & event & schedule[]) on Response
        if (isConnected(context)){
            Backend.INSTANCE.getData(token, callback);
        } else {
            showMsg(context);
        }
    }

    public static void showMsg(Context context){
        AlertDialog.Builder ad = new AlertDialog.Builder(context);
        ad.setTitle(NO_NETWORK_TITLE);
        ad.setMessage(NO_NETWORK_MSG);

        ad.setPositiveButton("Continue", (dialogInterface, i) -> {

        });
        ad.setCancelable(true);
        //ad.setOnCancelListener { }
        ad.show();
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
        if (response == null)
            Log.d("resp", "null");
        if (response != null)
            return BackendKt.validAuth(response);
        else return LoginState.ERROR;
    }

    //checks only network state
    public static boolean isConnected(Context context){
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    // tries to ping google
    public static boolean isConnected(){
        try{
            String command = "ping -c 1 google.com";
            return Runtime.getRuntime().exec(command).waitFor() == 0;
        } catch (InterruptedException | IOException ie){
            return false;
        }
    }


}
