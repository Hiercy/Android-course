package com.example.volk1.standup;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    // notify id
    private static final int NOTIFICATION_ID = 0;
    // channel id
    private static final String PRIMARY_CHANNEL_ID = "notify_channel";

    private NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        ToggleButton toggleButton = findViewById(R.id.alarmToggle);

        Intent intent = new Intent(this, AlarmReceiver.class);

        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID, intent, PendingIntent.FLAG_NO_CREATE) != null);
        toggleButton.setChecked(alarmUp);

        final PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
                NOTIFICATION_ID,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String message;
                if (b) {
//                    deliverNotify(MainActivity.this);
                    long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
                    long triggerTime = SystemClock.elapsedRealtime();

                    // If the Toggle is turned on, set the repeating alarm with a 15 minute interval
                    if (alarmManager != null) {
                        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                triggerTime,
                                repeatInterval,
                                pendingIntent);
                    }

                    message = "Toggle activated";
                } else {
                    mNotificationManager.cancelAll();

                    if (alarmManager != null) {
                        alarmManager.cancel(pendingIntent);
                    }

                    message = "Toggle deactivated";
                }
                Toast.makeText(getApplicationContext(),
                        message,
                        Toast.LENGTH_SHORT).show();
            }
        });
        createNotificationChannel();
    }

    public void createNotificationChannel() {

        // notificatin manager object
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    PRIMARY_CHANNEL_ID,
                    "Stand Up! You fat shit",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("15 minutes have passed Stand Up and Walks around");

            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void deliverNotify(Context context) {
        Intent intent = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                NOTIFICATION_ID,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_stand_up);
        builder.setContentTitle("ALERT STAND UP!");
        builder.setContentText("You should stand up and walk around now!");
        builder.setContentIntent(pendingIntent);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setAutoCancel(true);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);

        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public void showTime(View view) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        SimpleDateFormat format = new SimpleDateFormat("dd-MM HH:mm");
        Calendar calendar = Calendar.getInstance();

        if (alarmManager != null) {
            long triggerTime;
            AlarmManager.AlarmClockInfo message = alarmManager.getNextAlarmClock();
            triggerTime = message.getTriggerTime();

            calendar.setTimeInMillis(triggerTime);
        }

        Toast.makeText(getApplicationContext(),
                "The Alarm clock is set to: " + format.format(calendar.getTime()),
                Toast.LENGTH_SHORT).show();
    }
}
