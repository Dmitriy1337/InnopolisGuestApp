package com.ui.innoguestapplication.sqlite_database;

import androidx.annotation.NonNull;

public class LoginData {

    private String email = "";
    private String passwordHash = "";


    public LoginData(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = ""+passwordHash.hashCode();
    }


    @NonNull
    @Override
    public String toString() {
        return email+"\n"+passwordHash;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof LoginData){
            LoginData loginData = (LoginData)o;
            if(email.equals(loginData.email)&&passwordHash.equals(loginData.passwordHash)){
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

    public String getPasswordHash() {
        return passwordHash;
    }


}
