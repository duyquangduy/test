package com.example.readcontentwebsite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ReadContentWebsite().execute("http://khoapham.vn");
    }

    private class ReadContentWebsite extends AsyncTask<String , Void,String >{

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(strings[0]);

                URLConnection urlConnection = url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();

                //Doc du lieu lien tuc
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                //luu moi dong doc du lieu
                String line = "";
                while ( (line = bufferedReader.readLine()) != null ){
                    stringBuilder.append(line +"\n");
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString(); //phai co toString ep ve kieu chuoi
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }
}
