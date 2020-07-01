package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button btnXacNhan, btnHuy;
    EditText edtEmail, edtPassword;
    TextView tv2,tv3, tv4, tv5, tv6, tv7;
    CheckBox cbRemember;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        //lay gia tri sharedPreferences
        edtEmail.setText(sharedPreferences.getString("taikhoan", ""));
        edtPassword.setText(sharedPreferences.getString("matkhau", ""));
        cbRemember.setChecked(sharedPreferences.getBoolean("checked", false));

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String matkhau = edtPassword.getText().toString();
                KiemTraPassword(matkhau);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                //if (username.equals("quynh trang") && password.equals("1506")) {
                if (KiemTraEmail(edtEmail.getText().toString().trim()) && KiemTraPassword(edtPassword.getText().toString().trim())) {
                    tv7.setTextColor(Color.GREEN);
                    tv7.setText("Địa chỉ Email hợp lệ");
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    //neu co check
                    if (cbRemember.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("taikhoan", username);
                        editor.putString("matkhau", password);
                        editor.putBoolean("checked", true);
                        editor.commit();
                    } else {     //bo luu mat khau thi lan sau dang nhap lai no k nho mat khau nua
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("taikhoan");
                        editor.remove("matkhau");
                        editor.remove("checked");
                        editor.commit();
                    }
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                } else if (!KiemTraEmail(edtEmail.getText().toString().trim())) {
                    tv7.setTextColor(Color.RED);
                    tv7.setText("Địa chỉ Email không hợp lệ");
                    Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                } else if (!KiemTraPassword(edtPassword.getText().toString().trim())) {
                    edtPassword.setError("Mat khau khong dung");
                    Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean KiemTraEmail(String email) {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    private boolean KiemTraPassword(String password) {
        Pattern InThuong = Pattern.compile("[a-z]");
        Pattern InHoa = Pattern.compile("[A-Z]");
        Pattern ChuSo = Pattern.compile("[0-9]");
        Pattern DacBiet = Pattern.compile("[!,@,#,$,%,&,*,(,),|,:,?,*]");

        if (!InThuong.matcher(password).find()) {
            tv2.setTextColor(Color.RED);
            return false;
        } else if (!InHoa.matcher(password).find()) {
            tv3.setTextColor(Color.RED);
            return false;
        } else if (!ChuSo.matcher(password).find()) {
            tv3.setTextColor(Color.RED);
            return false;
        } else if (!DacBiet.matcher(password).find()) {
            tv6.setTextColor(Color.RED);
            return false;
        } else if (password.length() < 6) {
            tv5.setTextColor(Color.RED);
            return false;
        } else {
            tv2.setTextColor(Color.GREEN);
            tv3.setTextColor(Color.GREEN);
            tv4.setTextColor(Color.GREEN);
            tv5.setTextColor(Color.GREEN);
            tv6.setTextColor(Color.GREEN);
            return true;
        }
    }

    private void AnhXa() {
        btnXacNhan = findViewById(R.id.buttonXacNhan);
        btnHuy = findViewById(R.id.buttonHuy);
        edtEmail = findViewById(R.id.editTextUserName);
        edtPassword = findViewById(R.id.editTextPassword);
        tv2= findViewById(R.id.textViewInThuong);
        tv3 = findViewById(R.id.textViewInHoa);
        tv4 = findViewById(R.id.textViewChuSo);
        tv5 = findViewById(R.id.textViewSoKiTu);
        tv6 = findViewById(R.id.textViewDacBiet);
        tv7 = findViewById(R.id.textViewEmail);
        cbRemember = findViewById(R.id.checkBoxRemember);
    }
}
