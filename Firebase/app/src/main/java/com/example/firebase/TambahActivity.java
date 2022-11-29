package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class TambahActivity extends AppCompatActivity {

    EditText nama, matkul, alamat;
    Button simpan;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        nama = (EditText) findViewById(R.id.enter_nama);
        matkul = (EditText) findViewById(R.id.enter_subject);
        alamat = (EditText) findViewById(R.id.enter_address);
        simpan = (Button) findViewById(R.id.btn_simpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getNama = nama.getText().toString();
                String getMatkul = matkul.getText().toString();
                String getAlamat = alamat.getText().toString();

                if (getNama.isEmpty()){
                     nama.setError("enter your name");
                }
                else if (getMatkul.isEmpty()){
                    matkul.setError("enter your subject");

                }
                else if (getAlamat.isEmpty()){
                    alamat.setError("enter your address");

                }
                else{
                    database.child("Mahasiswa").push().setValue(new ModelMahasiswa(getNama,getMatkul,getAlamat)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(TambahActivity.this,"data berhasil dikirim", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(TambahActivity.this,MainActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TambahActivity.this, "data gagal dikirim", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }
}