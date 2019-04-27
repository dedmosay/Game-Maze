package com.example.work.maze.level;

import android.util.Log;

public class LevelUserScore extends LevelAbstractProductScore {

    static int scoreUser;
    static float levelMazeRead;

    public static class ChoiceLevelRead {
        final String TAG = "lifecycle";
        public float time_levelRead;
        public void setTime_LevelRead(float time_levelRead) {
            this.time_levelRead = time_levelRead;
            levelMazeRead = time_levelRead;
            Log.d(TAG, "choiceLevelRead состояние:  " + choiceLevelRead);
        }
         float choiceLevelRead = levelMazeRead;
    }


    public static class ResultUser {
        final String TAG = "lifecycle";
        public int time_variable;

        public void setTime_Variable(int time_variable) {
            this.time_variable = time_variable;

            if(levelMazeRead == 5.0f ) {
                scoreUser = time_variable;
                Log.d(TAG, "scoreUser состояние:  " + scoreUser);
            }
        }

        public int dataScoreUser = scoreUser;
    }
}
