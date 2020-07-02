package com.ui.innoguestapplication.sqlite_database;

import androidx.annotation.NonNull;

public class LoginData {

    private String email = "";
    private String password = "";


    public LoginData(String email, String password) {
        this.email = email;
        this.password = password;
    }


    @NonNull
    @Override
    public String toString() {
        return email+"\n"+ password;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof LoginData){
            LoginData loginData = (LoginData)o;
            if(email.equals(loginData.email)&& password.equals(loginData.password)){
                return true;
            }
            return false;
        }else{
            return false;
        }


    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


}
