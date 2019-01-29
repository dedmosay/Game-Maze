package com.example.work.maze;

import android.util.Log;

public class LevelHardScore extends LevelAbstractProductScore {
    static int scoreHard;
    static float levelMazeRead;

    static class ChoiceLevelRead {
        final String TAG = "lifecycle";
        public float time_levelRead;
        public void setTime_LevelRead(float time_levelRead) {
            this.time_levelRead = time_levelRead;
            levelMazeRead = time_levelRead;
            Log.d(TAG, "choiceLevelRead состояние:  " + choiceLevelRead);
        }
        float choiceLevelRead = levelMazeRead;
    }


    static class ResultHard {
        final String TAG = "lifecycle";
        public int time_variable;

        public void setTime_Variable(int time_variable) {
            this.time_variable = time_variable;

            if(levelMazeRead == 20.0f ) {
                scoreHard = time_variable;
                Log.d(TAG, "scoreHard состояние:  " + scoreHard);
            }
        }

        int dataScoreHard = scoreHard;
    }
}
// наследуемся от воды для создания колы, теперь в классе ColaWater содержит все методы Abstarct

