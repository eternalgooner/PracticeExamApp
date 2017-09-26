package com.apptrumps.practiceexamapp.utils;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by User on 9/26/2017.
 *
 * when the alarm fires, the Receiver receives the broadcast Intent and then posts the notification
 */

public class WakefulReceiver extends WakefulBroadcastReceiver {
    private AlarmManager alarmManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        WakefulReceiver.completeWakefulIntent(intent);
    }
}
