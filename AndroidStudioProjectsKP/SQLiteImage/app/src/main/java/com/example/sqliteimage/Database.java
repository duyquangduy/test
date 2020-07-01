package com.example.sqliteimage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //truy van k tra ve
    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
//truy van tra ve
    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
    return database.rawQuery(sql, null);
    }

    public void INSERT_DOVAT(String ten, String mota, byte[] hinh) {
        SQLiteDatabase database = getWritableDatabase();
        String  sql = " INSERT INTO DoVat VALUES ( null, ?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, ten); //index la so thu tu can bind trong chuoi du lieu
        statement.bindString(2,mota);
        statement.bindBlob(3, hinh);

        statement.executeInsert();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
