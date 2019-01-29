package com.example.work.maze;

        import android.util.Log;

public class LevelHardContainer extends LevelAbstractConatainer {

    final String TAG = "lifecycle";

    @Override
    public void pour(LevelAbstractProductScore levelScore){
        Log.d(TAG, " LevelScore приравен Hard "+ levelScore.getClass().getSimpleName().replace("HardScore" , "") );
    }
}