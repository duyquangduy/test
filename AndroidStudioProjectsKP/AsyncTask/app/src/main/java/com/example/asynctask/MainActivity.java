package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnXuLy;
    TextView txtThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnXuLy = findViewById(R.id.buttonXuLy);
        txtThongTin = findViewById(R.id.textViewThongTin);

        btnXuLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CongViec().execute();
            }
        });
    }

    private class CongViec extends AsyncTask<Void, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtThongTin.setText("Bat Dau." + "\n");
        }

        @Override
        protected String doInBackground(Void... voids) {
            for (int i = 0; i <=5; i++) {

                //truoc khi chayj tiep thi cho nghi 1 luc
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress("Xong Viec " +i+"\n");
            }
            return "Done. \n";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtThongTin.append(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            txtThongTin.append(values[0]);
        }
    }
}
