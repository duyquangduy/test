package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView txtTime, txtGio;

    EditText edtDate;

    Button btnTinh;
    EditText edtNgay1, edtNgay2;
    TextView txtKetQua;

    Calendar calendarOne, calendarTwo;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTime = findViewById(R.id.textviewTime);

        edtDate = findViewById(R.id.editTextDate);

        btnTinh = findViewById(R.id.buttonTinh);
        edtNgay1 = findViewById(R.id.editTextDateOne);
        edtNgay2 = findViewById(R.id.editTextDateTwo);
        txtKetQua = findViewById(R.id.textViewResult);

        txtGio = findViewById(R.id.textViewTime);

        //chon thu vien java.util
        Calendar calendar = Calendar.getInstance();

//        txtTime.append(calendar.getTime() + "\n");//lay tat ca
//        txtTime.append(calendar.get(Calendar.DATE) + "\n"); //lay ngay
//        txtTime.append(calendar.get(Calendar.MONTH) + "\n"); //lay thang (tinh tu thang 0)
//        txtTime.append(calendar.get(Calendar.YEAR) + "\n");

        SimpleDateFormat dinhDangNgay = new SimpleDateFormat("dd//MM/yyyy"); //VD 12/12/2020
        txtTime.append(dinhDangNgay.format(calendar.getTime()) + "\n");

        txtTime.append(calendar.get(Calendar.HOUR) + "\n"); //hour of day tra ve dinh dang 24h

        SimpleDateFormat dinhDangGio = new SimpleDateFormat("hh:mm:ss a"); // them a de dinh dang AM PM
        txtTime.append(dinhDangGio.format(calendar.getTime()) + "\n");

        //Date picker
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay();
            }
        });

        //dem ngay xa em
        edtNgay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay1();
            }
        });

        edtNgay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay2();
            }
        });

        //Tinh khoang cach 2 ngay
        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ngayXaNhau = (int) ((calendarTwo.getTimeInMillis() - calendarOne.getTimeInMillis()) / (1000 * 60 * 60 * 24));
                if (ngayXaNhau < 0) {
                    Toast.makeText(MainActivity.this, "Vui long chon dung ngay", Toast.LENGTH_SHORT).show();
                } else {
                    txtKetQua.setText("So ngay xa nhau la: " + ngayXaNhau);
                }
            }
        });

        //Chon Giờ
        txtGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonGio();
            }
        });
    }

    private void ChonNgay() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth); //gan gia tri do nguoi dung chon
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy"); //do ra tgian ngay thang nam
                edtDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    //Dem ngay xa em
    private void ChonNgay1() {
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        calendarOne = Calendar.getInstance();
        int ngay = calendarOne.get(Calendar.DATE);
        int thang = calendarOne.get(Calendar.MONTH);
        int nam = calendarOne.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarOne.set(year, month, dayOfMonth);
                edtNgay1.setText(simpleDateFormat.format(calendarOne.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private void ChonNgay2() {
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        calendarTwo = Calendar.getInstance();
        int ngay = calendarTwo.get(Calendar.DATE);
        int thang = calendarTwo.get(Calendar.MONTH);
        int nam = calendarTwo.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarTwo.set(year, month, dayOfMonth);
                edtNgay2.setText(simpleDateFormat.format(calendarTwo.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    //TimePicker
    private void ChonGio() {
        final Calendar calendar = Calendar.getInstance();
        int gio = calendar.get(Calendar.HOUR_OF_DAY);
        int phut = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                calendar.set(0, 0, 0, hourOfDay, minute);
                txtGio.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, gio, phut, true);
        timePickerDialog.show();
    }
}
