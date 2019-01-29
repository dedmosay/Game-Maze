package com.example.work.maze;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_PRIVATE;

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

