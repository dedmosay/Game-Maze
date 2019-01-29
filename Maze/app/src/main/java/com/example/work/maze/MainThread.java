package com.example.work.maze;

import android.graphics.Canvas;
import android.view.SurfaceHolder;


public class MainThread extends Thread {                                                            // Основная тема
    public static final int MAX_FPS =30;                                                            // MAX_FPS =30;
    private double averageFPS;                                                                      // средний FPS
    private SurfaceHolder surfaceHolder;                                                            // держатель поверхности
    private GamePanel gamePanel;                                                                    // игровая панель
    private boolean running;                                                                        // бег
    public static Canvas canvas;                                                                    // картина

    public void setRunning(boolean running) {                                                       // задавать  - Бег

        this.running = running;
    }

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {                           // Главная тема  состоит из Holder, Panel
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        long starTime;
        long timeMillis = 1000/MAX_FPS;
        long waitTime;
        int frameCount = 0;//13.02 1 les
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;

        while(running) {
            starTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {e.printStackTrace();}
                }
            }
            timeMillis = (System.nanoTime() - starTime)/1000000;
            waitTime = targetTime - timeMillis;
            try {
                if (waitTime > 0)
                    this.sleep(waitTime);
            } catch (Exception e) {e.printStackTrace();}

            totalTime += System.nanoTime() - starTime;
            frameCount++;
            if(frameCount == MAX_FPS) {
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
            }
        }
    }
}