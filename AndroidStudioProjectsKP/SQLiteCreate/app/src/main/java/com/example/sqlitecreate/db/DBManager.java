package com.example.sqlitecreate.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.sqlitecreate.Student;

public class DBManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "students_manager";
    private static final String TABLE_NAME = "students";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String PHONE_NUMBER = "phone";
    private static final String EMAIL = "email";
    private static int VERSION = 1;
    private final String  Tag = "DBManager";


    private Context context;
    private String SQLQuerry = "CREATE TABLE " + TABLE_NAME + " ( " +
            ID + " integer primary key, " +
            NAME + " TEXT, " +
            EMAIL + " TEXT, " +
            PHONE_NUMBER + " TEXT, " +
            ADDRESS + " TEXT) ";

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
        Log.d(Tag,"DBManager: ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuerry);
        Log.d(Tag,"onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(Tag,"onUpgrade: ");
    }
//    public void hello(){
//        Toast.makeText(context, "Hello" , Toast.LENGTH_LONG).show();
//    }

    public void addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,student.getmName());
        values.put(ADDRESS,student.getmAddress());
        values.put(PHONE_NUMBER,student.getmPhoneNumber());
        values.put(EMAIL,student.getmEmail());
        db.insert(TABLE_NAME,null,values);
        db.close();
        Log.d(Tag,"addStudent Successfull: ");
    }
}
