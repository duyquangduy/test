package com.example.sqliteimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnThem;
    ListView lvDoVat;
    ArrayList<DoVat> arrayDoVat;
    DoVatAdapter adapter;
    public static Database database; //khai bao de goi dc tu lop khac

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnThem = findViewById(R.id.buttonThem);
        lvDoVat = findViewById(R.id.listviewDoVat);
        arrayDoVat = new ArrayList<>();

        adapter = new DoVatAdapter(this, R.layout.dong_do_vat, arrayDoVat);
        lvDoVat.setAdapter(adapter);

        database = new Database(this, "QuanLy.sqlite", null,1);

        database.QueryData("CREATE TABLE IF NOT EXISTS DoVat ( Id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "Ten VARCHAR(150), MoTa VARCHAR(250), HinhAnh BLOB ) " );

        //get data
       Cursor cursor =  database.GetData(" SELECT * FROM DoVat ");
       while (cursor.moveToNext()){
           arrayDoVat.add(new DoVat(
                   cursor.getInt(0),
                   cursor.getString(1),
                   cursor.getString(2),
                   cursor.getBlob(3)
           ));
       }

       adapter.notifyDataSetChanged();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bấm nút chuyển từ màn hình chính sang màn hình thêm
                startActivity(new Intent(MainActivity.this, ThemDoVatActivity.class));
            }
        });
    }
}
