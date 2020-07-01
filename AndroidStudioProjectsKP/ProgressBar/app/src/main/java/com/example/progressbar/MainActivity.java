package com.example.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    Button btnDownload;
    ProgressBar pbDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDownload = (Button) findViewById(R.id.buttonDown);
        pbDown = (ProgressBar) findViewById(R.id.progressBar1);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = pbDown.getProgress();
                if(current >= pbDown.getMax())
                {
                    current = 0;
                }
                pbDown.setProgress(current + 10);
            }
        });
    }
}
