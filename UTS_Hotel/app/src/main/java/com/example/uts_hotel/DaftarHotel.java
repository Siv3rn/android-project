package com.example.uts_hotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.LinkedList;

public class DaftarHotel extends AppCompatActivity {
    private final LinkedList<String> daftar_hotel = new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_hotel);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listHotel);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(DaftarHotel.this));

        HotelData[] hotelData = new HotelData[]{
                new HotelData("Hotel-1",800000,R.drawable.hotel_1,getString(R.string.deskripsi_hotel),getString(R.string.fasilitas_hotel)),
                new HotelData("Hotel-2",900000,R.drawable.hotel_2,getString(R.string.deskripsi_hotel),getString(R.string.fasilitas_hotel)),
                new HotelData("Hotel-3",700000,R.drawable.hotel_5,getString(R.string.deskripsi_hotel),getString(R.string.fasilitas_hotel)),
                new HotelData("Hotel-4",600000,R.drawable.hotel_6,getString(R.string.deskripsi_hotel),getString(R.string.fasilitas_hotel)),
                new HotelData("Hotel-5",750000,R.drawable.hotel_7,getString(R.string.deskripsi_hotel),getString(R.string.fasilitas_hotel)),
                new HotelData("Hotel-6",1000000,R.drawable.hotel_8,getString(R.string.deskripsi_hotel),getString(R.string.fasilitas_hotel)),
                new HotelData("Hotel-7",2300000,R.drawable.hotel_9,getString(R.string.deskripsi_hotel),getString(R.string.fasilitas_hotel)),
                new HotelData("Hotel-8",1000000,R.drawable.hotel_10,getString(R.string.deskripsi_hotel),getString(R.string.fasilitas_hotel)),
                new HotelData("Hotel-9",1300000,R.drawable.hotel_11,getString(R.string.deskripsi_hotel),getString(R.string.fasilitas_hotel)),
                new HotelData("Hotel-10",2100000,R.drawable.hotel_5,getString(R.string.deskripsi_hotel),getString(R.string.fasilitas_hotel)),
                new HotelData("Hotel-11",3200000,R.drawable.hotel_2,getString(R.string.deskripsi_hotel),getString(R.string.fasilitas_hotel))



        };

        HotelAdapter hotelAdapter = new HotelAdapter(hotelData, DaftarHotel.this);
        recyclerView.setAdapter(hotelAdapter);

        TextView cIn = (TextView) findViewById(R.id.date_CheckIn);
        TextView cOut = (TextView) findViewById(R.id.date_CheckOut);
        TextView jk = (TextView) findViewById(R.id.Jumlah);
        Intent Tintent = getIntent();

        String var1 = Tintent.getStringExtra("tanggal1");
        String var2 = Tintent.getStringExtra("tanggal2");
        String jumlah_kamar = Tintent.getStringExtra("kamar");
        cIn.setText(var1);
        cOut.setText(var2);
        jk.setText(String.format(jumlah_kamar));
    }
}