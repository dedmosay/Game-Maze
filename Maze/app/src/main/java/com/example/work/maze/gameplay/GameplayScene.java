package com.example.work.maze.gameplay;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;



/**
 * В данном фале содержится
 * размер ИГРОВОЙ ТОЧКИ и начальное место положение Игрока
 * размер ПРЕПЯТСТИЯ
 * СБРОС
 * ОБРАБОТКА ОШИБОК - Получение сенсорном
 * ОПРЕДЕЛЯЕМ МЕСТО ПОЛОЖЕНИЕ ИГРОКА НА ПОЛЕ
 * ЧТО ДЕЛАТЬ ПРИ СТОЛКНОВЕНИИ
 * Created by Oleg on 30.03.2018.
 */
public class GameplayScene implements Scene {

    private Rect r = new Rect();

    private RectPlayer player;
    private Point playerPoint;
    private ObstacleManager obstacleManager;

    private boolean movingPlayer = false;

    private boolean gameOver = false;
    private long gameOverTime;

    private OrientationData orientationData;
    private long frameTime;



    public GameplayScene() {


        // Игровая сцена
        player = new RectPlayer(new Rect(100, 100, 200, 200), Color.rgb( 255, 0, 0) ); // ----------------------------------- Дописал РЕЗУЛЬТАТ = 0
        playerPoint = new Point(Constants.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4); // начальное место положение куба
        player.update(playerPoint);                                                                 // Игрок (точка)

        obstacleManager = new ObstacleManager( 350, 800, 75, Color.GREEN);   // Менеджер препятствий (размер )

        orientationData = new OrientationData();
        orientationData.register();
        frameTime = System.currentTimeMillis();
    }

    public void  reset() {
        playerPoint = new Point(Constants.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4);       // начальное место положение Игрока
        player.update(playerPoint);
        obstacleManager = new ObstacleManager(350, 800, 75, Color.GREEN); // ----------------------------------- Дописал РЕЗУЛЬТАТ = 0
        movingPlayer = false;

    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }



