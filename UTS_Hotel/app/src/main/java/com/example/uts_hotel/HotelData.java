package com.example.uts_hotel;

public class HotelData {

    private String hotelName;
    private int hotelPrice;
    private int hotelImage;
    private String deskripsi;
    private String fasilitas;

    public HotelData(String hotelName, int hotelPrice, int hotelImage, String deskripsi, String fasilitas) {
        this.hotelName = hotelName;
        this.hotelPrice = hotelPrice;
        this.hotelImage = hotelImage;
        this.deskripsi = deskripsi;
        this.fasilitas = fasilitas;

    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(int hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public int getHotelImage() {
        return hotelImage;
    }

    public void setHotelImage(int hotelImage) {
        this.hotelImage = hotelImage;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
