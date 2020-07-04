package com.ui.innoguestapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ui.innoguestapplication.sqlite_database.LocalSettingsStorage;
import com.ui.innoguestapplication.sqlite_database.Notification;
import com.ui.innoguestapplication.fragments.FAQFragment;
import com.ui.innoguestapplication.fragments.MapFragment;
import com.ui.innoguestapplication.fragments.MenuFragment;
import com.ui.innoguestapplication.fragments.NotificationsFragment;
import com.ui.innoguestapplication.fragments.ScheduleFragment;
import com.ui.innoguestapplication.fragments.SettingsFragment;
import com.ui.innoguestapplication.sqlite_database.NotificationStorage;
import com.ui.innoguestapplication.sqlite_database.NotifySound;
import com.ui.innoguestapplication.sqlite_database.Theme;

import java.util.Objects;

public class BottomNavigatorControllerActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String START_SETTINGS = "com.ui.innoguestapplication.START_SETTINGS";
    private static final String START_SCHEDULE = "com.ui.innoguestapplication.START_SCHEDULE";
    private static final String START_FAQ = "com.ui.innoguestapplication.START_FAQ";
    private static final String START_HOME = "com.ui.innoguestapplication.START_HOME";
    private static final String START_LOCATION = "com.ui.innoguestapplication.START_LOCATION";
    private static final String CHANNEL_ID = "CHANNEL_ID";

    TextView labelTop;
    ImageButton notifications_button;

    static ScheduleFragment scheduleFragment = new ScheduleFragment();
    static FAQFragment faqFragment = new FAQFragment();
    static MenuFragment menuFragment = new MenuFragment();
    static MapFragment mapFragment = new MapFragment();
    static SettingsFragment settingsFragment = new SettingsFragment();

    static Fragment current = menuFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingsFragment = new SettingsFragment();

        //check database for theme mode
        /*
         * if theme is auto, check system theme and apply
         *
         *
         * */


        if (LocalSettingsStorage.getLocalSettingsStorage(getBaseContext()).getTheme() == Theme.DARK) {

            setTheme(R.style.DarkTheme);
        }
        else{
        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                //Toast.makeText(getApplicationContext(),"light_active",Toast.LENGTH_SHORT).show();
                setTheme(R.style.DarkTheme);

                break;
            case Configuration.UI_MODE_NIGHT_NO:
                //Toast.makeText(getApplicationContext(),"dark_active",Toast.LENGTH_SHORT).show();
                setTheme(R.style.LightTheme);

                break;
        }

    }

        setContentView(R.layout.activity_main);





        labelTop = findViewById(R.id.label_top);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        navigation.setSelectedItemId(R.id.navigation_home);

        notifications_button = findViewById(R.id.notifications_button);

        notifications_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                labelTop.setText(R.string.title_notifications);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NotificationsFragment()).commit();

            }
        });

        try {
            switch (Objects.requireNonNull(getIntent().getAction())) {
                case START_SETTINGS:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                    navigation.setSelectedItemId(R.id.navigation_settings);
                    break;

                case START_SCHEDULE:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ScheduleFragment()).commit();
                    navigation.setSelectedItemId(R.id.navigation_schedule);
                    break;
                case START_FAQ:
                    current = faqFragment;
                    loadFragment(faqFragment);

                    break;
                case START_HOME:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MenuFragment()).commit();
                    navigation.setSelectedItemId(R.id.navigation_home);
                    break;
                case START_LOCATION:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MapFragment()).commit();
                    navigation.setSelectedItemId(R.id.navigation_map);
                    break;

            }


        } catch (NullPointerException e) {

            e.printStackTrace();
        }

        loadFragment(menuFragment);




        //**add this line**


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Notifications",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Notifications of an app");
            channel.enableLights(true);
            channel.setLightColor(Color.BLACK);
            channel.enableVibration(false);
            notificationManager.createNotificationChannel(channel);
        }


    }

    public  void addNotification(Notification notification, Context context) {
        if (LocalSettingsStorage.getLocalSettingsStorage(getBaseContext()).getSound() == NotifySound.ON){
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
                .setContentText(notification.getText())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(13, builder.build());
    }
    }


    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;


        switch (item.getItemId()) {
            case R.id.navigation_schedule:
                fragment = scheduleFragment;
                labelTop.setText(R.string.title_schedule);
                break;
            case R.id.navigation_faq:
                fragment = faqFragment;
                labelTop.setText(R.string.title_faq);
                break;
            case R.id.navigation_home:
                fragment =menuFragment;
                labelTop.setText(R.string.title_home);
                break;
            case R.id.navigation_map:
                fragment = mapFragment;
                labelTop.setText(R.string.title_map);
                break;
            case R.id.navigation_settings:
                fragment = settingsFragment;
                labelTop.setText(R.string.title_settings);
                break;
        }

        current = fragment;
        loadFragment(fragment);
        return true;
    }
    @Override
    public void onBackPressed() {
        loadFragment(current);
        // do nothing
    }
}
