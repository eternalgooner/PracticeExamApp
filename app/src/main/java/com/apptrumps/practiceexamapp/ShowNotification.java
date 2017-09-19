package com.apptrumps.practiceexamapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class ShowNotification extends AppCompatActivity {

    private Button btnShowNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notification);

        btnShowNotification = (Button) findViewById(R.id.btnShowNotification);
        addClickHandler(btnShowNotification);
    }

    private void addClickHandler(Button btnShowNotification) {
        btnShowNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewNotification();
            }
        });
    }

    private void createNewNotification() {
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("New Notification! - content title")
                .setContentText("this is the context text");

        //set activity to show in intent
        Intent intent = new Intent(this, ResultActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(resultPendingIntent);

        int notificationId = 101;
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.notify(notificationId, builder.build());
    }
}
