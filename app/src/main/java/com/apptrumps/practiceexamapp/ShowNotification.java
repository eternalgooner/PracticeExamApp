package com.apptrumps.practiceexamapp;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.apptrumps.practiceexamapp.utils.WakefulReceiver;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class ShowNotification extends AppCompatActivity implements TimePicker.OnTimeChangedListener{

    private Button btnShowNotification;
    private TimePicker timePicker;
    private Button btnCancelAlarm;
    private int hour;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notification);

        btnShowNotification = (Button) findViewById(R.id.btnShowNotification);
        btnCancelAlarm = (Button) findViewById(R.id.btnCancelAlarm);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(this);
        addClickHandler(btnShowNotification);
        addClickHandler(btnCancelAlarm);
    }

    private void addClickHandler(final Button btnShowNotification) {
        btnShowNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnShowNotification.getId() == R.id.btnShowNotification){
                    Log.d("addClickHandler", "btn click - match for notification");
                    createNewNotification();
                }else{
                    Log.d("addClickHandler", "btn click - match for cancel alarm");
                    cancelNotification();
                }

            }
        });
    }

    private void cancelNotification() {
        Intent intent = new Intent(this, WakefulReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(this, 100, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.cancel(sender);
    }

    private void createNewNotification() {
//        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setAutoCancel(true)
//                .setContentTitle("New Notification! - content title")
//                .setContentText("this is the context text");
//
//        //set activity to show in intent
//        Intent intent = new Intent(this, ResultActivity.class);
//        intent.putExtra("extras", "This is extra content put in the intent");
//        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        builder.setContentIntent(resultPendingIntent);
//
//        int notificationId = 101;
//        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        manager.notify(notificationId, builder.build());


        //WakefulReceiver receiver = new WakefulReceiver();
        //receiver.setAlarm(this);


        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, WakefulReceiver.class);
        intent.putExtra("userTime", "User time chose: " + hour + " : " + minute);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 05);

        //int userHour = timePicker.getHour();
        //calendar.setTimeInMillis(System.currentTimeMillis());

        //always recomputer calendar after using add, set, roll
        //Date date = calendar.getTime();

        //alarmManager.setExact(AlarmManager.RTC_WAKEUP, date.getTime(), alarmIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 60000, alarmIntent);
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        hour = hourOfDay;
        this.minute = minute;
        Log.d("ShowNotification", "time changed - hour is: " + hour + " and minut is: " + minute);
    }
}
