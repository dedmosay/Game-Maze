# Android-Game
 Начало игры при запуске приложения Maze

## [Menu.java](https://github.com/ilinoa/Android-Game/blob/master/Maze/app/src/main/java/com/example/work/maze/Menu.java "Menu.java ")

    @Override
    public void onClick(View view) {

        Intent intent;
        switch (view.getId()) {                                                                     
            case R.id.Id_start:
                levelReadFile();
                ObstacleManager.ChoiceLevel Level = new ObstacleManager.ChoiceLevel();
                float levelChoice = levelMaze;
                Level.setTime_Level(levelChoice);
                intent = new Intent(this, GAME.class);
                startActivity(intent);
                Log.d(TAG, "open START - class GAME");
                break;

            case R.id.Id_level:
                intent = new Intent(this, PLAYERS.class);
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
    
Пример запуска Menu

![](https://github.com/ilinoa/Android-Game/blob/master/image/4.jpeg)

Если выбрать кнопку пункт меню НАЧАТЬ ИГРУ - запуститься класс GAME

## [GAME.java](https://github.com/ilinoa/Android-Game/blob/master/Maze/app/src/main/java/com/example/work/maze/GAME.java "GAME.java")

    @Override
    protected void onCreate(Bundle savedInstanceState) {                                            
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); 
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);                                         
        DisplayMetrics dm = new DisplayMetrics();                                     
        getWindowManager().getDefaultDisplay().getMetrics(dm);                        
        Constants.SCREEN_WIDTH = dm.widthPixels;                                          
        Constants.SCREEN_HEIGHT = dm.heightPixels;                                          
        setContentView(new GamePanel(this));                                                
    }

Пример запуска GAME 

![](https://github.com/ilinoa/Android-Game/blob/master/image/1.jpeg)



Для того чтобы узнать лучшие результаты, данные можно увидеть в классе DBH (данные сохраняются в mySQL)
В предыдущем варианте данные сохранялись в файлы в класс RECORDS.java
## [DBH.java](https://github.com/ilinoa/Android-Game/blob/master/Maze/app/src/main/java/com/example/work/maze/DBH.java "DBH.java")

        LevelUserScore.ResultUser OutResult = new LevelUserScore.ResultUser();
        LevelNormalScore.ResultNormal NormalOutResult = new LevelNormalScore.ResultNormal();
        LevelHardScore.ResultHard HardOutResult = new LevelHardScore.ResultHard();

        int readUser =Integer.parseInt(user);
        int readNorm =Integer.parseInt(norm);
        int readHard =Integer.parseInt(hard);

        if (readUser < OutResult.dataScoreUser){
            int updIntValue = OutResult.dataScoreUser;
            updValue = Integer.toString(updIntValue);
            updId = "1";
            user = updValue;
            updUsing();
            readTable();
        }
        if (readNorm < NormalOutResult.dataScoreNormal) {
            int updIntValue = NormalOutResult.dataScoreNormal;
            updValue = Integer.toString(updIntValue);
            updId = "2";
            String norm = updValue;
            updUsing();
            readTable();
        }
        if (readHard  < HardOutResult.dataScoreHard){
            int updIntValue = HardOutResult.dataScoreHard;
            updValue = Integer.toString(updIntValue);
            updId = "3";
            String hard = updValue;
            updUsing();
            readTable();
        }
    }

Пример запуска DBH - ЛУЧШИЕ РЕЗУЛЬТАТЫ

![](https://github.com/ilinoa/Android-Game/blob/master/image/3.jpeg)




Можно изменить уровень сложности

## [PLAYERS.java](https://github.com/ilinoa/Android-Game/blob/master/Maze/app/src/main/java/com/example/work/maze/PLAYERS.java "PLAYERS.java")

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

Пример запуска PLAYERS - ИЗМЕНИТЬ УРОВЕНЬ

![](https://github.com/ilinoa/Android-Game/blob/master/image/2.jpeg)


На данный момент планируется работа с сервером (уже реализовано - идет доработка с графикой)
Вы могли заметить кнопки OPEN | SEND | CLOSE
Предназначены для открытия потока (связь с сервером)
Отправкой данных на сервер (данные - лучшие результаты)
Закрыть соединение

        mButtonOpen = (Button) findViewById(R.id.button_open_connection);
        mButtonSend = (Button) findViewById(R.id.button_send_connection);
        mButtonClose = (Button) findViewById(R.id.button_close_connection);


#### [Menu.java](https://github.com/ilinoa/Android-Game/blob/master/Maze/app/src/main/java/com/example/work/maze/Menu.java "Menu.java")
