package com.example.inteneexplicit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    TextView txtKetQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        txtKetQua = findViewById(R.id.textViewKetQua);

        //nhan du lieu tu intent
        Intent intent = getIntent();

        //String noiDung = intent.getStringExtra("Du lieu"); //name phai chinh xac vs ben name cua main
        // int noiDung2 = intent.getIntExtra("Du lieu2", 1111); //kieu du lieu k giong vs Du lieu2 ben Main thi se lay gia trii 1234 mac dinh

        //String[] mangMonHoc = intent.getStringArrayExtra("Du lieu2");

//        HocSinh hs = (HocSinh) intent.getSerializableExtra("Dulieu");
//        txtKetQua.setText(hs.getHoTen() + " | " + hs.getNamSinh());

        Bundle bundle = intent.getBundleExtra("Dulieu");
        //kiem tra bundle xem co du lieu hay khong
        if (bundle != null) {
            String ten = bundle.getString("chuoi");
            int so = bundle.getInt("conSo", 1506);
            String[] mang = bundle.getStringArray("mangTen");
            HocSinh hocSinh = (HocSinh) bundle.getSerializable("doiTuong");

            txtKetQua.setText(ten + "\n" + so + "\n" + mang[2] + "\n" + hocSinh.getHoTen() + " | " + hocSinh.getNamSinh());
        } else {
            Toast.makeText(this, "Khong co du lieu", Toast.LENGTH_SHORT).show();
        }
    }
}
