package com.example.volk1.standup;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    // notify id
    private static final int NOTIFICATION_ID = 0;
    // channel id
    private static final String PRIMARY_CHANNEL_ID = "notify_channel";

    private NotificationManager mNotificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        deliverNotify(context);
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
}
