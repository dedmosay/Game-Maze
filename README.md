# Android-Game
## Menu
![](https://github.com/ilinoa/Android-Game/blob/master/image/2.jpeg)
#### [Menu.java](https://github.com/ilinoa/Android-Game/blob/master/Maze/app/src/main/java/com/example/work/maze/Menu.java "Menu.java ")

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


Image:

![](https://github.com/ilinoa/Android-Game/blob/master/image/1.jpeg)

![](https://github.com/ilinoa/Android-Game/blob/master/image/3.jpeg)
![](https://github.com/ilinoa/Android-Game/blob/master/image/4.jpeg)
