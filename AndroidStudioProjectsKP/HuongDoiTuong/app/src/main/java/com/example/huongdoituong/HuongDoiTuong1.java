package com.example.huongdoituong;

public class HuongDoiTuong1 {
    private String HoTen;
    private String DiaChi;
    private int NamSinh;

    public HuongDoiTuong1(String hoTen, String diaChi, int namSinh) {
        HoTen = hoTen;
        DiaChi = diaChi;
        NamSinh = namSinh;
    }

    // Getter and Setter
    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public int getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(int namSinh) {
        NamSinh = namSinh;
    }

    //    public void SetNamSinh(int ns) {
//        if (ns > 1999) {
//            ns = 1999;
//        }
//        NamSinh = ns;
//    }
//
//    public int GetNamSinh() {
//        return NamSinh;
//    }
}
