package com.example.alphaanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgAlpha, imgRotate,imgScale,imgTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgAlpha = findViewById(R.id.imageViewAlpha);
        imgRotate = findViewById(R.id.imageViewRotate);
        imgScale = findViewById(R.id.imageViewScale);
        imgTranslate = findViewById(R.id.imageViewTranslate);

        final Animation animTranslate =AnimationUtils.loadAnimation(this,R.anim.anim_translate);

        final Animation animScale = AnimationUtils.loadAnimation(this,R.anim.anim_scale);

        final Animation animRotate = AnimationUtils.loadAnimation(this,R.anim.anim_rotate);

        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);

        imgAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
            }
        });

        imgRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animRotate);
            }
        });

        imgScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animScale);
            }
        });

        imgTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animTranslate);
            }
        });
    }
}
