package com.example.sqliteimage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ThemDoVatActivity extends AppCompatActivity {

    Button btnAdd, btnCancel;
    ImageButton ibtnCamera, ibtnFolder;
    EditText edtTen, edtMoTa;
    ImageView imgHinh;

    final int REQUEST_CODE_CAMERA = 123;
    final  int REQUEST_CODE_FOLDER = 456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_do_vat);

        AnhXa();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyen data cua imageview => byte
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                //khi bao mang chua du lieu
                byte[ ] hinhAnh = byteArray.toByteArray();

                MainActivity.database.INSERT_DOVAT (
                        edtTen.getText().toString().trim(),
                        edtMoTa.getText().toString().trim(),
                        hinhAnh
                );
                Toast.makeText(ThemDoVatActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ThemDoVatActivity.this, MainActivity.class));
            }
        });

        ibtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cai nay danh cho androi duoi 6.0
//                Intent intent = new Intent((MediaStore.ACTION_IMAGE_CAPTURE));
//                startActivityForResult(intent, REQUEST_CODE_CAMERA);
                ActivityCompat.requestPermissions(
                        ThemDoVatActivity.this,
                        new String[] {Manifest.permission.CAMERA},
                        REQUEST_CODE_CAMERA
                );
            }
        });

        ibtnFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //danh cho android 6.0 tro xuong
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent, REQUEST_CODE_FOLDER);
                ActivityCompat.requestPermissions(
                        ThemDoVatActivity.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_FOLDER
                );
            }
        });
    }

    //ham dong y cho phep truy cap
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_CAMERA:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CODE_CAMERA);
                }else {
                    Toast.makeText(this, "Ban khong cho phep mo camera!", Toast.LENGTH_SHORT).show();
                }
                    break;
            case  REQUEST_CODE_FOLDER:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, REQUEST_CODE_FOLDER);
                } else {
                    Toast.makeText(this, "Ban khong cho phep truy cap thu muc anh", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);
        }

        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgHinh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void AnhXa() {
        btnAdd = findViewById(R.id.buttonAdd);
        btnCancel = findViewById(R.id.buttonHuy);
        edtMoTa = findViewById(R.id.editTextMoTa);
        edtTen = findViewById(R.id.editTextTenDoVat);
        imgHinh = findViewById(R.id.imageViewHinh);
        ibtnCamera = findViewById(R.id.imageButtonCamera);
        ibtnFolder = findViewById(R.id.imageButtonFolder);
    }
}
