package com.example.jsonobjectlanguage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageButton ibtnVN, ibtnEU;
    TextView txtNoiDung;

    String noiDung = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        new ReadJSON().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo3.json");

        ibtnEU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadJSONLanguage("en");
            }
        });

        ibtnVN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadJSONLanguage("vn");
            }
        });
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

            noiDung = s;

            //ReadJSONLanguage("vn");  //mo len mac dinh se la tieng viet

//            try {
//                JSONObject object = new JSONObject(s);
//
//                //mang nam trong mang thi goi tung cai mot
//                JSONObject objectLanguage = object.getJSONObject("language");
//
//                JSONObject objectVN = objectLanguage.getJSONObject("vn");
//
//                String ten = objectVN.getString("name");
//                String diachi = objectVN.getString("address");
//                String khoahoc1 = objectVN.getString("course1");
//                String khoahoc2 = objectVN.getString("course2");
//                String khoahoc3 = objectVN.getString("course3");
//
//                txtNoiDung.setText(ten + "\n" + diachi + "\n" + khoahoc1 + "\n" + khoahoc2 + "\n" + khoahoc3);
//
//                //Toast.makeText(MainActivity.this, ten, Toast.LENGTH_SHORT).show();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        }
    }

    private void ReadJSONLanguage(String lang){  //truyen vao tham so de nguoi dung chon vn hoac En
        try {
            JSONObject object = new JSONObject(noiDung);

            //mang nam trong mang thi goi tung cai mot
            JSONObject objectLanguage = object.getJSONObject("language");

            JSONObject objectVN = objectLanguage.getJSONObject(lang);

            String ten = objectVN.getString("name");
            String diachi = objectVN.getString("address");
            String khoahoc1 = objectVN.getString("course1");
            String khoahoc2 = objectVN.getString("course2");
            String khoahoc3 = objectVN.getString("course3");

            txtNoiDung.setText(ten + "\n" + diachi + "\n" + khoahoc1 + "\n" + khoahoc2 + "\n" + khoahoc3);

            //Toast.makeText(MainActivity.this, ten, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void AnhXa() {
        ibtnVN = findViewById(R.id.imageButtonVn);
        ibtnEU = findViewById(R.id.imageButtonEn);
        txtNoiDung = findViewById(R.id.textViewInfo);
    }
}
