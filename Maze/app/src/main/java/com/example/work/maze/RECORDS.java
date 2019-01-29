package com.example.work.maze;

        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.widget.TextView;

        import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;

public class RECORDS extends AppCompatActivity {

    final String TAG = "lifecycle";
    final String FILE_USER = "userRecord";
    int Score = 0;
    String str = "";
    int boolScore2;
    BufferedReader reader = null;
    int userRecord;
//______________________________________________________

    final String FILE_NORMAL = "normalRecord";
    int ScoreNormal = 0;
    String strNormal = "";
    int boolScoreNormal2;
    BufferedReader readerNormal = null;
    int normalRecord;
//______________________________________________________

    final String FILE_HARD = "hardRecord";
    int ScoreHard = 0;
    String strHard = "";
    int boolScoreHard2;
    BufferedReader readerHard = null;
    int hardRecord;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        LevelUserScore.ResultUser OutResult = new LevelUserScore.ResultUser();
        LevelNormalScore.ResultNormal NormalOutResult = new LevelNormalScore.ResultNormal();
        LevelHardScore.ResultHard HardOutResult = new LevelHardScore.ResultHard();

        readFile();
        readFileNormal();
        readFileHard();


        if ((Score < OutResult.dataScoreUser) && (boolScore2 < OutResult.dataScoreUser)) {                  // Score < OutResult.dataScoreUser 0 < 13 && boolScore2 < OutResult.dataScoreUser  8 < 13
            Log.d(TAG, "Score < OutResult.dataScoreUser " + Score + " < " +OutResult.dataScoreUser +" && "+"boolScore2 < dataScoreUser  " + boolScore2 + " < " + OutResult.dataScoreUser);
            Score = OutResult.dataScoreUser;
            Log.d(TAG,"Score = OutResult.dataScoreUser " + Score);
                    writeFileUser();
        } else {
            if ((Score > boolScore2)) {
                userRecord = Score;
                TextView myTextView = (TextView) findViewById(R.id.Id_userresult);
                String recordUser = Integer.toString(Score);
                myTextView.setText(recordUser);

            }
            if (Score < boolScore2) {
                userRecord = boolScore2;
                TextView myTextView = (TextView) findViewById(R.id.Id_userresult);
                String recordUser = Integer.toString(boolScore2);
                myTextView.setText(recordUser);
            }
        }



        if ((ScoreNormal < NormalOutResult.dataScoreNormal) && (boolScoreNormal2 < NormalOutResult.dataScoreNormal)) {
            Log.d(TAG, "boolScoreNormal2 < dataScoreNormal  " + boolScoreNormal2 + " < " + NormalOutResult.dataScoreNormal);
            ScoreNormal = NormalOutResult.dataScoreNormal;

            writeFileNormal();
        } else {
            if ((ScoreNormal > boolScoreNormal2)) {
                normalRecord = ScoreNormal;
                TextView myTextView = (TextView) findViewById(R.id.Id_normalresult);
                String recordNormal = Integer.toString(ScoreNormal);
                myTextView.setText(recordNormal);
            }
            if (ScoreNormal < boolScoreNormal2) {
                normalRecord = boolScoreNormal2;
                TextView myTextView = (TextView) findViewById(R.id.Id_normalresult);
                String recordNormal = Integer.toString(boolScoreNormal2);
                myTextView.setText(recordNormal);
            }
        }

