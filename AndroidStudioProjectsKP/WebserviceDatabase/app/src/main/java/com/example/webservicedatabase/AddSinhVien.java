package com.example.webservicedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AddSinhVien extends AppCompatActivity {

    EditText edtHoTen, edtNamSinh, edtDiaChi;
    Button btnThem, btnHuy;

    String  urlInsert = "http://192.168.0.104:8888/androidwebservice/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinh_vien);

        AnhXa();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//kiem tra ng dung co nhap vao k
                String hoten = edtHoTen.getText().toString().trim();
                String namsinh = edtNamSinh.getText().toString().trim();
                String diachi = edtDiaChi.getText().toString().trim();
                if (hoten.isEmpty() || namsinh.isEmpty() || diachi.isEmpty()){
                    Toast.makeText(AddSinhVien.this, "Vui long nhap du thong tin", Toast.LENGTH_SHORT).show();
                } else {
                    ThemSinhVien(urlInsert);
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ThemSinhVien(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,  //lay xuong thi la GET, sua r cap nhat thi la POST
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("Success")) {
                            Toast.makeText(AddSinhVien.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddSinhVien.this, MainActivity.class));
                        } else {
                            Toast.makeText(AddSinhVien.this, "Them khong thanh cong", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddSinhVien.this, "Xay ra loi", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError { //goi ham nay de day du lieu len

                Map<String, String> params = new HashMap<>();

                params.put("hotenSV", edtHoTen.getText().toString().trim());   //nhan gia tri o dong POST file php
                params.put("namsinhSV", edtNamSinh.getText().toString().trim());
                params.put("diachiSV", edtDiaChi.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa() {
        edtHoTen = findViewById(R.id.editTextHoTen);
        edtDiaChi = findViewById(R.id.editTextDiaChi);
        edtNamSinh = findViewById(R.id.editTextNamSinh);
        btnThem = findViewById(R.id.buttonThem);
        btnHuy = findViewById(R.id.buttonHuy);
    }
}
