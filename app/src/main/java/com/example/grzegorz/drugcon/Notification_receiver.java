package com.example.grzegorz.drugcon;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.grzegorz.drugcon.ui.activity.blank.Alarm;

/**
 * Created by Lenovo on 12.06.2017.
 */

public class Notification_receiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notification = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent repeating_intent = new Intent(context, Alarm.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder =   new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle("My first alarm")
                .setContentText(" My content text")
                .setAutoCancel(true);
        notification.notify(100, builder.build());
    }
}
