package com.example.notifikasi;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private NotificationManager mNotifyManager;
    private static final String NOTIFICATION_GUIDE_URL =
            "https://developer.android.com/design/patterns/notifications.html";
    private static final int NOTIFICATION_ID = 0;
    private static final String ACTION_UPDATE_NOTIFICATION =
            "com.example.android.notifikasi.ACTION_UPDATE_NOTIFICATION";
    String CHANNEL_ID = "my_channel01";
    private NotificationReceiver mReceiver = new NotificationReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Button mNotifyButton = (Button) findViewById(R.id.notify);
        Button mUpdateButton = (Button) findViewById(R.id.update);
        Button mCancelButton = (Button) findViewById(R.id.cancel);
        mNotifyButton.setEnabled(true);
        mUpdateButton.setEnabled(false);
        mCancelButton.setEnabled(false);
        registerReceiver(mReceiver, new IntentFilter(ACTION_UPDATE_NOTIFICATION));

        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNotification();
                mNotifyButton.setEnabled(false);
                mUpdateButton.setEnabled(false);
                mCancelButton.setEnabled(true);

            }
        });
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelNotification();
                mNotifyButton.setEnabled(true);
                mUpdateButton.setEnabled(false);
                mCancelButton.setEnabled(false);
            }
        });
        mNotifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
                mNotifyButton.setEnabled(false);
                mUpdateButton.setEnabled(true);
                mCancelButton.setEnabled(true);
            }
        });


    }
    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    public void cancelNotification(){
        mNotifyManager.cancel(NOTIFICATION_ID);
    }

    public void updateNotification(){
        Intent learnMoreIntent = new Intent(Intent.ACTION_VIEW, Uri
                .parse(NOTIFICATION_GUIDE_URL));
        PendingIntent learnMorePendingIntent = PendingIntent.getActivity
                (this,NOTIFICATION_ID,learnMoreIntent,PendingIntent.FLAG_ONE_SHOT);

        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);


        Bitmap androidImage = BitmapFactory
                .decodeResource(getResources(),R.drawable.mascot_1);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("You've been notified!")
                .setContentText("This is your notification text.")
                .setSmallIcon(R.drawable.ic_android)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(androidImage)
                        .setBigContentTitle("Notification Updated!"))
                .addAction(R.drawable.ic_learn_more,"Learn More", learnMorePendingIntent)
                .addAction(R.drawable.ic_update, "Update", updatePendingIntent);

        Notification myNotification = notifyBuilder.build();
        mNotifyManager.notify(NOTIFICATION_ID, myNotification);

    }

    public void sendNotification(){
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent learnMoreIntent = new Intent(Intent.ACTION_VIEW, Uri
                .parse(NOTIFICATION_GUIDE_URL));
        PendingIntent learnMorePendingIntent = PendingIntent.getActivity
                (this,NOTIFICATION_ID,learnMoreIntent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this,CHANNEL_ID)
                                                .setContentTitle("You've been notified!")
                                                .setContentText("This is your notification text.")
                                                .setSmallIcon(R.drawable.ic_android)
                                                .setDefaults(NotificationCompat.DEFAULT_ALL)
                                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                                .setContentIntent(notificationPendingIntent)
                                                .addAction(R.drawable.ic_learn_more,"Learn More", learnMorePendingIntent);

        Notification myNotification = notifyBuilder.build();
        mNotifyManager.notify(NOTIFICATION_ID, myNotification);
    }


    public class NotificationReceiver extends BroadcastReceiver {
        public NotificationReceiver() {
        }
        @Override
        public void onReceive(Context context, Intent intent) {
            updateNotification();
        }
    }

}
