package com.example.inteneexplicit;

import java.io.Serializable;

public class HocSinh implements Serializable {
    private String HoTen;
    private int NamSinh;

    public HocSinh() {
    }

    public HocSinh(String hoTen, int namSinh) {
        HoTen = hoTen;
        NamSinh = namSinh;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public int getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(int namSinh) {
        NamSinh = namSinh;
    }
}
