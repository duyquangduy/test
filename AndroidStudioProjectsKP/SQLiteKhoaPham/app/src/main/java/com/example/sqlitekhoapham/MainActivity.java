package com.example.sqlitekhoapham;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database database;
    ListView listViewCongViec;
    ArrayList<CongViec> arrayCongViec;
    CongViecAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewCongViec = findViewById(R.id.listviewCongViec);  //ánh xạ
        arrayCongViec = new ArrayList<>();

        adapter = new CongViecAdapter(this, R.layout.dong_cong_viec, arrayCongViec);
        listViewCongViec.setAdapter(adapter);

        //tao database ghi chu
        database = new Database(this, "ghichu.sqlite", null, 1);   //truyen vao 4 tham so
        //tao bang CongViec
        database.QuerryData("CREATE TABLE IF NOT EXISTS CongViec ( Id INTEGER PRIMARY KEY AUTOINCREMENT, TenCV VARCHAR(200))");
        // Ví dụ them du lieu vao database
//        database.QuerryData(" INSERT INTO CongViec VALUES(null, 'Bai tap Android')");
//        database.QuerryData(" INSERT INTO CongViec VALUES(null, 'Bai tap Python')");

        //duyet du lieu

//        Cursor dataCongViec = database.GetData("SELECT * FROM CongViec");
//        while (dataCongViec.moveToNext()) {
//            String ten = dataCongViec.getString(1);//lay theo vi tri
//            //Toast.makeText(this, ten , Toast.LENGTH_LONG).show();
//            int id = dataCongViec.getInt(0);
//            arrayCongViec.add(new CongViec(id, ten));
//        }
//        adapter.notifyDataSetChanged();
        GetDataCongViec(); // thêm vào hàm onCreate để chạy
    }

    public void DialogXoaCV(final String tencv, final int id) {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Ban có muốn xóa công việc " + tencv + " không?");
        dialogXoa.setPositiveButton("Có ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QuerryData("DELETE FROM CongViec WHERE Id = ' " + id + "  ' ");
                Toast.makeText(MainActivity.this, "Đã xóa " + tencv, Toast.LENGTH_SHORT).show();
                GetDataCongViec();
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }

    public void DialogSuaCongViec(String ten, final int id) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sua);

        final EditText edtTenCV = dialog.findViewById(R.id.editTextTenCVEdit);
        Button btnXacNhan = dialog.findViewById(R.id.buttonXacNhan);  //phải có dialog. nhé
        Button btnHuy = dialog.findViewById(R.id.buttonHuyEdit);

        edtTenCV.setText(ten); //Hiển thị tên (gồm cả id) của nội dung sửa lên edittext, nên phương thức phải truyền vào 2 biến

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); //đóng layout
            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMoi = edtTenCV.getText().toString().trim(); //trim để bỏ khoảng trắng
                database.QuerryData(" UPDATE CongViec SET TenCV = ' " + tenMoi + " ' WHERE Id = ' " + id + " '   ");
                Toast.makeText(MainActivity.this, "Đã cập nhật", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetDataCongViec();  //refresh lại dũ liệu trước khi đổ lại
            }
        });
        dialog.show();
    }

    private void GetDataCongViec() {    //tao ra ham duyet du lieu rieng để dùng chung thay cho thằng ở trên
        Cursor dataCongViec = database.GetData("SELECT * FROM CongViec");
        arrayCongViec.clear(); //xóa dữ liệu cũ r nạp lại để không bị ghi chồng lại dữ liệu
        while (dataCongViec.moveToNext()) {
            String ten = dataCongViec.getString(1);//lay theo vi tri
            //Toast.makeText(this, ten , Toast.LENGTH_LONG).show();
            int id = dataCongViec.getInt(0);
            arrayCongViec.add(new CongViec(id, ten));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_congviec, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuAdd) {
            DialogThem();
        }
        return super.onOptionsItemSelected(item);
    }

    private void DialogThem() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //bo title di
        dialog.setContentView(R.layout.dialog_them_cong_viec);

        //ánh xạ
        final EditText edtTen = dialog.findViewById(R.id.editTextTenCV);
        final Button btnThem = dialog.findViewById(R.id.buttonThem);  //phải có dialog. nhé
        Button btnHuy = dialog.findViewById(R.id.buttonHuy);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tencv = edtTen.getText().toString();
                if (tencv.equals("")) {  //2 dấu kép phải đề liền nhau chứ không là sai đấy nhé
                    Toast.makeText(MainActivity.this, "Vui long nhập tên công việc", Toast.LENGTH_SHORT).show();
                } else {
                    database.QuerryData(" INSERT INTO CongViec VALUES(null, ' " + tencv + " ')");
                    Toast.makeText(MainActivity.this, "Đã thêm công việc", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetDataCongViec(); //gọi lại hàm duyệt dữ liệu để hiển thị lên danh sách
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); //thoat ra ngoai
            }
        });
        dialog.show();
    }
}
