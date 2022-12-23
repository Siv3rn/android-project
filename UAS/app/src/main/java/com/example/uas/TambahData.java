package com.example.uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahData extends AppCompatActivity {
     EditText judul,isi,tanggal,lokasi;
     Button add;
     private NotificationManager notificationManager;
    String CHANNEL_ID = "my_channel01";
    private static final int NOTIFICATION_ID = 0;

     DatabaseReference database = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        judul = (EditText) findViewById(R.id.Tam_judul);
        isi = (EditText) findViewById(R.id.Tam_isi);
        tanggal = (EditText) findViewById(R.id.Tam_tanggal);
        lokasi = (EditText) findViewById(R.id.Tam_lokasi);

        add = (Button) findViewById(R.id.Tam_button);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getJudul = judul.getText().toString();
                String getIsi = isi.getText().toString();
                String getTanggal = tanggal.getText().toString();
                String getLokasi = lokasi.getText().toString();


                database.child("Laporan").push().setValue(new ModelLaporan(getJudul,getIsi,getTanggal,getLokasi)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(TambahData.this, "Data has been saved", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(TambahData.this, MainMenu.class));
                        sendNotification();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TambahData.this, "Failed to save data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void sendNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("Report has been submitted")
                .setContentText("Thanks for the report!")
                .setSmallIcon(R.drawable.ic_android)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(notificationPendingIntent);

        Notification myNotification = notifyBuilder.build();
        notificationManager.notify(NOTIFICATION_ID, myNotification);
    }
}