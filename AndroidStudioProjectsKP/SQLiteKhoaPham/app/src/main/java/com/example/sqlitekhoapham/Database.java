package com.example.sqlitekhoapham;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //truy van khong tra ket qua: CREATE, INSERT, UPDATE...

    public  void QuerryData(String  sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    //truy van tra ket qua: SELECT
    public Cursor GetData(String  sql){
        SQLiteDatabase  database = getReadableDatabase();
        return database.rawQuery(sql, null);  //tra ve con tro
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
