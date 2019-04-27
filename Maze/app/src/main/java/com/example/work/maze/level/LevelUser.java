package com.example.work.maze.level;

import android.util.Log;

public class LevelUser extends LevelAbstractFactory {
        final String TAG = "lifecycle";


        @Override
        public LevelAbstractProductScore createLevelScore(){
            Log.d(TAG, "open public class LevelUser -> начало LevelUserScore");
            return new LevelUserScore();
        }

        @Override
        public LevelAbstractConatainer createContainer(){
            Log.d(TAG, "open public class LevelUser -> начало createContainer");
            return new LevelUserContainer();
        }
}

