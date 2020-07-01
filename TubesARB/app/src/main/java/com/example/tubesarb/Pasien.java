package com.example.tubesarb;
public class Pasien {
    public String nama;
    public String tanggalLahir;
    public String alamat;
    public String tanggalMasuk;
    public String deskripsi;
    public String qrcode;

    public Pasien(){
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String toString(){
        return " " +nama+"\n"+
                " "+tanggalLahir+"\n"+
                " "+alamat+"\n"+
                " "+tanggalMasuk+"\n"+
                " "+deskripsi+"\n"+
                qrcode+"\n";
    }
    public Pasien(String nama, String tanggalLahir, String alamat, String tanggalMasuk, String deskripsi, String qrcode) {
        this.nama = nama;
        this.tanggalLahir = tanggalLahir;
        this.alamat = alamat;
        this.tanggalMasuk = tanggalMasuk;
        this.deskripsi = deskripsi;
        this.qrcode = qrcode;
    }

}

