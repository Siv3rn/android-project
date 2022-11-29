package com.example.firebase;

public class ModelMahasiswa {
    private String nama;
    private String matkul;
    private String alamat;
    private String key;

    public  ModelMahasiswa(){

    }

    public ModelMahasiswa(String nama, String matkul, String alamat) {
        this.nama = nama;
        this.matkul = matkul;
        this.alamat = alamat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
