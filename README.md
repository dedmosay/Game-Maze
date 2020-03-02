## Android-Game
Пример. Запуск приложения.

### Menu.java

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
    

![](https://github.com/ilinoa/Android-Game/blob/master/image/4.jpeg)

Пример запуска игры Maze

![](https://github.com/ilinoa/Android-Game/blob/master/image/1.jpeg)

### Dbh.java

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

Лучшие результаты.

![](https://github.com/ilinoa/Android-Game/blob/master/image/3.jpeg)


Можно изменить уровень сложности.

### Players.java

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
 
Изменяем уровень сложности игры

![](https://github.com/ilinoa/Android-Game/blob/master/image/2.jpeg)



#### Menu.java

        mButtonOpen = (Button) findViewById(R.id.button_open_connection);
        mButtonSend = (Button) findViewById(R.id.button_send_connection);
        mButtonClose = (Button) findViewById(R.id.button_close_connection);



