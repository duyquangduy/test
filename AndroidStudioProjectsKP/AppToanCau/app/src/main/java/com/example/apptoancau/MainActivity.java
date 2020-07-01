package com.example.apptoancau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


//Xử lý ngôn ngữ trong values - strings
public class MainActivity extends AppCompatActivity {

    TextView textViewThongTin;
    Button buttonXacNhan;
    EditText editTextHoTen, editTextEmail, editTextSoDienThoai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        //bat su kien
        buttonXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten = editTextHoTen.getText().toString();
                String email = editTextEmail.getText().toString();
                String soDT = editTextSoDienThoai.getText().toString();

                String textChaoBan = getResources().getString(R.string.text_ChaoBan);
                String textEmail = getResources().getString(R.string.text_Email);
                String textSoDT = getResources().getString(R.string.text_SoDT);

                textViewThongTin.setText(textChaoBan + ": " + hoten + "\n " + textEmail + ": " + email + "\n" + textSoDT + ": " + soDT);
            }
        });
    }

    private void AnhXa() {
        buttonXacNhan = findViewById(R.id.buttonXacNhan);
        textViewThongTin = findViewById(R.id.textViewThongTin);
        editTextHoTen = findViewById(R.id.editTextHoTen);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSoDienThoai = findViewById(R.id.editTextSoDienThoai);
    }
}
