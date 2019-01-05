package com.example.volk1.notificationscheduler;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class NotificationJobService extends JobService {
    private static final String PRIMARY_CHANNEL_ID = "notify_channel";

    NotificationManager mNotificationManager;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        createNotificationChannel();

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                new Intent(this, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_job_running)
                .setContentTitle("Job Service")
                .setContentText("Your Job ran to completion")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);

        mNotificationManager.notify(0, builder.build());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }

    public void createNotificationChannel() {
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(
                    PRIMARY_CHANNEL_ID,
                    "Job notification",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setDescription("Notification from Job Service");

            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
