package com.example.retrofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.retrofit2.retrofit.ApiService;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    private void getDataFromAPI(){
        ApiService.endpoint().get
    }

}