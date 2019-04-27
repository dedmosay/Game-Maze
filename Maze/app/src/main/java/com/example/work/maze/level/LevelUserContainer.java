package com.example.work.maze.level;

        import android.util.Log;

public class LevelUserContainer extends LevelAbstractConatainer {

    final String TAG = "lifecycle";

    @Override
    public void pour(LevelAbstractProductScore levelScore){
        Log.d(TAG, " LevelScore приравен User "+ levelScore.getClass());
    }
}
