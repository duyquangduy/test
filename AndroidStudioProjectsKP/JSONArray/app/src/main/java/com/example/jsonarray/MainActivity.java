package com.example.jsonarray;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ReadJSON().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo2.json");
    }

    private class ReadJSON extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);

                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //Phan tich JSON

            try {
                JSONObject object = new JSONObject(s);

                //khai bao jsonArray
                JSONArray array = object.getJSONArray("danhsach");

                //duyet cac mang con trong danh sach
                for (int i = 0; i < array.length(); i++) {
                    JSONObject objectKH = array.getJSONObject(i);
                    String ten = objectKH.getString("khoahoc");
                    Toast.makeText(MainActivity.this, ten, Toast.LENGTH_LONG).show();
                }

               // Toast.makeText(MainActivity.this, " " + array.length(), Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }
}
