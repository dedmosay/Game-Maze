package com.example.work.maze.gameplay;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Oleg on 05.04.2018.
 */
public class AnimationManager {                                                                     // Менеджер анимации
    private Animation[] animations;
    private int animationIndex = 0;

    public AnimationManager(Animation[] animations) {
        this.animations = animations;
    }

    public void playAnim(int index) {                                                               // Управление игрой анимации во время передивежения
        for (int i = 0; i < animations.length; i++) {
            if(i == index) {
                if (!animations[index].isPlaying())
                     animations[i].play();
            } else
                animations[i].stop();
        }
        animationIndex = index;
    }

    public void draw(Canvas canvas, Rect rect) {                                                    // Если движение есть, то управлять анимацией
        if(animations[animationIndex].isPlaying())
            animations[animationIndex].draw(canvas, rect);
    }

    public void update () {                                                                         // Если есть аниация, то обновлять
        if(animations[animationIndex].isPlaying())
            animations[animationIndex].update();
    }
}