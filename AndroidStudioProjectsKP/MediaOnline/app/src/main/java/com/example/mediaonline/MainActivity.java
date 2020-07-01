package com.example.mediaonline;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnMp3, btnMp4;
    ProgressBar pbLoading;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMp3 = findViewById(R.id.buttonMp3);
        btnMp4 = findViewById(R.id.buttonMp4);
        videoView = findViewById(R.id.videoViewMp4);
        pbLoading = findViewById(R.id.progressBarLoading);
        pbLoading.setVisibility(View.GONE);

        btnMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://khoapham.vn/download/vietnamoi.mp3";
                final MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepareAsync();
                    //hien thi diaglog loading
                    pbLoading.setVisibility(View.VISIBLE);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            pbLoading.setVisibility(View.GONE);
                            mp.start();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnMp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://khoapham.vn/download/vuoncaovietnam.mp4");
                videoView.setVideoURI(uri);
                videoView.setMediaController(new MediaController(MainActivity.this));
                videoView.start();
            }
        });
    }
}
