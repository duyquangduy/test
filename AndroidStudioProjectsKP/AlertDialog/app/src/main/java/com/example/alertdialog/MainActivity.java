package com.example.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvName;
    ArrayList<String> arrayName;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvName = findViewById(R.id.listviewName);
        arrayName = new ArrayList<>();
        AddArrayName();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayName);
        lvName.setAdapter(adapter);

        lvName.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                XacNhanXoa(position);
                return false;
            }
        });
    }


    private void XacNhanXoa(final int position) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Thong bao");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage("Ban co muon xoa mon " + arrayName.get(position) + "  khong? ");
        alertDialog.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrayName.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        alertDialog.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();

    }

    private void AddArrayName() {
        arrayName.add("Android");
        arrayName.add("IOS");
        arrayName.add("PHP");
        arrayName.add("Python");
        arrayName.add("Java");
        arrayName.add("NodeIS");
    }
}
