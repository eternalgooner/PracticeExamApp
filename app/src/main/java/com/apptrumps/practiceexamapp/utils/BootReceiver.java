package com.apptrumps.practiceexamapp.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.apptrumps.practiceexamapp.ResultActivity;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by User on 9/26/2017.
 */

public class BootReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            //context = ApplicationActivity.class;
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intent1 = new Intent(context, WakefulReceiver.class);
            PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent1, 0);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());

            //calendar.add(Calendar.SECOND, MINUTS, HOUR, int);

            Date date = calendar.getTime();

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, date.getTime(), alarmIntent);
        }
    }
}
