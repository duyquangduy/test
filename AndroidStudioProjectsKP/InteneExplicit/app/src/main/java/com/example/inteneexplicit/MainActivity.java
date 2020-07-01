package com.example.inteneexplicit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = findViewById(R.id.buttonSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                //truyen dang mang
                //String[] arrayCourse = {"Android", "Python","Java","IOS"};
                //intent.putExtra("Du lieu","Noi dung chuoi");  //gui du lieu
                //intent.putExtra("Du lieu2",arrayCourse);

                //truyen dang doi tuong
//                HocSinh hocSinh = new HocSinh("Quynh Trang", 2002);
//                intent.putExtra("Dulieu", hocSinh);

                //truyen dang Bundle
                String[] arrayName = {"Ngoc Huyen", "Van Han", "Duyen Pham", "Quynh Trang"};
                HocSinh hocSinh = new HocSinh("Quang", 1995);

                Bundle bundle = new Bundle();
                bundle.putString("chuoi", "Quynh Trang");
                bundle.putInt("conSo", 15062002);
                bundle.putStringArray("mangTen", arrayName);  //gui kieu mang
                bundle.putSerializable("doiTuong", hocSinh);  //gui kieu doi tuong

                intent.putExtra("Dulieu", bundle);  //bao gom ca 4 cai bundle nho ben tren

                startActivity(intent);
            }
        });

    }
}
