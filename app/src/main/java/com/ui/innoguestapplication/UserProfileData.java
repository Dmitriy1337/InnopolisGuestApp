package com.ui.innoguestapplication;

import com.ui.innoguestapplication.backend.APIRequests;
import com.ui.innoguestapplication.exceptions.IllegalPasswordException;
import com.ui.innoguestapplication.sqlite_database.LoginData;

public class UserProfileData {

    private String name, surname, telegramAlias, email;


    public UserProfileData(String name, String surname,  String email) {
        this.name = name;
        this.surname = surname;
        this.telegramAlias = telegramAlias;
        this.email = email;

    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTelegramAlias() {
        return telegramAlias;
    }

    public String getEmail() {
        return email;
    }
    //has been moved into APIRequests
    /*public static UserProfileData getUserFrofileData(LoginData loginData) throws IllegalPasswordException {

        APIRequests.LoginState state =APIRequests.checkValidityOfUser(loginData);
        if (state== APIRequests.LoginState.NO_ERRORS) {
            //TODO
           //Return new UserProfileData object using json from api


            return null;

        } else {
            throw new IllegalPasswordException(state);
        }
    }*/
}
