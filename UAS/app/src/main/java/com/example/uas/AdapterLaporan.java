package com.example.uas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterLaporan extends RecyclerView.Adapter<AdapterLaporan.myViewHolder> {
    private List<ModelLaporan> mList;
    private Activity activity;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();


    public AdapterLaporan(List<ModelLaporan> mList, Activity activity){
        this.mList = mList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_item,parent, false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        final ModelLaporan data = mList.get(position);
        holder.judul.setText("Judul : " + data.getJudul());
        holder.isi.setText("Isi : " + data.getIsi());
        holder.tanggal.setText("Tanggal : " + data.getTanggal());
        holder.lokasi.setText("Lokasi : " + data.getLokasi());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database.child("Laporan").child(data.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(activity, "Success delete data", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activity, "Failed delete data", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                }).setMessage("Are you sure want to delete this report? ");
                builder.show();
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = ((AppCompatActivity)activity).getSupportFragmentManager();
                DialogForm dialog = new DialogForm(
                        data.getJudul(),
                        data.getIsi(),
                        data.getTanggal(),
                        data.getLokasi(),
                        data.getKey(),
                        "change"

                );
                dialog.show(manager,"form");
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView judul,isi,tanggal,lokasi;
        CardView card;
        ImageView delete;
        Button edit;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.item_Judul);
            isi = itemView.findViewById(R.id.item_isi);
            tanggal = itemView.findViewById(R.id.item_tanggal);
            lokasi = itemView.findViewById(R.id.item_lokasi);
            card = itemView.findViewById(R.id.pengaduan);
            delete = itemView.findViewById(R.id.item_delete);
            edit = itemView.findViewById(R.id.item_edit);
        }
    }



}
