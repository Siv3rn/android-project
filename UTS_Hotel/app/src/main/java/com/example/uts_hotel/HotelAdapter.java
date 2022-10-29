package com.example.uts_hotel;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder>{
    HotelData[] hotelData;
    Context context;

    public HotelAdapter(HotelData[] hotelData, DaftarHotel activity) {
        this.hotelData = hotelData;
        this.context = activity;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.hotel_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final HotelData hotelDataList = hotelData[i];
        viewHolder.nameHotel.setText(hotelDataList.getHotelName());
        viewHolder.priceHotel.setText(String.valueOf(hotelDataList.getHotelPrice())+"/Malam");
        viewHolder.hotelImage.setImageResource(hotelDataList.getHotelImage());




        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailHotel.class);
                String nama = hotelDataList.getHotelName().toString();
                String deskripsi = hotelDataList.getDeskripsi().toString();
                String fasilitas = hotelDataList.getFasilitas().toString();

                int gambar = hotelDataList.getHotelImage();
                String jumlah = String.valueOf(hotelDataList.getHotelPrice()+"/Malam");
                intent.putExtra("nama", nama);
                intent.putExtra("deskripsi",deskripsi);
                intent.putExtra("fasilitas", fasilitas);
                intent.putExtra("gambar2", gambar);
                intent.putExtra("harga1", jumlah);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotelData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView hotelImage;
        TextView priceHotel;
        TextView nameHotel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelImage = itemView.findViewById(R.id.imageview);
            priceHotel = itemView.findViewById(R.id.hotelPrice);
            nameHotel = itemView.findViewById(R.id.hotelName);

        }
    }
}
