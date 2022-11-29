package com.example.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

}