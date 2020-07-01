package com.example.gamechaydua;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtDiem;
    CheckBox cbOne, cbTwo, cbThree;
    SeekBar skOne, skTwo, skThree;
    ImageButton ibtnPlay;
    int soDiem = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Anhxa();

        skOne.setEnabled(false);   //khi dang chay thi k the keo dc seekbar nua
        skTwo.setEnabled(false);
        skThree.setEnabled(false);


        txtDiem.setText(soDiem + "");

        final CountDownTimer countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long millisUntilFinished) {
                // chay toc do random
                int number = 5;
                Random random = new Random();
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three = random.nextInt(number);

                //kiem tra win one
                if (skOne.getProgress() >= skOne.getMax()) {
                    this.cancel();
                    Toast.makeText(MainActivity.this, "Lisa wins", Toast.LENGTH_SHORT).show();
                    ibtnPlay.setVisibility(View.VISIBLE); //cuoc dua ket thuc thi hien lai nut play
                    //kiem tra dat cuoc
                    if (cbOne.isChecked()) {

                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Ban doan dung roi", Toast.LENGTH_SHORT).show();
                    } else {
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this, "Ban doan sai roi", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                    EnableCheckbox();
                }
                //ktra win two
                if (skTwo.getProgress() >= skTwo.getMax()) {
                    this.cancel();
                    Toast.makeText(MainActivity.this, "Nancy wins", Toast.LENGTH_SHORT).show();
                    ibtnPlay.setVisibility(View.VISIBLE);

                    if (cbTwo.isChecked()) {
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Ban doan dung roi", Toast.LENGTH_SHORT).show();
                    } else {
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this, "Ban doan sai roi", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                    EnableCheckbox();
                }
                if (skThree.getProgress() >= skThree.getMax()) {
                    this.cancel();
                    Toast.makeText(MainActivity.this, "Roses wins", Toast.LENGTH_SHORT).show();
                    ibtnPlay.setVisibility(View.VISIBLE);

                    if (cbThree.isChecked()) {
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Ban doan dung roi", Toast.LENGTH_SHORT).show();
                    } else {
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this, "Ban doan sai roi", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                    EnableCheckbox();
                }
                skOne.setProgress(skOne.getProgress() + one);
                skTwo.setProgress(skTwo.getProgress() + two);
                skThree.setProgress(skThree.getProgress() + three);
            }

            @Override
            public void onFinish() {

            }
        };
        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked()) {
                    skOne.setProgress(0); // truoc khi play thi seekbar tro ve 0
                    skTwo.setProgress(0);
                    skThree.setProgress(0);
                    ibtnPlay.setVisibility(View.INVISIBLE); //an nut button khi play
                    countDownTimer.start();
                    DisableCheckbox();
                } else {
                    Toast.makeText(MainActivity.this, "Ban phai dat cuoc truoc", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (b) {
                    cbTwo.setChecked(false);   //khi chon 1 thi 2 voi 3 k dc chon nua
                    cbThree.setChecked(false);
                }
            }
        });
        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (b) {
                    cbOne.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });
        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (b) {
                    cbOne.setChecked(false);
                    cbTwo.setChecked(false);
                }
            }
        });
    }

    private void EnableCheckbox()
    //khi da chon 1 checkbox thi cac checkbox con lai k dc chon nua
    {
        cbOne.setEnabled(true);
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
    }

    private void DisableCheckbox() {
        cbOne.setEnabled(false);
        cbTwo.setEnabled(false);
        cbThree.setEnabled(false);
    }

    private void Anhxa() {
        txtDiem = (TextView) findViewById(R.id.textViewScore);
        ibtnPlay = (ImageButton) findViewById(R.id.buttonPlay);
        cbOne = (CheckBox) findViewById(R.id.checkBox1);
        cbTwo = (CheckBox) findViewById(R.id.checkBox2);
        cbThree = (CheckBox) findViewById(R.id.checkBox3);
        skOne = (SeekBar) findViewById(R.id.seekBar1);
        skTwo = (SeekBar) findViewById(R.id.seekBar2);
        skThree = (SeekBar) findViewById(R.id.seekBar3);
    }
}
