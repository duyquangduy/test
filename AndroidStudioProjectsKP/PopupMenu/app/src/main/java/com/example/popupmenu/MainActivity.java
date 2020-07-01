package com.example.popupmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    Button btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMenu = findViewById(R.id.buttonMenu);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu();
            }
        });
    }

    private  void ShowMenu(){
        PopupMenu popupMenu = new PopupMenu(this, btnMenu);
        //lay layout khi an vao nut menu
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuThem: btnMenu.setText("Menu Them");
                        break;
                    case R.id.menuXoa: btnMenu.setText("Menu Xoa");
                        break;
                    case R.id.menuSua: btnMenu.setText("Menu Sua");
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
}
