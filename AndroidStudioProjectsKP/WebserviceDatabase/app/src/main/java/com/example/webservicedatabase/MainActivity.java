package com.example.webservicedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String urlGetData = "http://192.168.0.104:8888/androidwebservice/getdata.php";
    String urlDelete = "http://192.168.0.104:8888/androidwebservice/delete.php";

    ListView lvSinhVien;
    ArrayList<SinhVien> arraySinhVien;
    SinhVienAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSinhVien = findViewById(R.id.listviewSinhVien);
        arraySinhVien = new ArrayList<>();

        adapter = new SinhVienAdapter(this, R.layout.dong_sinh_vien, arraySinhVien);
        lvSinhVien.setAdapter(adapter);

        GetData(urlGetData);
    }

    private void GetData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arraySinhVien.clear();  //phuc vu cho ham Delete sau khi xoa goi lai ham GetData thi khong bi nap them DL
                        //Doc phan tu trong JSON
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                //do du lieu vao mang
                                arraySinhVien.add(new SinhVien(
                                        object.getInt("ID"),
                                        object.getString("HoTen"),
                                        object.getInt("NamSinh"),
                                        object.getString("DiaChi")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Loi", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    public void DeleteStudent(final int idsv) {   //dung public de sang class svAdapter co the goi ra dc
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlDelete,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("Success")) {
                            Toast.makeText(MainActivity.this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                            GetData(urlGetData); //xoa xong phai load lai du lieu
                        } else {
                            Toast.makeText(MainActivity.this, "Loi xoa", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Xay ra loi", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idCuaSinhVien", String.valueOf(idsv));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    //tao menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_student, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //tao menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAddStudent) {
            startActivity(new Intent(MainActivity.this, AddSinhVien.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
