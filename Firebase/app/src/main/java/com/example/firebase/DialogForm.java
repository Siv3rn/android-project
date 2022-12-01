package com.example.firebase;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogForm extends DialogFragment {
    String nama;
    String matkul;
    String alamat;
    String key;
    String pilih;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    public DialogForm(String nama, String matkul, String alamat, String key, String pilih) {
        this.nama = nama;
        this.matkul = matkul;
        this.alamat = alamat;
        this.key = key;
        this.pilih = pilih;
    }

    TextView tnama,tmatkul,talamat;
    Button btn_simpan;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_tambah, container, false);
        tnama = view.findViewById(R.id.enter_nama);
        tmatkul = view.findViewById(R.id.enter_subject);
        talamat = view.findViewById(R.id.enter_address);
        btn_simpan = view.findViewById(R.id.btn_simpan);

        tnama.setText(nama);
        tmatkul.setText(matkul);
        talamat.setText(alamat);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String anama = tnama.getText().toString();
                String amatkul = tmatkul.getText().toString();
                String aalamat = talamat.getText().toString();
                    if (pilih.equals("ubah")){
                        database.child("Mahasiswa").child(key).setValue(new ModelMahasiswa(anama, amatkul,aalamat)).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(view.getContext(), "Berhasil Di update", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(view.getContext(), "Gagal Di update", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
            }
        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if(dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
