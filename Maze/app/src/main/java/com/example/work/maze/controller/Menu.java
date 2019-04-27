package com.example.work.maze.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.work.maze.R;
import com.example.work.maze.gameplay.Game;
import com.example.work.maze.gameplay.LaptopServer;
import com.example.work.maze.gameplay.ObstacleManager;
import com.example.work.maze.level.LevelHardScore;
import com.example.work.maze.level.LevelNormalScore;
import com.example.work.maze.level.LevelUserScore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.work.maze.controller.DBH.hard;
import static com.example.work.maze.controller.DBH.norm;
import static com.example.work.maze.controller.DBH.user;


public class Menu extends AppCompatActivity implements View.OnClickListener {

    DBH dbh = new DBH();
    final String TAG = "lifecycle";
    static final String FILE_LEVEL = "LevelMaze";
    static String str = "";

    static float levelMaze;

    private float LevelUser = 5.0f;

    Button button;                                                                                  // создаем переменную класса кнопка

    private Button mButtonOpen = null;
    private Button mButtonSend = null;
    private Button mButtonClose = null;
    private LaptopServer mServer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mButtonOpen = (Button) findViewById(R.id.button_open_connection);
        mButtonSend = (Button) findViewById(R.id.button_send_connection);
        mButtonClose = (Button) findViewById(R.id.button_close_connection);


        mButtonSend.setEnabled(false);
        mButtonClose.setEnabled(false);

        mButtonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* создаем объект для работы с сервером*/
                mServer = new LaptopServer();
                /* Открываем соединение. Открытие должно происходить в отдельном потоке от ui */
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mServer.openConnection();
 /*
 устанавливаем активные кнопки для отправки данных и закрытия соедиения. Все данные по обновлению интерфеса должны
 обрабатывается в Ui потоке, а так как мы сейчас находимся в отдельном потоке, нам необходимо вызвать метод runOnUiThread()
 */
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mButtonSend.setEnabled(true);
                                    mButtonClose.setEnabled(true);
                                }
                            });
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                            mServer = null;
                        }
                    }
                }).start();
            }
        });

        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mServer == null) {
                    Log.e(TAG, "Сервер не создан");
                }
                Log.d(TAG, "СОЗДАН СЕРВЕР ");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            /* отправляем на сервер данные */
                            Log.d(TAG, "ОТПРАВКА СООБЩЕНИЯ " + databaseList() );
                            String dataText = " user " + user + "\n norm "+norm + "\n hard " + hard;
                            mServer.sendData(dataText.getBytes());
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }
                    }
                }).start();
            }
        });

        mButtonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Закрываем соединение */
                mServer.closeConnection(); Log.d(TAG, "ЗАКРЫТИЕ СОЕДИНЕНИЯ ");
                /* устанавливаем неактивными кнопки отправки и закрытия */
                mButtonSend.setEnabled(false);
                mButtonClose.setEnabled(false);
            }
        });

        button = (Button) findViewById(R.id.Id_start);                                                  // Нахождение кнопки
        button.setOnClickListener(this);                                                                // И присваем обратчик setOnClickListener

        button = (Button) findViewById(R.id.Id_level);                                                  // Нахождение кнопки
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.Id_records_DB);                                             // Нахождение кнопки
        button.setOnClickListener(this);

        Log.d(TAG, "открыт class Menu");
    }

    void levelReadFile() {
        final String TAG = "lifecycle";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FILE_LEVEL)));
            while ((str = br.readLine()) != null) {
                Log.d(TAG, "Чтение файла " + str);
                float levelFile = Float.parseFloat(str);
                levelMaze = levelFile;

                float levelChoiceRead = 5.0f;

                LevelUserScore.ChoiceLevelRead LeveUserlRead = new LevelUserScore.ChoiceLevelRead();
                LeveUserlRead.setTime_LevelRead(levelMaze);
                Log.d(TAG, "Чтение файла для User levelMaze " + levelMaze);

                LevelNormalScore.ChoiceLevelRead LeveNormalRead = new LevelNormalScore.ChoiceLevelRead();
                LeveNormalRead.setTime_LevelRead(levelMaze);
                Log.d(TAG, "Чтение файла для Normal levelMaze " + levelMaze);

                LevelHardScore.ChoiceLevelRead LevelHardRead = new LevelHardScore.ChoiceLevelRead();
                LevelHardRead.setTime_LevelRead(levelMaze);
                Log.d(TAG, "Чтение файла для Hard levelMaze " + levelMaze);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {                                                                     
            case R.id.Id_start:
                levelReadFile();
                ObstacleManager.ChoiceLevel Level = new ObstacleManager.ChoiceLevel();
                float levelChoice = levelMaze;
                Level.setTime_Level(levelChoice);
                intent = new Intent(this, Game.class);
                startActivity(intent);
                Log.d(TAG, "open START - class GAME");
                break;

            case R.id.Id_level:
                intent = new Intent(this, Players.class);
                startActivity(intent);
                Log.d(TAG, "open PLAYERS - class PLAYERS");
                break;
                
            case R.id.Id_records_DB:
                intent = new Intent(this, DBH.class);
                startActivity(intent);
                Log.d(TAG, "open PLAYERS - class Id_records_DB");
                break;
        }
    }
}
