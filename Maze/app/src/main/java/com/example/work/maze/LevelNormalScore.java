package com.example.work.maze;

import android.util.Log;

public class LevelNormalScore extends LevelAbstractProductScore {

    static int scoreNormal;
    static float levelMazeRead;
    //static float choiceLevelRead;


    static class ChoiceLevelRead {
        final String TAG = "lifecycle";
        public float time_levelRead;
        public void setTime_LevelRead(float time_levelRead) {
            this.time_levelRead = time_levelRead;
            levelMazeRead = time_levelRead;
            Log.d(TAG, "choiceLevelRead состояние :  " + choiceLevelRead);
        }
        float choiceLevelRead = levelMazeRead;
    }


    static class ResultNormal {
        final String TAG = "lifecycle";
        public int time_variable;

        public void setTime_Variable(int time_variable) {
            this.time_variable = time_variable;

            if(levelMazeRead == 10.0f ) {
                scoreNormal = time_variable;
                Log.d(TAG, "scoreNormal состояние:  " + scoreNormal);
            }
        }

        int dataScoreNormal = scoreNormal;
    }
}
