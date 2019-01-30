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
    
  Example menu

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

 Example start game

![](https://github.com/ilinoa/Android-Game/blob/master/image/1.jpeg)

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

![](https://github.com/ilinoa/Android-Game/blob/master/image/3.jpeg)

![](https://github.com/ilinoa/Android-Game/blob/master/image/2.jpeg)
