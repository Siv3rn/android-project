package com.example.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail);
        TextView nama = (TextView) findViewById(R.id.nama);
        TextView gender = (TextView) findViewById(R.id.jk);
        TextView prodi = (TextView) findViewById(R.id.prodi);
        TextView address = (TextView) findViewById(R.id.adress);


        Intent Tintent = getIntent();
        String var1 = Tintent.getStringExtra("tname");
        String var2 = Tintent.getStringExtra("tgen");
        String var3 = Tintent.getStringExtra("tprod");
        String var4 = Tintent.getStringExtra("tadd");

        nama.setText(var1);
        gender.setText(var2);
        prodi.setText(var3);
        address.setText(var4);
    }
}