package com.example.uas;

public class ModelLaporan {
    private String judul;
    private String isi;
    private String tanggal;
    private String lokasi;
    private String key;

    public ModelLaporan(){

    }

    public ModelLaporan(String judul, String isi, String tanggal, String lokasi) {
        this.judul = judul;
        this.isi = isi;
        this.tanggal = tanggal;
        this.lokasi = lokasi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
