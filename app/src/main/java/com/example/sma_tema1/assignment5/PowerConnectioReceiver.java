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

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
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
