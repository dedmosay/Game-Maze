package com.example.work.maze.gameplay;
//import android.view.SurfaceView;
// import android.support.annotation.MainThread;
//import android.view.Surface;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
/**
 *  Настройка поверхности
 *  Основная тема
 *  Менеджер сцен
 *  Получение обратного вызова
 *
 * Created by work on 21.03.2018.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {                      // Настройка поверхности
    private MainThread thread;                                                                      // Основная тема

    private SceneManager manager;                                                                   // Менеджер сцен

    public GamePanel(Context context){
        super (context);

        getHolder().addCallback(this);                                                              // Получение владельцем, добавление обратного вызова

        Constants.CURRENT_COTEXT = context;

        thread = new MainThread(getHolder(), this);

        manager = new SceneManager();

        setFocusable(true);                                                                         // установить фокус
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int wight, int heidht){            // Настрока поверхности

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){                                               // Поверхность создана
        thread = new MainThread(getHolder(), this);                                       // Основная тема
        Constants.INIT_TIME = System.currentTimeMillis();
        thread.setRunning(true);                                                                    // БЕГ
        thread.start();                                                                             // Старт!
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){                                             // разрушена поверхность (Обработка исключений)
        boolean retry = true;
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
            }catch(Exception e){e.printStackTrace();}                                               // Исключение для вывода трассировки стека
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {                                                // на сенсорном событии (Событие движения)

        manager.recieveToutch(event);                                                               // получение сенсорного события

        return true;
        //return  super.onTouchEvent(event);
    }

    public  void update() {
        manager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        manager.draw(canvas);
    }
}