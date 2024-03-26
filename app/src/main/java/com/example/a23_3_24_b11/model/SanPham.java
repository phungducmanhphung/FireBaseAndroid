package com.example.a23_3_24_b11.model;

public class SanPham {
    String maSP;

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    String tenSP;
    int giaSP;

    public SanPham() {
    }

    public SanPham(String tenSP, int giaSP) {
        this.tenSP = tenSP;
        this.giaSP = giaSP;
    }
    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }
}
