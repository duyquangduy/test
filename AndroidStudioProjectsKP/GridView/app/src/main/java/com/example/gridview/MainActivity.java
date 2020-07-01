package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gvHinhAnh;
    ArrayList<HinhAnh> arrayImage;
    HinhAnhAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Anhxa();

        adapter = new HinhAnhAdapter(this, R.layout.dong_hinh_anh, arrayImage);
        gvHinhAnh.setAdapter(adapter);

        gvHinhAnh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(MainActivity.this, arrayImage.get(i).getTen(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Anhxa() {
        gvHinhAnh = (GridView) findViewById(R.id.gridViewHinhAnh);
        arrayImage = new ArrayList<>();
        arrayImage.add(new HinhAnh("Hinh so 1", R.drawable.and1));
        arrayImage.add(new HinhAnh("Hinh so 2", R.drawable.and2));
        arrayImage.add(new HinhAnh("Hinh so 3", R.drawable.and3));
        arrayImage.add(new HinhAnh("Hinh so 4", R.drawable.and5));
        arrayImage.add(new HinhAnh("Hinh so 5", R.drawable.and6));
        arrayImage.add(new HinhAnh("Hinh so 6", R.drawable.and7));
        arrayImage.add(new HinhAnh("Hinh so 7", R.drawable.and9));
    }
}
