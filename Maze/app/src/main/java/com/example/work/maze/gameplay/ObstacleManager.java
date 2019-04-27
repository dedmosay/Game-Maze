package com.example.work.maze.gameplay;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.work.maze.level.LevelHardScore;
import com.example.work.maze.level.LevelNormalScore;
import com.example.work.maze.level.LevelUserScore;

import java.util.ArrayList;

/**
 * Менеджер по препятствиям
 *
 *
 * Created by work on 26.03.2018.
 */
public class ObstacleManager {

    final String TAG = "lifecycle";

    //higher index = lower on screen = higher y value
    private ArrayList<Obstacle> obstacles;
    private int playerGap;                                                                          // игрок разрыв
    private int obstacleGap;                                                                        // препятствие разрыв
    private int obstacleHeight;                                                                     // высота препятствия
    private int color;                                                                              // цвет

    private long startTime; //private float elapsedTime;
    private long initTime;

    private int score = 0;
    private int scoreUser = 0;
    private int scoreNormal = 0;
    private int scoreHard = 0;

    static float levelChoice;

    //private static float time_level;

    static float LevelUser;
    static float LevelNormal;
    static float LevelHard;

    public ObstacleManager(int playerGap, int obstacleGap, int obstacleHeight, int color){
        //this.result = result;
        this.playerGap = playerGap;
        this.obstacleGap = obstacleGap;
        this.obstacleHeight = obstacleHeight;
        this.color = color;

        startTime = initTime =System.currentTimeMillis();

        obstacles = new ArrayList<>();

        populateObstacles();



    }


    public static class ChoiceLevel {
        final String TAG = "lifecycle";
        public float time_level;
        public void setTime_Level(float time_level) {
            this.time_level = time_level;
            levelChoice = time_level;
            Log.d(TAG, "level состояние:  " + levelChoice);
        }
       // float dataLevel = levelChoice;
    }




    float dataLevel = levelChoice;

    boolean playerCollide(RectPlayer player) {
        for(Obstacle ob : obstacles) {
            if(ob.playerCollide(player))
                return true;
        }
        return false;
    }

    private void populateObstacles() {                                                              // Заполнять препятствия
        int currY = -5*Constants.SCREEN_HEIGHT/4;
        while (currY < 0) {
            int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));                 // Место положение разрыва (random)
            obstacles.add(new Obstacle(obstacleHeight, color, xStart, currY, playerGap));
            currY += obstacleHeight + obstacleGap;                                                  // высота препятствия +  разрыв препятствие = контроль Y
        }
    }




    public void update() {                                                                          // Обновление
        if (startTime < Constants.INIT_TIME)
            startTime = Constants.INIT_TIME;
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();


        float speed = (float) (Math.sqrt(dataLevel + (startTime - initTime) / 5000.0)) * Constants.SCREEN_HEIGHT / (10000.0f);  // Скорость увеличение каждые 10 секунд и высота между препятствиями
        for (Obstacle ob : obstacles) {                                                             // для препятствия ob
            ob.incrementY(speed * elapsedTime);                                                  // прибавлять скорость за пройденное время

        }
        if (obstacles.get(obstacles.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {     // Если
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(0, new Obstacle(obstacleHeight, color, xStart, obstacles.get(0).getRectangle().top - obstacleHeight - obstacleGap, playerGap));
            obstacles.remove(obstacles.size() - 1);

            score++;

            scoreUser++;
            scoreNormal++;
            scoreHard++;

            LevelUserScore.ResultUser UserInResult  = new LevelUserScore.ResultUser();
            UserInResult.setTime_Variable(scoreUser);

            LevelNormalScore.ResultNormal NormalInResult  = new LevelNormalScore.ResultNormal();
            NormalInResult.setTime_Variable(scoreNormal);

            LevelHardScore.ResultHard HardInResult  = new LevelHardScore.ResultHard();
            HardInResult.setTime_Variable(scoreHard);
        }
    }



    public void draw(Canvas canvas) {                                                               // Размер счетчика и цвет
        for (Obstacle ob : obstacles)
            ob.draw(canvas);
        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.BLUE);
        canvas.drawText("" + score, 50, 50 + paint.descent() - paint.ascent(), paint);


    }
}