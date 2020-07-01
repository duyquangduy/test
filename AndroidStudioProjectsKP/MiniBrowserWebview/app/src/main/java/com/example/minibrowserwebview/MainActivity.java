package com.example.minibrowserwebview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton imgBack, imgNext, imgReload;
    EditText edtSearch;
    Button btnSearch;
    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        webView.setWebViewClient(new WebViewClient()); //doc khong bi vang ra khoi trinh duyet

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = edtSearch.getText().toString().trim(); //trim de bo khoang trang
                webView.loadUrl("http://"+url);
                edtSearch.setText(webView.getUrl());
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(webView.canGoBack()){
                    webView.goBack();
                    edtSearch.setText(webView.getUrl());
                    Toast.makeText(MainActivity.this, "Trang truoc", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Khong co du lieu trang truoc", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(webView.canGoForward()){
                    webView.goForward();
                    edtSearch.setText(webView.getUrl());
                    Toast.makeText(MainActivity.this, "Trang sau", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Khong co du lieu trang sau", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.reload();
                edtSearch.setText(webView.getUrl());
            }
        });

        WebSettings webSettings = webView.getSettings(); //truy cap vao setting cua trang web

        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);  //tat thanh cong cu zoom cua trang web
        webSettings.setJavaScriptEnabled(true); //thuc hien cac chuc nang + xem video trang web
    }

    private void AnhXa() {
        imgBack = findViewById(R.id.imageButtonBack);
        imgNext = findViewById(R.id.imageButtonNext);
        imgReload = findViewById(R.id.imageButtonReload);
        edtSearch = findViewById(R.id.editTextSearch);
        btnSearch = findViewById(R.id.buttonSearch);
        webView = findViewById(R.id.webView);
    }
}
