package com.hafidz.fixproject3.ui.gallery;

public class ModelLaka {
    private String ruas;
    private String lokasikejadia;
    private String kejadian;
    private String TanggalKejadian;
    private String Uraianpesan;
    private String key;

    public ModelLaka(){

    }
    public ModelLaka(String ruas, String lokasikejadia, String kejadian, String tanggalKejadian, String uraianpesan) {
        this.ruas = ruas;
        this.lokasikejadia = lokasikejadia;
        this.kejadian = kejadian;
        TanggalKejadian = tanggalKejadian;
        Uraianpesan = uraianpesan;
    }

    public String getRuas() {
        return ruas;
    }

    public void setRuas(String ruas) {
        this.ruas = ruas;
    }

    public String getLokasikejadia() {
        return lokasikejadia;
    }

    public void setLokasikejadia(String lokasikejadia) {
        this.lokasikejadia = lokasikejadia;
    }

    public String getKejadian() {
        return kejadian;
    }

    public void setKejadian(String kejadian) {
        this.kejadian = kejadian;
    }

    public String getTanggalKejadian() {
        return TanggalKejadian;
    }

    public void setTanggalKejadian(String tanggalKejadian) {
        TanggalKejadian = tanggalKejadian;
    }

    public String getUraianpesan() {
        return Uraianpesan;
    }

    public void setUraianpesan(String uraianpesan) {
        Uraianpesan = uraianpesan;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
