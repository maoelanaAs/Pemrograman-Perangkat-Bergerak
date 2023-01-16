package com.learning.firebasecrud;

import java.util.ArrayList;

public class ModelBarang {

    private String kode_brg, nama_brg, hrg_beli, hrg_jual,stan_brg, stok_brg, stok_min;
    private String key;

    public ModelBarang() {

    }

    public ModelBarang(String kode_brg, String nama_brg, String hrg_beli, String hrg_jual,
                       String stan_brg, String stok_brg, String stok_min) {
        this.kode_brg = kode_brg;
        this.nama_brg = nama_brg;
        this.hrg_beli = hrg_beli;
        this.hrg_jual = hrg_jual;
        this.stan_brg = stan_brg;
        this.stok_brg = stok_brg;
        this.stok_min = stok_min;
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

    public String getStan_brg() {
        return stan_brg;
    }

    public void setStan_brg(String stan_brg) {
        this.stan_brg = stan_brg;
    }

    public String getStok_brg() {
        return stok_brg;
    }

    public void setStok_brg(String stok_brg) {
        this.stok_brg = stok_brg;
    }

    public String getStok_min() {
        return stok_min;
    }

    public void setStok_min(String stok_min) {
        this.stok_min = stok_min;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
