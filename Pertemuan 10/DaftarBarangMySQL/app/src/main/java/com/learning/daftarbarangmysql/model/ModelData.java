package com.learning.daftarbarangmysql.model;

public class ModelData {
    String kode_brg, nama_brg, hrg_beli, hrg_jual, stok_brg;

    public ModelData() {
    }

    public ModelData(String kode_brg, String nama_brg, String hrg_beli, String hrg_jual, String stok_brg) {
        this.kode_brg = kode_brg;
        this.nama_brg = nama_brg;
        this.hrg_beli = hrg_beli;
        this.hrg_jual = hrg_jual;
        this.stok_brg = stok_brg;
    }

    public String getKode_brg() {
        return kode_brg;
    }

    public void setKode_brg(String kode_brg) {
        this.kode_brg = kode_brg;
    }

    public String getNama_brg() {
        return nama_brg;
    }

    public void setNama_brg(String nama_brg) {
        this.nama_brg = nama_brg;
    }

    public String getHrg_beli() {
        return hrg_beli;
    }

    public void setHrg_beli(String hrg_beli) {
        this.hrg_beli = hrg_beli;
    }

    public String getHrg_jual() {
        return hrg_jual;
    }

    public void setHrg_jual(String hrg_jual) {
        this.hrg_jual = hrg_jual;
    }

    public String getStok_brg() {
        return stok_brg;
    }

    public void setStok_brg(String stok_brg) {
        this.stok_brg = stok_brg;
    }
}
