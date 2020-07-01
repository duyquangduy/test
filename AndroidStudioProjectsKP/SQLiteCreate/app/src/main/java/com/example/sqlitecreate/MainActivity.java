package com.example.sqlitecreate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.sqlitecreate.db.DBManager;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtAddress, edtPhoneNumber, edtEmail;
    Button btnSave;
    ListView lvStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DBManager dbManager = new DBManager(this);
        //dbManager.hello();
        edtName = findViewById(R.id.edt_name);
        edtAddress = findViewById(R.id.edt_address);
        edtPhoneNumber = findViewById(R.id.edt_number);
        edtEmail = findViewById(R.id.edt_email);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = createStudent();
                if(student != null){
                dbManager.addStudent(student);
            }
            }
        });
    }

    private Student createStudent() {
        String name = edtName.getText().toString();
        String address = String.valueOf(edtAddress.getText());
        String phoneNumber = edtPhoneNumber.getText() + "";
        String email = edtEmail.getText().toString();
        Student student = new Student(name, address, phoneNumber, email);
        return student;
    }
}
