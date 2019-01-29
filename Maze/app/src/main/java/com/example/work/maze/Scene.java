package com.example.work.maze;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by Oleg on 30.03.2018.
 */
public interface Scene {
    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void recieveToutch(MotionEvent event);
}
