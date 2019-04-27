package com.example.work.maze.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.work.maze.R;
import com.example.work.maze.level.LevelHardScore;
import com.example.work.maze.level.LevelNormalScore;
import com.example.work.maze.level.LevelUserScore;

public class DBH extends AppCompatActivity implements View.OnClickListener {
    final String TAG = "lifecycle";

    Button btnClear;
    static String user, norm, hard,       // для чтения

    updId, updValue; // для записи


    TextView tvViewS01, tvViewS02, tvViewS03;

    DBHelper dbHelper;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbh);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        tvViewS01 = (TextView) findViewById(R.id.tvViewS01);
        tvViewS02 = (TextView) findViewById(R.id.tvViewS02);
        tvViewS03 = (TextView) findViewById(R.id.tvViewS03);

        dbHelper = new DBHelper(this);


        createTable();
        readTable();

        Log.d(TAG, "user " + user);
        Log.d(TAG, "norm " + norm);
        Log.d(TAG, "hard " + hard);

        LevelUserScore.ResultUser OutResult = new LevelUserScore.ResultUser();
        LevelNormalScore.ResultNormal NormalOutResult = new LevelNormalScore.ResultNormal();
        LevelHardScore.ResultHard HardOutResult = new LevelHardScore.ResultHard();

        int readUser =Integer.parseInt(user);
        int readNorm =Integer.parseInt(norm);
        int readHard =Integer.parseInt(hard);

        if (readUser < OutResult.dataScoreUser){
            int updIntValue = OutResult.dataScoreUser;
            updValue = Integer.toString(updIntValue);
            updId = "1";
            user = updValue;
            updUsing();
            readTable();
        }
        if (readNorm < NormalOutResult.dataScoreNormal) {
            int updIntValue = NormalOutResult.dataScoreNormal;
            updValue = Integer.toString(updIntValue);
            updId = "2";
            String norm = updValue;
            updUsing();
            readTable();
        }
        if (readHard  < HardOutResult.dataScoreHard){
            int updIntValue = HardOutResult.dataScoreHard;
            updValue = Integer.toString(updIntValue);
            updId = "3";
            String hard = updValue;
            updUsing();
            readTable();
        }
    }

    public void onClick(View v) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (v.getId()) {
            case R.id.btnClear:
            Log.d(TAG, "--- Clear recordTable: ---");
            int clearCount = db.delete("recordTable", null, null);
            Log.d(TAG, "deleted rows count = " + clearCount);
            createTable();
            readTable();
            break;
        }
        dbHelper.close();
    }

    public void createTable() {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d(TAG, "--- createTable in recordTable: ---");
        for (int i = 1; i <= 3; i++) {
            cv.put("id", i);
            cv.put("value", 0);
            long rowID = db.insert("recordTable", null, cv);
        }
        dbHelper.close();
    }

    public void readTable() {
        Log.d(TAG, "--- readTable  recordTable: ---");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("recordTable", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idColIndex = cursor.getColumnIndex("id");
            int nameColIndex = cursor.getColumnIndex("value");
            do {
                Log.d(TAG,
                        "ID = " + cursor.getInt(idColIndex) + ", VALUE = " + cursor.getString(nameColIndex));
                if ((cursor.getInt(idColIndex)) == 1) {tvViewS01.setText(user = cursor.getString(nameColIndex)); }
                if ((cursor.getInt(idColIndex)) == 2) {tvViewS02.setText(norm = cursor.getString(nameColIndex)); }
                if ((cursor.getInt(idColIndex)) == 3) {tvViewS03.setText(hard = cursor.getString(nameColIndex)); }
            } while (cursor.moveToNext());
        } else
            Log.d(TAG, "0 rows");
        cursor.close();
    }

    public void updUsing() {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d(TAG, "--- Update recordTable: ---");
        cv.put("id", updId);
        cv.put("value", updValue);
        int updCount = db.update("recordTable", cv, "id = ?", new String[] { updId });
        Log.d(TAG, "updated rows count = " + updCount);
    }
}