            //-----------------------------------------------------------------
            if ((ScoreHard < HardOutResult.dataScoreHard) && (boolScoreHard2 < HardOutResult.dataScoreHard)) {
                Log.d(TAG, "boolScore2 < dataScoreHard  " + boolScoreHard2 + " < " + HardOutResult.dataScoreHard);
                ScoreHard = HardOutResult.dataScoreHard;

                writeFileHard();
            } else {
                if ((ScoreHard > boolScoreHard2)) {
                    hardRecord = ScoreHard;
                    TextView myTextView = (TextView) findViewById(R.id.Id_hardresult);
                    String recordHard = Integer.toString(ScoreHard);
                    myTextView.setText(recordHard);
                }
                if (ScoreHard < boolScoreHard2) {
                    hardRecord = boolScoreHard2;
                    TextView myTextView = (TextView) findViewById(R.id.Id_hardresult);
                    String recordHard = Integer.toString(boolScoreHard2);
                    myTextView.setText(recordHard);
                }
            }
        }


    void writeFileUser() {
        final String TAG = "lifecycle";
        try {
            LevelUserScore.ResultUser inputResult = new LevelUserScore.ResultUser();
            TextView myTextView = (TextView) findViewById(R.id.Id_userresult);
            String UserSTR = Integer.toString(Score);
            myTextView.setText(UserSTR);
            BufferedWriter UserBW = new BufferedWriter(new OutputStreamWriter(                          // отрываем поток для записи
                    openFileOutput(FILE_USER, MODE_PRIVATE)));
            Log.d(TAG, "Файл file - открыт FILE_USER:  " + FILE_USER);
            UserBW.write(UserSTR);                                                                        // пишем данные

            Log.d(TAG, "Запись файла FILE_USER" + UserSTR);
            UserBW.close();                                                                             // закрываем поток
            Log.d(TAG, "Файл ЗАКРЫТ FILE_USER");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeFileNormal() {
        final String TAG = "lifecycle";
        try {
            LevelNormalScore.ResultNormal NormalinputResult = new LevelNormalScore.ResultNormal();
            TextView myTextView = (TextView) findViewById(R.id.Id_normalresult);
            String NormalSTR = Integer.toString(ScoreNormal);
            myTextView.setText(NormalSTR);
            BufferedWriter NormalBW = new BufferedWriter(new OutputStreamWriter(                          // отрываем поток для записи
                    openFileOutput(FILE_NORMAL, MODE_PRIVATE)));
            Log.d(TAG, "Файл file - открыт FILE_NORMAL:  " + FILE_NORMAL);
            NormalBW.write(NormalSTR);                                                                        // пишем данные

            Log.d(TAG, "Запись файла FILE_NORMAL" + NormalSTR);
            NormalBW.close();                                                                             // закрываем поток
            Log.d(TAG, "Файл ЗАКРЫТ FILE_NORMAL");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeFileHard() {
        final String TAG = "lifecycle";
        try {
            LevelHardScore.ResultHard HardlinputResult = new LevelHardScore.ResultHard();
            TextView myTextView = (TextView) findViewById(R.id.Id_hardresult);                      //Id_hardresult
            String HardSTR = Integer.toString(ScoreHard);
            myTextView.setText(HardSTR);
            BufferedWriter HardBW = new BufferedWriter(new OutputStreamWriter(                          // отрываем поток для записи
                    openFileOutput(FILE_HARD, MODE_PRIVATE)));
            Log.d(TAG, "Файл file - открыт FILE_HARD:  " + FILE_HARD);
            HardBW.write(HardSTR);                                                                        // пишем данные

            Log.d(TAG, "Запись файла FILE_HARD" + HardSTR);
            HardBW.close();                                                                             // закрываем поток
            Log.d(TAG, "Файл ЗАКРЫТ FILE_HARD");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void readFile() {
        final String TAG = "lifecycle";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(FILE_USER)));
            String str = "";
            while ((str = br.readLine()) != null) {
                Log.d(TAG, "Чтение файла " + str);
                int Score2 = Integer.parseInt(str);
                boolScore2 = Score2;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readFileNormal() {
        final String TAG = "lifecycle";
        try {
            BufferedReader NormalBW = new BufferedReader(new InputStreamReader(
                    openFileInput(FILE_NORMAL)));
            String strNormal = "";
            while ((strNormal = NormalBW.readLine()) != null) {
                Log.d(TAG, "Чтение файла " + strNormal);
                int ScoreNormal2 = Integer.parseInt(strNormal);
                boolScoreNormal2 = ScoreNormal2;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readFileHard() {
            final String TAG = "lifecycle";
            try {
                BufferedReader HardBW = new BufferedReader(new InputStreamReader(
                        openFileInput(FILE_HARD)));
                String strHard = "";
                while ((strHard = HardBW.readLine()) != null) {
                    Log.d(TAG, "Чтение файла " + strHard);
                    int ScoreHard2 = Integer.parseInt(strHard);
                    boolScoreHard2 = ScoreHard2;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}