package com.ui.innoguestapplication.sqlite_database;

import android.content.Context;

public class LocalSettingsStorage {

    private static  LocalSettingsStorage localSettingsStorage = null;
    private static Context context;


    public Language getLanguage() {
        return language;
    }

    public Theme getTheme() {
        return theme;
    }

    private Language language;

    public NotifySound getSound() {
        return  this.sound;
    }

    public void setLanguage(Language language) {
        LoginLocalDatabase.getLoginLocalDatabase(context).setLanguage(language);
        this.language = language;
    }

    public void setTheme(Theme theme) {
        LoginLocalDatabase.getLoginLocalDatabase(context).setTheme(theme);
        this.theme = theme;
    }

    public void setSound(NotifySound sound) {
        LoginLocalDatabase.getLoginLocalDatabase(context).setSound(sound);
        this.sound = sound;
    }

    private  Theme theme;
    private  NotifySound sound;
    private  LocalSettingsStorage(Context context){
        language = LoginLocalDatabase.getLoginLocalDatabase(context).getLanguage();
        theme = LoginLocalDatabase.getLoginLocalDatabase(context).getTheme();
        sound = LoginLocalDatabase.getLoginLocalDatabase(context).getSound();
    }
    public static LocalSettingsStorage getLocalSettingsStorage(Context contextt){
        if(localSettingsStorage==null){
            context = contextt;
            localSettingsStorage = new LocalSettingsStorage(contextt);
        }
        return localSettingsStorage;
    }


}
