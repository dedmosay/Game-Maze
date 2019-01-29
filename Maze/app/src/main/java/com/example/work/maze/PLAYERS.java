package com.example.work.maze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class PLAYERS extends AppCompatActivity   {

    final String TAG = "lifecycle";

    Button USER;
    Button NORMAL;
    Button HARD;

    private float LevelUser = 5.0f;
    private float LevelNormal = 10.0f;
    private float LevelHard = 20.0f;

    final String FILE_LEVEL = "LevelMaze";
    String levelMaze;
    BufferedReader reader = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        USER = (Button) findViewById(R.id.Id_User);
        NORMAL = (Button) findViewById(R.id.Id_Normal);
        HARD = (Button) findViewById(R.id.Id_Hard);


        View.OnClickListener User = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String LevelUserView = Float.toString(LevelUser);
                levelMaze = LevelUserView;
                writeFile();
            }
        };
        USER.setOnClickListener(User);

        View.OnClickListener Normal = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String LevelNormalView = Float.toString(LevelNormal);
                levelMaze = LevelNormalView;
                writeFile();
            }
        };
        NORMAL.setOnClickListener(Normal);

        View.OnClickListener Hard = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String LevelHardView = Float.toString(LevelHard);
                levelMaze = LevelHardView;
                writeFile();
            }
        };
        HARD.setOnClickListener(Hard);
    }


    void writeFile() {
        final String TAG = "lifecycle";
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(FILE_LEVEL, MODE_PRIVATE)));
            Log.d(TAG, "Файл file - открыт:  " + FILE_LEVEL);
            bw.write(levelMaze);                                                                    // пишем данные
            Log.d(TAG, "Запись файла " + levelMaze);
            bw.close();                                                                             // закрываем поток
            Log.d(TAG, "Файл ЗАКРЫТ ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}

