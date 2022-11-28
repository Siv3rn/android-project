package com.example.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ModifyCountryActivity extends AppCompatActivity implements OnClickListener {
    private Button updateBtn, deleteBtn;
    private EditText nameText;
    private EditText genderText;
    private EditText prodiText;
    private EditText addressText;

    private long _id;

    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modify Record");

        setContentView(R.layout.activity_modify_record);

        dbManager = new DBManager(this);
        dbManager.open();

        nameText = (EditText) findViewById(R.id.name_edittext);
        genderText = (EditText) findViewById(R.id.gender_edittext);
        prodiText = (EditText) findViewById(R.id.prodi_edittext);
        addressText = (EditText) findViewById(R.id.tempat_edittext);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String prodi = intent.getStringExtra("prodi");
        String address = intent.getStringExtra("adress");

        _id = Long.parseLong(id);

        nameText.setText(name);
        genderText.setText(gender);
        prodiText.setText(prodi);
        addressText.setText(address);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update:
                final String name = nameText.getText().toString();
                final String gender = genderText.getText().toString();
                final String prodi = prodiText.getText().toString();
                final String address = addressText.getText().toString();

                dbManager.update(_id, name, gender,prodi,address);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }

    }
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

}