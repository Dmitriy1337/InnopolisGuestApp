package com.ui.innoguestapplication.sqlite_database;

public class LocalLoginStorage {

    private String login;
    private static LocalLoginStorage localLoginStorage=null;

    private LocalLoginStorage(String login){
        this.login = login;
    }
    private LocalLoginStorage(){

    }
    public String getEmail(){
        return login;
    }
    public static LocalLoginStorage getInstance(){
        if(localLoginStorage==null){
            localLoginStorage=new LocalLoginStorage("");
        }
        return  localLoginStorage;
    }
    public static LocalLoginStorage getInstance(String email){
        if(localLoginStorage==null){
            localLoginStorage=new LocalLoginStorage(email);
        }
        return  localLoginStorage;
    }

}
