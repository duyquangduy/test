package com.example.jsonarrayobject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvKhoaHoc;
    ArrayList<String > arrayCourse;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvKhoaHoc = findViewById(R.id.listviewKhoaHoc);
        arrayCourse = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayCourse);
        lvKhoaHoc.setAdapter(adapter);

        new ReadJSON().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo4.json");
    }

    private class ReadJSON extends AsyncTask <String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);

                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line ="";

                while((line = bufferedReader.readLine()) != null){
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

            try {
                JSONArray array = new JSONArray(s);

                for (int i = 0; i <array.length() ; i++) {
                    JSONObject object = array.getJSONObject(i);

                    String khoahoc = object.getString("khoahoc");
                    String hocphi = object.getString("hocphi");
                   // Toast.makeText(MainActivity.this, khoahoc+" - "+hocphi, Toast.LENGTH_SHORT).show();

                    arrayCourse.add(khoahoc+" - "+hocphi);
                }
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
