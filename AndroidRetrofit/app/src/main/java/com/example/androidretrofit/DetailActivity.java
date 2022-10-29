package com.example.androidretrofit;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String title = getIntent().getStringExtra("intentTitle");
        String image = getIntent().getStringExtra("intentImage");
        getSupportActionBar().setTitle(title);
        Picasso.get()
                .load(image )
                .into((ImageView) findViewById(R.id.imageView2));
    }
}