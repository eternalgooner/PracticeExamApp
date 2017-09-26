package com.apptrumps.practiceexamapp.utils;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.apptrumps.practiceexamapp.R;
import com.apptrumps.practiceexamapp.ResultActivity;

import java.util.Calendar;
import java.util.Date;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by User on 9/26/2017.
 *
 * when the alarm fires, the Receiver receives the broadcast Intent and then posts the notification
 */

public class WakefulReceiver extends WakefulBroadcastReceiver {
    private AlarmManager alarmManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        //WakefulReceiver.completeWakefulIntent(intent);

        NotificationManager notificationManager  =(NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        Intent repeatingIntent = new Intent(context, ResultActivity.class);
        repeatingIntent.putExtra("extras", "This is extra content put in the intent");
        repeatingIntent.putExtra("userTime", intent.getStringExtra("userTime"));

        repeatingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //build back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(ResultActivity.class);
        stackBuilder.addNextIntent(repeatingIntent);

        //PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, repeatingIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(100, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentTitle("New Notification! - content title")
                .setContentText("this is the context text");

       notificationManager.notify(100, builder.build());
    }

    /**
     * sets the next alarm to run. When alarm fires, the app broadcasts an Intent to this receiver
     */
    public void setAlarm(Context context){
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, WakefulReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        //always recomputer calendar after using add, set, roll
        Date date = calendar.getTime();

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, date.getTime(), alarmIntent);

        //enable BootReceiver to automatically restart when device is rebooted
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT, PackageManager.DONT_KILL_APP);
    }

    /**
     * cancels the next alarm from running. Removes any intents set by this receiver
     */
    public void cancelAlarm(Context context){
        Log.d("WakefulReceiver", "{cancel alarm}");

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, WakefulReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        alarmManager.cancel(alarmIntent);

        //disable BootReceiver so that it doesn't automatically restart wjen the device is rebooted
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }
}
