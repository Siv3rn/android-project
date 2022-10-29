package com.example.uts_hotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailHotel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);
        ImageView gambar1 = (ImageView)findViewById(R.id.parseImage);
        TextView nama = (TextView) findViewById(R.id.data_nama);
        TextView harga = (TextView) findViewById(R.id.data_harga);
        TextView fasilitas = (TextView) findViewById(R.id.data_fas);
        TextView desc = (TextView)findViewById(R.id.deskripsi);
        Intent Tintent = getIntent();
        String var1 = Tintent.getStringExtra("nama");
        String var2 = Tintent.getStringExtra("harga1");
        int var3 = Tintent.getIntExtra("gambar2",R.drawable.hotel_1);
        String var4 = Tintent.getStringExtra("deskripsi");
        String var5 = Tintent.getStringExtra("fasilitas");

        nama.setText(var1);
        harga.setText(var2);
        desc.setText(var4);
        fasilitas.setText(var5);
        gambar1.setImageResource(var3);



    }
}