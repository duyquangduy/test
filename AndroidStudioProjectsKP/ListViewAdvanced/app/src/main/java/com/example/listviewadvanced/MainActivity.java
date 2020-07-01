package com.example.listviewadvanced;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvTraiCay;
    ArrayList<TraiCay> arrayTraiCay;
    TraiCayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Anhxa();

        adapter = new TraiCayAdapter(this, R.layout.dong_trai_cay, arrayTraiCay);
        lvTraiCay.setAdapter(adapter);
    }

    private void Anhxa() {
        lvTraiCay = (ListView) findViewById(R.id.listViewTraiCay);
        arrayTraiCay = new ArrayList<>();

        arrayTraiCay.add(new TraiCay("Xoai", "Xoai thai lan", R.drawable.mango));
        arrayTraiCay.add(new TraiCay("Dua hau", "Dua hau giai cuu", R.drawable.melon));
        arrayTraiCay.add(new TraiCay("Dua", "Dua sieu ngot", R.drawable.pine));
        arrayTraiCay.add(new TraiCay("Xoai xanh", "Xoai xanh thai lan", R.drawable.green_mango));
        arrayTraiCay.add(new TraiCay("Tao", "Tao usa", R.drawable.apple));
        arrayTraiCay.add(new TraiCay("Xoai", "Xoai thai lan", R.drawable.mango));
        arrayTraiCay.add(new TraiCay("Dua hau", "Dua hau giai cuu", R.drawable.melon));
        arrayTraiCay.add(new TraiCay("Dua", "Dua sieu ngot", R.drawable.pine));
        arrayTraiCay.add(new TraiCay("Xoai xanh", "Xoai xanh thai lan", R.drawable.green_mango));
        arrayTraiCay.add(new TraiCay("Tao", "Tao usa", R.drawable.apple));
        arrayTraiCay.add(new TraiCay("Xoai", "Xoai thai lan", R.drawable.mango));
        arrayTraiCay.add(new TraiCay("Dua hau", "Dua hau giai cuu", R.drawable.melon));
        arrayTraiCay.add(new TraiCay("Dua", "Dua sieu ngot", R.drawable.pine));
        arrayTraiCay.add(new TraiCay("Xoai xanh", "Xoai xanh thai lan", R.drawable.green_mango));
        arrayTraiCay.add(new TraiCay("Tao", "Tao usa", R.drawable.apple));
        arrayTraiCay.add(new TraiCay("Xoai", "Xoai thai lan", R.drawable.mango));
        arrayTraiCay.add(new TraiCay("Dua hau", "Dua hau giai cuu", R.drawable.melon));
        arrayTraiCay.add(new TraiCay("Dua", "Dua sieu ngot", R.drawable.pine));
        arrayTraiCay.add(new TraiCay("Xoai xanh", "Xoai xanh thai lan", R.drawable.green_mango));
        arrayTraiCay.add(new TraiCay("Tao", "Tao usa", R.drawable.apple));
    }
}
