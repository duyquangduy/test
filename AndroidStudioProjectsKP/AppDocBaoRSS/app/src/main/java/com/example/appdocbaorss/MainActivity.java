package com.example.appdocbaorss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NameList;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvTieuDe;
    ArrayList<String> arrayTitle, arrayLink;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTieuDe = findViewById(R.id.listViewTieuDe);
        arrayTitle = new ArrayList<>();
        arrayLink = new ArrayList<>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayTitle);
        lvTieuDe.setAdapter(adapter);

        new ReadRSS().execute("https://vnexpress.net/rss/giai-tri.rss");

        lvTieuDe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("linkTinTuc", arrayLink.get(position));
                startActivity(intent);
                //Toast.makeText(MainActivity.this, arrayLink.get(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    private class ReadRSS extends AsyncTask<String, Void, String> {

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

            XMLDOMParser parser = new XMLDOMParser();

            Document document = parser.getDocument(s);

            NodeList nodeList = document.getElementsByTagName("item");

            String tieuDe = "";

            for (int i = 0; i < nodeList.getLength(); i++) {
                //doc phia trong moi item
                Element element = (Element) nodeList.item(i);
                tieuDe = parser.getValue(element, "title");   //doc tieu de
                //add tieu de vao mang
                arrayTitle.add(tieuDe);
                arrayLink.add(parser.getValue(element, "link"));
            }
            adapter.notifyDataSetChanged();
            //Toast.makeText(MainActivity.this, tieuDe, Toast.LENGTH_LONG).show();

//            Toast.makeText(MainActivity.this, "Item: "+nodeList.getLength(), Toast.LENGTH_LONG).show();
        }
    }
}
