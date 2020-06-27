package com.ui.innoguestapplication.sqlite_database;

import android.content.Context;

import java.util.ArrayList;

public class NotificationStorage {

    private static NotificationStorage notificationStorage = null;
    private NotificationLocalDatabase notificationLocalDatabase;


    private ArrayList<Notification> notifications;
    private NotificationStorage(Context context){
        notificationLocalDatabase = new NotificationLocalDatabase(context);
        notifications = notificationLocalDatabase.getDataOrNull();
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }
    public void addNotification(Notification notification){
      notificationLocalDatabase.addData(notification);
        notifications.add(notification);
    }
    public static  NotificationStorage getNotificationStorage(Context context){
        if(notificationStorage==null){
           notificationStorage = new NotificationStorage(context);
        } return notificationStorage;


    }


}
