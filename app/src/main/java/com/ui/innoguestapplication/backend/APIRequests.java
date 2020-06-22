package com.ui.innoguestapplication.backend;

import com.ui.innoguestapplication.EventList;
import com.ui.innoguestapplication.UserProfileData;
import com.ui.innoguestapplication.sqlite_database.LoginData;

public class APIRequests {


    public static boolean checkValidityOfUser(LoginData loginData){
        //Hashes password and gets hashed password using email as key. Compares two hashes.
        //Returns true if they're equal, false otherwise
        //TODO
        return true;

    }
    public static UserProfileData getUserData(String email){
        //TODO
        return null;

    }
    public static EventList getEventListData(){
        //TODO
        return null;
    }




}
