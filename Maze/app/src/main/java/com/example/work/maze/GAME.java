
package com.example.work.maze;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.WindowManager;
import android.graphics.Canvas;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class GAME extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {                                            // Создание состояние сохраненного экземпляра
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Получает Менеджер окон
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);                                         // функция окна запроса

        DisplayMetrics dm = new DisplayMetrics();                                                   // Показатели отображения
        getWindowManager().getDefaultDisplay().getMetrics(dm);                                      // Получение отображение по умолчанию
        Constants.SCREEN_WIDTH = dm.widthPixels;                                                    // Константы ШИРИНА экрана(пикселей);
        Constants.SCREEN_HEIGHT = dm.heightPixels;                                                  // Константы ВЫСОТА экрана(пикселей);

        setContentView(new GamePanel(this));                                                // Задает содержание просмотра игровой панели
    }
}

