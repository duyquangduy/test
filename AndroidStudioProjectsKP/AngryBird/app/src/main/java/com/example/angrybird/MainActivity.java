package com.example.angrybird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
//tao mang chua cac hinh trong values-strings.xml

    ArrayList<String> arrayName;
    ImageView imgGoc, imgNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgGoc = findViewById(R.id.imageViewGoc);
        imgNhan = findViewById(R.id.imageViewNhan);

        //khai bao mang string- array
        String[] mangTen = getResources().getStringArray(R.array.list_name);
        arrayName = new ArrayList<>(Arrays.asList(mangTen));

        //xao tron phan tu trong mang de goi ra ngau nhien 1 tam hinh
        Collections.shuffle(arrayName);

        //xac dinh id cua moi tam hinh trong list
        int idHinh = getResources().getIdentifier(arrayName.get(2), "drawable", getPackageName());

        imgGoc.setImageResource(idHinh);
    }
}
