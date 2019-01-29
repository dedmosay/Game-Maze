package com.example.work.maze;

        import android.util.Log;

public class LevelNormalContainer extends LevelAbstractConatainer {

    final String TAG = "lifecycle";

    @Override
    public void pour(LevelAbstractProductScore levelScore){
        Log.d(TAG, " LevelScore приравен Normal"+ levelScore.getClass().getSimpleName().replace("NormalScore" , "") );

    }
}