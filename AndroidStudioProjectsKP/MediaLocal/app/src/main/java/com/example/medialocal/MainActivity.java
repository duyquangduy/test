package com.example.medialocal;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button btnMp3, btnMp4;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMp3 = findViewById(R.id.buttonMp3);
        btnMp4 = findViewById(R.id.buttonmp4);
        videoView = findViewById(R.id.videoViewMp4);

        btnMp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.hoa_khuong));
                videoView.start();
                MediaController mediaController = new MediaController(MainActivity.this); //cua android.widget
                mediaController.setMediaPlayer(videoView);
                videoView.setMediaController(mediaController);
            }
        });

        btnMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.luyen_nhan_tam);
                mediaPlayer.start();
            }
        });
    }
}
