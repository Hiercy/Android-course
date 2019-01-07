package com.example.volk1.downloadsimulate;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int JOB_ID = 0;
    private JobScheduler mJobScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
    }

    public void downloadNowJob(View view) {

        int networkOption = JobInfo.NETWORK_TYPE_ANY;

        ComponentName componentName = new ComponentName(
                getPackageName(),
                DownloadJob.class.getName());

        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, componentName)
                .setRequiredNetworkType(networkOption)
                .setRequiresCharging(true)
                .setRequiresDeviceIdle(false)
                .setPeriodic(5000);

        JobInfo jobInfo = builder.build();
        mJobScheduler.schedule(jobInfo);

        Toast.makeText(this, "Job Scheduled, job will run when " +
                "the constraints are met.", Toast.LENGTH_SHORT).show();
    }

    public void cancelJob(View view) {
        if (mJobScheduler != null) {
            mJobScheduler.cancelAll();
            mJobScheduler = null;

            Toast.makeText(getApplicationContext(), "Job canceled", Toast.LENGTH_SHORT).show();
        }
    }

    public class DownloadJob extends JobService {
        private static final String PRIMARY_CHANNEL_ID = "notify_channel";
        NotificationManager mNotificationManager;

        @Override
        public boolean onStartJob(JobParameters jobParameters) {
            createNotification();

            PendingIntent pendingIntent = PendingIntent.getActivity(
                    this,
                    0,
                    new Intent(this, MainActivity.class),
                    PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_android)
                    .setContentTitle("Performing Work")
                    .setContentText("Download in progress")
                    .setContentIntent(pendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setAutoCancel(true);

            mNotificationManager.notify(0, builder.build());

            return false;
        }

        @Override
        public boolean onStopJob(JobParameters jobParameters) {
            Toast.makeText(this, "Job Stopped: criteria not met", Toast.LENGTH_SHORT).show();
            return false;
        }

        public void createNotification() {
            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                NotificationChannel notificationChannel = new NotificationChannel(
                        PRIMARY_CHANNEL_ID,
                        "Download Notification",
                        NotificationManager.IMPORTANCE_HIGH);

                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.enableVibration(true);
                notificationChannel.setDescription("Download in progress");

                mNotificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }
}
