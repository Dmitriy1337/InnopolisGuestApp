package com.ui.innoguestapplication.backend;

import com.ui.innoguestapplication.EventList;
import com.ui.innoguestapplication.UserProfileData;
import com.ui.innoguestapplication.sqlite_database.LoginData;

import java.util.ArrayList;

public class APIRequests {



    public enum LoginState{
        NO_ERRORS,WRONG_LOGIN,WRONG_PASSWORD
    }

    public static LoginState checkValidityOfUser(LoginData loginData){

        //Returns true if password from loginData equals to correct password taken from api
        //TODO
        return LoginState.NO_ERRORS;

    }




    public static ArrayList<EventList> getEventListData(){
        //Return list eventlists of events
        //TODO
        return null;
    }




}
