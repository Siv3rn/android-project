package com.example.notifikasi;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Button mNotifyButton = (Button) findViewById(R.id.notify);
        mNotifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });

    }

    public void sendNotification(){
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this)
                                                .setContentTitle("You've been notified!")
                                                .setContentText("This is your notification text.")
                                                .setSmallIcon(R.drawable.ic_android);
        Notification myNotification = notifyBuilder.build();
        mNotifyManager.notify(NOTIFICATION_ID, myNotification);
    }



}