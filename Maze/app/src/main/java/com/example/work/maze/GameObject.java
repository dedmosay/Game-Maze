package com.example.work.maze;

import android.graphics.Canvas;

/**интерфейс Игрового объекта:
 *      - Обращение окна
 *      - Обновление
 *
 * Created by work on 26.03.2018.
 */
public interface GameObject {
    public void draw(Canvas canvas);
    public void update();
}