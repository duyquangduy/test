package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvMonHoc;
    Button btnThem, btnCapNhat;
    EditText edtMonHoc;
    ArrayList<String> arrayCourse;

    int vitri = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMonHoc = (ListView) findViewById(R.id.listviewMonHoc);
        btnCapNhat = (Button) findViewById(R.id.buttonCapNhat);
        btnThem = (Button) findViewById(R.id.buttonThem);
        edtMonHoc = (EditText) findViewById(R.id.editTextMonHoc);
        arrayCourse = new ArrayList<>();

        arrayCourse.add("Android");
        arrayCourse.add("PHP");
        arrayCourse.add("IOS");
        arrayCourse.add("Unity");
        arrayCourse.add("ASP");

        final ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayCourse
        );
        lvMonHoc.setAdapter(adapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monhoc = edtMonHoc.getText().toString(); //lay noi dung
                arrayCourse.add(monhoc); //add vao mang
                adapter.notifyDataSetChanged(); //cap nhat lai du lieu
            }
        });

        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                edtMonHoc.setText(arrayCourse.get(i));
                vitri = i;
            }
        });

        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                //i tra ve vi tri tren listview
                Toast.makeText(MainActivity.this, arrayCourse.get(i), Toast.LENGTH_SHORT).show();
            }
        });
        lvMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Long Click" + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayCourse.set(vitri, edtMonHoc.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        lvMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {

                arrayCourse.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}
