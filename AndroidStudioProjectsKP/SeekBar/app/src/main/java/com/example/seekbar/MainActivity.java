package com.example.seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SeekBar skSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        skSound = (SeekBar) findViewById(R.id.seekBarSound);
        skSound.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //i: gia tri cua seekbar
                Log.d("AAA", "Gia tri" + i);
                
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("AAA", "Start");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("AAA", "Stop");
            }
        });
    }
}
