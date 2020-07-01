package com.example.mediaappmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtTitle, txtStart, txtEnd;
    SeekBar seekBarProgress;
    ImageButton btnBack, btnNext, btnStop, btnPlay;
    ImageView imageViewDisc;

    ArrayList<Song> arraySong;
    int position = 0;
    MediaPlayer mediaPlayer;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Anhxa();
        AddSong();
        KhoiTaoMediaPlayer();

        animation = AnimationUtils.loadAnimation(this, R.anim.disc_rotate);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if (position > arraySong.size() - 1) {
                    position = 0;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop(); //khong cho bai hat hat chong len nhau
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause_button1);
                SetTimeTotal();
                UpdateTimeSong();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position < 0) {
                    position = arraySong.size() - 1;  //den bai dau tien back thi sang bai cuoii cung
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop(); //khong cho bai hat hat chong len nhau
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause_button1);
                SetTimeTotal();
                UpdateTimeSong();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                btnPlay.setImageResource(R.drawable.play_button);
                KhoiTaoMediaPlayer();
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    //neu dang phat thi cho pause -> doi hinh play
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play_button);
                } else {
                    //dang ngung thi phat -> doi thanh pause
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause_button1);
                }
                SetTimeTotal();
                UpdateTimeSong();
                imageViewDisc.startAnimation(animation);
            }
        });

        seekBarProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBarProgress.getProgress());
            }
        });
    }

    private void  UpdateTimeSong(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                txtStart.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()));
                //update progreess cuar seekbar
                seekBarProgress.setProgress(mediaPlayer.getCurrentPosition());
                //keim tra tgian bai hat neu ket thuc thi chuyen sang bai khac
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if(position > arraySong.size() - 1) {
                            position = 0;
                        }
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        KhoiTaoMediaPlayer();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.pause_button1);
                        SetTimeTotal();
                        UpdateTimeSong();
                    }
                });

                handler.postDelayed(this, 500);
            }
        }, 100);
    }

    private void SetTimeTotal() {
        SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
        txtEnd.setText(dinhDangGio.format(mediaPlayer.getDuration()));
        //gan max cua seekbar = do dai bai hat
        seekBarProgress.setMax(mediaPlayer.getDuration());  //cai nay quan trong
    }

    private void AddSong() {
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Luyen Nhan Tam", R.raw.luyen_nhan_tam));
        arraySong.add(new Song("Mang Chung", R.raw.mang_chung));
    }

    private void KhoiTaoMediaPlayer() {
        mediaPlayer = MediaPlayer.create(MainActivity.this, arraySong.get(position).getFile());
        txtTitle.setText(arraySong.get(position).getTitle());
    }

    private void Anhxa() {
        txtStart = findViewById(R.id.textViewStart);
        txtEnd = findViewById(R.id.textViewEnd);
        txtTitle = findViewById(R.id.textViewTitle);
        seekBarProgress = findViewById(R.id.seekBarProgress);
        btnBack = findViewById(R.id.imageButtonBack);
        btnNext = findViewById(R.id.imageButtonNext);
        btnStop = findViewById(R.id.imageButtonStop);
        btnPlay = findViewById(R.id.imageButtonPlay);
        imageViewDisc = findViewById(R.id.imageViewDisc);
    }
}
