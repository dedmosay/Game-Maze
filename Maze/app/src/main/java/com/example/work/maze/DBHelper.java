package com.example.work.maze;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper  extends SQLiteOpenHelper{

    final String TAG = "lifecycle";

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "DB";
    public static final String TABLE_RECORDS = "recordTable";

    public static final String KEY_ID = "id";
    public static final String KEY_VALUE = "value";

    public DBHelper(Context context) {
        //  конструктор суперкласса
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "-- onCreate database --");
        // создаем таблицу с полями
        db.execSQL("create table " + TABLE_RECORDS + "(" + KEY_ID
                + " integer primary key," + KEY_VALUE + " text" +  ")");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_RECORDS);

        onCreate(db);
    }
}