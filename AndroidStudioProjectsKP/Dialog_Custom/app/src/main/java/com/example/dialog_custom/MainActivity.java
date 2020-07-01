package com.example.dialog_custom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.awt.font.TextAttribute;

public class MainActivity extends AppCompatActivity {
    TextView txtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLogin = findViewById(R.id.textviewLogin);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLogin();
            }
        });
    }

    private void DialogLogin() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //bỏ phần title ở ô đăng nhập, phải viết trước dòng setcontentView
        dialog.setContentView(R.layout.dialog_custom); //luu y
        dialog.setCanceledOnTouchOutside(false); //chạm ra ngoài vùng đăng nhập thì bảng đăng nhập k bị tắt đi

        //anh xa
        //vi da set dialog. contentView nen khi anh xa id thi phai co dialog.find
        final EditText edtUserName = dialog.findViewById(R.id.editTextUserName);
        final EditText edtPassword = dialog.findViewById(R.id.editTextPassword);
        Button btnDongY = dialog.findViewById(R.id.buttonDongY);
        Button btnHuy = dialog.findViewById(R.id.buttonHuy);

        //bat su kien
        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUserName.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (username.equals("trang") && password.equals("1506")) {
                    Toast.makeText(MainActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Loi dang nhap", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); // tat luon dialog
            }
        });
        dialog.show();
    }
}
