package com.example.affereaflaw.uxdatabase;

/**
 * Created by Affe Reaflaw on 10/6/2017.
 */
public class profilGetSet {
    private String nama, noKamar, kode, image, telp, rs, tanggalMasuk;

    public profilGetSet(String nama, String noKamar, String kode, String image, String telp, String rs, String tanggalMasuk) {
        this.nama = nama;
        this.noKamar = noKamar;
        this.kode = kode;
        this.image = image;
        this.telp = telp;
        this.rs = rs;
        this.tanggalMasuk = tanggalMasuk;
    }

    public profilGetSet(){

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoKamar() {
        return noKamar;
    }

    public void setNoKamar(String noKamar) {
        this.noKamar = noKamar;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }
}
