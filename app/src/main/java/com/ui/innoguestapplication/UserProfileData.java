package com.ui.innoguestapplication;

import com.ui.innoguestapplication.backend.APIRequests;
import com.ui.innoguestapplication.exceptions.IllegalPasswordException;
import com.ui.innoguestapplication.sqlite_database.LoginData;

public class UserProfileData {

    private String name, surname, telegramAlias, email;

    private UserProfileData(String name, String surname, String telegramAlias, String email) {
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

    public static UserProfileData getuserFrofileData(String email, String password) throws IllegalPasswordException {
        //TODO
        if (APIRequests.checkValidityOfUser(new LoginData(email, password))) {
            return null;

        } else {
            throw new IllegalPasswordException();
        }
    }
}
