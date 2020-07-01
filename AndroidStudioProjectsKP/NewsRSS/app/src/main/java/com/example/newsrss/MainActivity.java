package com.example.newsrss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    DocBaoAdapter adapter;
    ArrayList<DocBao> mangDocBao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewTinTuc);
        mangDocBao = new ArrayList<DocBao>();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadData().execute("https://vnexpress.net/rss/du-lich.rss");
                new ReadData().execute("https://vnexpress.net/rss/giai-tri.rss");
                new ReadData().execute("https://vnexpress.net/rss/so-hoa.rss");

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, mangDocBao.get(position).getLink(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, IntentNoiDung.class);
                intent.putExtra("link", mangDocBao.get(position).getLink());
                startActivity(intent);
            }
        });
    }

    private class ReadData extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {

            return docNoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();

            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");   //lay the trung vs ten
            NodeList nodeListDescription = document.getElementsByTagName("description");

            String hinhAnh = "";
            String title = "";
            String link = "";
            for (int i = 0; i < nodeList.getLength(); i++) {
                String cdata = nodeListDescription.item(i +1).getTextContent();  //bo desc dau tien vi no k chua link
                //doc the image
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                if (matcher.find()) {
                    hinhAnh = matcher.group(1);
                }

                Element element = (Element) nodeList.item(i);
                title = parser.getValue(element, "title"); //lam app hoan chinh thi khong duoc += nhe
                link = parser.getValue(element, "link");
                mangDocBao.add(new DocBao(title, link, hinhAnh));
            }
            adapter = new DocBaoAdapter(MainActivity.this, android.R.layout.simple_list_item_1, mangDocBao);
            listView.setAdapter(adapter);
            super.onPostExecute(s);
        }
    }

    private String docNoiDung_Tu_URL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
