package com.ui.innoguestapplication.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScheduleNotifyReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        BackgroundRunner.scheduleJob(context);
    }
}
