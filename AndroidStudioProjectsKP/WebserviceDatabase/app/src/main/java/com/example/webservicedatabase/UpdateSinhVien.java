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

import java.util.HashMap;
import java.util.Map;

public class UpdateSinhVien extends AppCompatActivity {

    EditText edtHoTen, edtDiaChi, edtNamSinh;
    Button btnCapNhat, btnHuy;
    int id = 0;
    String urlUpdate = "http://192.168.0.104:8888/androidwebservice/update.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sinh_vien);

        Intent intent = getIntent();
        SinhVien sinhVien = (SinhVien) intent.getSerializableExtra("dataSinhVien");
        //Toast.makeText(this, sinhVien.getHoTen(), Toast.LENGTH_SHORT).show();

        AnhXa();
        id = sinhVien.getId();
        edtHoTen.setText(sinhVien.getHoTen());
        edtNamSinh.setText(sinhVien.getNamSinh() + "");
        edtDiaChi.setText(sinhVien.getDiaChi());

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten = edtHoTen.getText().toString().trim();
                String namsinh = edtNamSinh.getText().toString().trim();
                String diachi = edtDiaChi.getText().toString().trim();
                if (hoten.matches("") || namsinh.equals("") || diachi.length() == 0) {
                    Toast.makeText(UpdateSinhVien.this, "Moi nhap du thong tin", Toast.LENGTH_SHORT).show();
                } else {
                    UpdateSinhVien(urlUpdate);
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

    private void UpdateSinhVien(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,  //lay xuong thi la GET, sua r cap nhat thi la POST
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("Success")) {
                            Toast.makeText(UpdateSinhVien.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateSinhVien.this, MainActivity.class));
                        } else {
                            Toast.makeText(UpdateSinhVien.this, "Them khong thanh cong", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateSinhVien.this, "Xay ra loi", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError { //goi ham nay de day du lieu len

                Map<String, String> params = new HashMap<>();

                params.put("idSV", String.valueOf(id));
                params.put("hotenSV", edtHoTen.getText().toString().trim());   //nhan gia tri o dong POST file php
                params.put("namsinhSV", edtNamSinh.getText().toString().trim());
                params.put("diachiSV", edtDiaChi.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa() {
        btnCapNhat = findViewById(R.id.buttonSua);
        btnHuy = findViewById(R.id.buttonExit);
        edtDiaChi = findViewById(R.id.editTextSuaDiaChi);
        edtHoTen = findViewById(R.id.editTextSuaHoTen);
        edtNamSinh = findViewById(R.id.editTextSuaNgaySinh);
    }
}
