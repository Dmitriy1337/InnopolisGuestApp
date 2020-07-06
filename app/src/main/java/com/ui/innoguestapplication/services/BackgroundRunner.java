package com.ui.innoguestapplication.services;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

public class BackgroundRunner {

    public static void scheduleJob(Context context) {
        ScheduleNotifyService.context = context;
        ComponentName serviceComponent = new ComponentName(context, ScheduleNotifyService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
        builder.setMinimumLatency(1 * 1000); // Wait at least 30s
        builder.setOverrideDeadline(2 * 1000); // Maximum delay 60s

        JobScheduler jobScheduler = (JobScheduler)context.getSystemService(context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
    }
}
