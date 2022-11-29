package com.example.firebase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterMahasiswa extends RecyclerView.Adapter<AdapterMahasiswa.myViewHolder> {


    private List<ModelMahasiswa> mList;
    private Activity activity;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public AdapterMahasiswa(List<ModelMahasiswa> mList, Activity activity){
     this.mList = mList;
     this.activity = activity;
    }
    @NonNull
    @Override
    public AdapterMahasiswa.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.layout_item, parent, false);
        return new myViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMahasiswa.myViewHolder holder, int position) {
            final ModelMahasiswa data = mList.get(position);
            holder.tv_nama.setText("nama :" + data.getNama());
            holder.tv_matkul.setText("nama :" + data.getMatkul());
            holder.tv_alamat.setText("nama :" + data.getAlamat());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nama,tv_matkul,tv_alamat;
        CardView cardView;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_matkul = itemView.findViewById(R.id.tv_maktul);
            tv_alamat = itemView.findViewById(R.id.tv_alamat);
            cardView = itemView.findViewById(R.id.card_hasil);
        }
    }

}
