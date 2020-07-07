package com.ui.innoguestapplication.services;

import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.ui.innoguestapplication.R;
import com.ui.innoguestapplication.activities.BottomNavigatorControllerActivity;
import com.ui.innoguestapplication.events.Event;
import com.ui.innoguestapplication.events.EventList;
import com.ui.innoguestapplication.events.EventListStorage;
import com.ui.innoguestapplication.sqlite_database.LocalSettingsStorage;
import com.ui.innoguestapplication.sqlite_database.Notification;
import com.ui.innoguestapplication.sqlite_database.NotificationStorage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScheduleNotifyService extends JobService {
    private static final String CHANNEL_ID = "CHANNEL_ID";
    public static Context context;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    public boolean onStartJob(JobParameters jobParameters) {
        Event next = EventListStorage.eventList.getFollowingEvent();

        if(!next.isWasNotified() && !next.getEventTimeStart().equals("-/-")){
            EventListStorage.eventList.getFollowingEvent().setWasNotified(true);
            if(Math.abs(EventList.convertDateToInt(next.getEventTimeStart())
                -EventList.convertDateToInt(EventList.getCurrentTimeFormat()))<10){
            String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

            addNotification(new Notification("Upcoming event",currentTime,next.getEventName()+
                    " is starting soon!"),context);

             }


        }

        BackgroundRunner.scheduleJob(getBaseContext());
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        return true;
    }
    public   void addNotification(Notification notification, Context context) {

        Log.d("Notifications", LocalSettingsStorage.getLocalSettingsStorage(getBaseContext()).getSound().toString());
        // if (LocalSettingsStorage.getLocalSettingsStorage(getBaseContext()).getSound() == NotifySound.ON){
            NotificationStorage.getNotificationStorage(context).addNotification(notification);

            int requestID = (int) System.currentTimeMillis();
            Intent notificationIntent = new Intent(getApplicationContext(), BottomNavigatorControllerActivity.class);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent contentIntent = PendingIntent.getActivity(this, requestID,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                            R.mipmap.ic_launcher))
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(contentIntent)
                    .setContentTitle(notification.getText())
                    .setContentText(notification.getDescription())
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT).setAutoCancel(true);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
            notificationManager.notify(13, builder.build());
        //}
    }
}
