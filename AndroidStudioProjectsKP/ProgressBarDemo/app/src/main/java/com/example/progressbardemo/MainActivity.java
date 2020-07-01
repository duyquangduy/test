package com.example.progressbardemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar, progressBarCircle;
    TextView txtPhanTram;
    int count = 0;
    int phanTram = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar2);
        progressBarCircle = findViewById(R.id.progressBarCircle);
        txtPhanTram = findViewById(R.id.txtPhanTram);
        CountDownTimer countDownTimer = new CountDownTimer(1000 * 60, 100) {
            @Override
            public void onTick(long l) {  //cu moi 0,1s thi chay vao ham nay
                phanTram++;
                progressBar.setProgress(phanTram);
                txtPhanTram.setText(phanTram +" % ");

            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Het 1 phut", Toast.LENGTH_SHORT).show();
            }
        };
        countDownTimer.start();
//        progressBar.setProgress(40);
//        progressBar.setSecondaryProgress(100);
    }

    public void XuLy(View view) {
        count = count + 10;
        progressBar.setProgress(count);
        Toast.makeText(this, "Progress: " + progressBar.getProgress(), Toast.LENGTH_SHORT).show();
        progressBar.setSecondaryProgress(count + 5);
        if (progressBar.getProgress() >= progressBar.getMax()) {
            progressBar.setProgress(0);
            progressBar.setSecondaryProgress(0);
            count = 0;
        }
    }
}
