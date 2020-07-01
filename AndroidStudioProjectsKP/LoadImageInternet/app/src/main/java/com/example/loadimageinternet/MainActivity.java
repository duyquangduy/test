package com.example.loadimageinternet;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

   Button btnLoad;
   ImageView imgHinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoad = findViewById(R.id.buttonLoad);
        imgHinh = findViewById(R.id.imageViewHinh);

        //truoc khi an phai ket noi internet nhe hihi
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //truoc khi lam phai xin quyen trong manifest
                new LoadImageInternet().execute("https://kenh14cdn.com/2019/8/30/mistreatedrose-15671559818251218589719.jpg");
            }
        });
    }

    private  class LoadImageInternet extends AsyncTask<String, Void, Bitmap>{
        Bitmap bitmapHinh = null;

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);

                InputStream inputStream = url.openConnection().getInputStream();

                bitmapHinh = BitmapFactory.decodeStream(inputStream);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmapHinh;
        }

        //load xong thi do ra

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgHinh.setImageBitmap(bitmap);
        }
    }
}
