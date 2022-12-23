package com.example.uas;

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
    String judul,isi,tanggal,lokasi,key,select;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public DialogForm(String judul, String isi, String tanggal, String lokasi, String key, String select) {
        this.judul = judul;
        this.isi = isi;
        this.tanggal = tanggal;
        this.lokasi = lokasi;
        this.key = key;
        this.select = select;
    }
    TextView ejudul,eisi,etanggal,elokasi;
    Button add;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_tambah_data,container,false);
        ejudul = view.findViewById(R.id.Tam_judul);
        eisi = view.findViewById(R.id.Tam_isi);
        etanggal = view.findViewById(R.id.Tam_tanggal);
        elokasi = view.findViewById(R.id.Tam_lokasi);
        add = view.findViewById(R.id.Tam_button);

        ejudul.setText(judul);
        eisi.setText(isi);
        etanggal.setText(tanggal);
        elokasi.setText(lokasi);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getJudul = ejudul.getText().toString();
                String getIsi = eisi.getText().toString();
                String getTanggal = etanggal.getText().toString();
                String getLokasi = elokasi.getText().toString();

                if (select.equals("change")){
                    database.child("Laporan").child(key).setValue(new ModelLaporan(getJudul,getIsi,getTanggal,getLokasi)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(view.getContext(), "Success edit data", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(view.getContext(), "Failed edit data", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if(dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
