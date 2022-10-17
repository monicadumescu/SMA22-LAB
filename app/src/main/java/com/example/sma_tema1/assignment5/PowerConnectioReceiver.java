package com.example.sma_tema1.assignment5;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.sma_tema1.MainActivity;

public class PowerConnectioReceiver extends BroadcastReceiver {
    private Context context;
    private String chargingStatus;

    Intent newIntent = new Intent(context, MainActivity.class);
    newIntent.putExtras("status", chargingStatus);
    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, newIntent, 0);

    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, MainActivity.CHANNEL_ID)
            .setSmallIcon(android.R.mipmap.sym_def_app_icon)
            .setContentTitle("Charging state changed!")
            .setContentText(chargingStatus)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true);

    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
    notificationManagerCompat.notify(MainActivity.notificationId, mBuilder.build());

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = newIntent.getExtras();
        if (extras != null) {
            String state = extras.getString(BatteryManager.EXTRA_STATUS);
            Log.d("MY_DEBUG_TAG", state);
            if (state.equals(BatteryManager.EXTRA_PLUGGED)) {
                String statusB = extras.getString(BatteryManager.EXTRA_LEVEL);
                Log.d("MY_DEBUG_TAG", statusB);
            }
        }
    }


}