    @Override                                                                                       // ОБРАБОТКА ОШИБОК
    public void recieveToutch(MotionEvent event){                                                   // Получение сенсорном, получить действие
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:                                                           // В случае если действие вниз
                if(!gameOver && player.getRectangle().contains((int)event.getX(), (int)event.getY())); // если не конец и получаемый прямоугольник по оси Х и У
                movingPlayer = true;                                                                   // движущийся игрок = ДА
                if(gameOver && System.currentTimeMillis() - gameOverTime >=2000) {                          // если конец и системное время -  время игры >= 2 секунды
                    reset();                                                                                // вызвать сборс
                    gameOver = false;                                                                       // игра НЕ закончена
                    orientationData.newGame();                                                              // Начать новую игру
                }
                break;
            case MotionEvent.ACTION_MOVE:                                                           //  В случае если действие движение
                if(!gameOver && movingPlayer)                                                       //  если не конец и перемещение
                    playerPoint.set((int)event.getX(), (int)event.getY());                          //  (точка) игрок по оси Х и У
                break;
            case MotionEvent.ACTION_UP:                                                             // В случае если Событие движения вперед
                movingPlayer = false;                                                               // движущийся игрок = НЕТ
                break;
        }
    }

    @Override
    public void draw(Canvas canvas){                                                                // При получении
        canvas.drawColor(Color.WHITE);                                                              // На окне рисовать цвет БЕЛЫЙ

        player.draw(canvas);                                                                        // Игрок - на окне
        obstacleManager.draw(canvas);                                                               // Менеджер препятствий - на окне

        if (gameOver) {                                                                             // ЕСЛИ конец игры
            Paint paint = new Paint();                                                              // создать обьект
            paint.setTextSize(100);                                                                 // такого-то размера
            paint.setColor(Color.BLUE);                                                             // такого-то цвета

//            int OUTPUT2 = 0;
//            Menu.Result OutResult  = new Menu.Result();
//            OUTPUT2=OutResult.dataScore;
//            String Str = Integer.toString(OUTPUT2);

            drawCenterText(canvas, paint, "Game Over");
        }
    }

    @Override
    public void update() {                                                                          // ОБРАБОТЧИК ОБНОВЛЕНИЯ
        if (!gameOver) {                                                                            // ЕСЛИ не конец игры
            if(frameTime < Constants.INIT_TIME)                                                             // ЕСЛИ время кадра меньше в это время
                frameTime = Constants.INIT_TIME;                                                            // время кадра = это время
            int elepsedTime = (int) (System.currentTimeMillis() - frameTime);                       // Пройденое время  = текущее время - время кадра
            frameTime = System.currentTimeMillis();                                                 // Время кадра =  текущее время
            if(orientationData.getOrientation() != null && orientationData.getStartOrientation() != null) {     // ДАННЫЕ   = ДАННЫЕ (1) - начальные ДАННЫЕ (2) != NULL
                float pitch = orientationData.getOrientation()[1] - orientationData.getStartOrientation()[1];   // УРОВЕНЬ  = ДАННЫЕ (1) - начальные ДАННЫЕ (1)
                float roll = orientationData.getOrientation()[2] - orientationData.getStartOrientation()[2];    // СТРОКА   = ДАННЫЕ (2) - начальные ДАННЫЕ (2)

                float xSpeed = 2 * roll * Constants.SCREEN_WIDTH/1000f;                             // Скорость по Х =  2 * СТРОКА  *  ШИРИНА/1000
                float ySpeed = pitch * Constants.SCREEN_HEIGHT/1500f;                               // Скорость по У =  УРОВЕНЬ     *  ВЫСОТА/1000!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                playerPoint.x += Math.abs(xSpeed*elepsedTime) > 5 ? xSpeed*elepsedTime : 0;         // Игрок (ТОЧКА по Х) = Вычисление скорости
                playerPoint.y += Math.abs(ySpeed*elepsedTime) > 5 ? ySpeed*elepsedTime : 0;         // Игрок (ТОЧКА по У) = Вычисление скорости
            }
                                                                                                    // ОПРЕДЕЛЯЕМ МЕСТО ПОЛОЖЕНИЕ ИГРОКА НА ПОЛЕ
            if(playerPoint.x < 0)                                                                   // Если (ТОЧКА по Х) меньше 0
                playerPoint.x = 0;                                                                  // То РАВНО 0
            else if(playerPoint.x > Constants.SCREEN_WIDTH)                                         // ИНАЧЕ ЕСЛИ (ТОЧКА по Х) больше ШИРИНЫ
                playerPoint.x = Constants.SCREEN_WIDTH;                                             // То РАВНО ШИРИНЕ
            if(playerPoint.y < 0)                                                                   // Если (ТОЧКА по У) меньше 0
                playerPoint.y = 0;                                                                  // То РАВНО 0
            else if(playerPoint.y > Constants.SCREEN_HEIGHT)                                        // ИНАЧЕ ЕСЛИ (ТОЧКА по Y) больше ВЫСОТЫ
                playerPoint.y = Constants.SCREEN_HEIGHT;                                            // То РАВНО ВЫСОТЕ

            player.update(playerPoint);                                                             // Игрок обновление(ТОЧКА ИГРОКА)
            obstacleManager.update();                                                               // Менеджер препятствий. ОБНОВЛЕНИЕ
                                                                                                    // ЧТО ДЕЛАТЬ ПРИ СТОЛКНОВЕНИИ
            if (obstacleManager.playerCollide(player)) {                                            // ЕСЛИ (менеджер препятствий СТАЛКИВАНИЕ ИГРОКА)
                gameOver = true;                                                                    // игра закончена = ДА!
                gameOverTime = System.currentTimeMillis();

                                                                                                    // ВРЕМЯ ИГРЫ ВЫЛШО = текущее время (В данный момент)
            }

        }

    }


    private void drawCenterText(Canvas canvas, Paint paint, String text) {
        // Добавляем поле для текста
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);

        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }



}
