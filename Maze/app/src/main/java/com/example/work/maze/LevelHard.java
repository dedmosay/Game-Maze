package com.example.work.maze;

import android.util.Log;

public class LevelHard extends LevelAbstractFactory {
    final String TAG = "lifecycle";

    @Override
    public LevelAbstractProductScore createLevelScore(){
        Log.d(TAG, "open public class LevelUser -> начало LevelUserScore");
        return new LevelHardScore();
    }

    @Override
    public LevelAbstractConatainer createContainer(){
        Log.d(TAG, "open public class LevelUser -> начало createContainer");
        return new LevelHardContainer();
    }
}