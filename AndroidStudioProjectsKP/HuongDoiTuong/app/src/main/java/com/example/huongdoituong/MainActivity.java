package com.example.huongdoituong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HuongDoiTuong1 sinhVien = new HuongDoiTuong1("Duy Quang", "Ha Noi", 1995);

//        sinhVien.HoTen = "Dao Duy Quang";
//        sinhVien.DiaChi = "HaNoi";
//        //sinhVien.NamSinh = 1995;
//        sinhVien.SetNamSinh(2017);

        // HuongDoiTuong1 sinhVien2 = new HuongDoiTuong1();
        // sinhVien2.HoTen = "VU NGuyen Quynh Trang";

        Toast.makeText(this, sinhVien.getHoTen(), Toast.LENGTH_SHORT).show();
    }
}


