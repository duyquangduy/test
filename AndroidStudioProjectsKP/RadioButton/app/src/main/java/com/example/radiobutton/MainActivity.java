package com.example.radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button btnXacnhan;
    RadioButton rdSang, rdTrua, rdChieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        btnXacnhan = findViewById(R.id.buttonXacnhan);
        rdSang = findViewById(R.id.radioSang);
        rdChieu = findViewById(R.id.radioChieu);
        rdTrua = findViewById(R.id.radioTrua);

        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdSang.isChecked()) {
                    Toast.makeText(MainActivity.this, rdSang.getText(), Toast.LENGTH_LONG).show();
                }
                if (rdTrua.isChecked()) {
                    Toast.makeText(MainActivity.this, rdTrua.getText(), Toast.LENGTH_LONG).show();
                }
                if (rdChieu.isChecked()) {
                    Toast.makeText(MainActivity.this, rdChieu.getText(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Ban phai chon", Toast.LENGTH_LONG).show();
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                //tra id cua radiobutton
                switch (i) {
                    case R.id.radioSang:
                        Toast.makeText(MainActivity.this, "Ban chon sang", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioChieu:
                        Toast.makeText(MainActivity.this, "Ban chon chieu", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioTrua:
                        Toast.makeText(MainActivity.this, "Ban chon trua", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }
}
