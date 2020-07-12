package com.ui.innoguestapplication.sqlite_database;

import android.content.Context;

public class LocalLoginStorage {

    public void setLogin(String login) {
        this.login = login;
    }



    private String login;
    private String token;



    private  String name;
    private static Context context;
    private static LocalLoginStorage localLoginStorage=null;

    private LocalLoginStorage(String login,String token){
        this.login = login;
        this.token = token;

    }

    public String getEmail(){
        return login;
    }
    public String getToken(){
        //token  = LoginLocalDatabase.getLoginLocalDatabase(context).getToken();
        return token;
    }
    public void setToken(String token) {
        LoginLocalDatabase.getLoginLocalDatabase(context).setToken(token);
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        LoginLocalDatabase.getLoginLocalDatabase(context).setToken(token);
        this.name = name;
    }

    public static LocalLoginStorage getInstance(Context context){
        if(localLoginStorage==null){
            localLoginStorage=new LocalLoginStorage("","");
        }
        return  localLoginStorage;
    }
    public static LocalLoginStorage getInstance(Context cont,String email,String token){
        if(localLoginStorage==null){
            localLoginStorage=new LocalLoginStorage(email,token);
            context = cont;
            LoginLocalDatabase.getLoginLocalDatabase(context).setToken(token);
        }
        return  localLoginStorage;
    }

}
