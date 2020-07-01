package com.example.intentdataresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    Button btnConfirm;
    EditText edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnConfirm = findViewById(R.id.buttonConfirm);
        edtName = findViewById(R.id.editTextName);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenmoi = edtName.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("tenmoi", tenmoi);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
