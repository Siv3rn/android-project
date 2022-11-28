package com.example.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddCountryActivity extends AppCompatActivity implements OnClickListener {
    private Button addTodoBtn;
    private EditText nameEditText;
    private EditText genderEditText;
    private EditText prodiEditText;
    private EditText addressEditText;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Record");

        setContentView(R.layout.activity_add_record);


        nameEditText = (EditText) findViewById(R.id.name_edittext);
        genderEditText = (EditText) findViewById(R.id.gender_edittext);
        prodiEditText = (EditText) findViewById(R.id.prodi_edittext);
        addressEditText = (EditText) findViewById(R.id.tempat_edittext);

        addTodoBtn = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_record:

                final String name = nameEditText.getText().toString();
                final String gender = genderEditText.getText().toString();
                final String prodi = prodiEditText.getText().toString();
                final String address = addressEditText.getText().toString();

                dbManager.insert(name, gender, prodi, address );

                Intent main = new Intent(AddCountryActivity.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }
}