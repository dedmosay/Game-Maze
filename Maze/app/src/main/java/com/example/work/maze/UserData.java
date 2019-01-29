//package com.example.work.maze;
///*
// *  int Score = 0; // записывает значение игрока(class GAME)
// *  String str = ""; // сохроаняет значение для чтения из файла переводится в число int boolScore2 = Score2;
// * */
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.widget.TextView;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//
//public class RECORDS extends AppCompatActivity {
//    final String TAG = "lifecycle";
//    final String FILE_NAME = "file";
//    int resultUserScore = 0;
//    String str = "";
//    int boolUserScore;
//    BufferedReader reader = null;
//    int recordUser;
//
//
//    //    Menu.Worker workerUser = new Menu.Worker(new LevelUser());
////    Menu.Worker workerNormal = new Menu.Worker(new LevelNormal());
////    Menu.Worker workerHard = new Menu.Worker(new LevelHard());
////
////        workerUser.run();
////        workerNormal.run();
////        workerHard.run();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_records);
////    UserData.readFileUser();
//
//
//        // 1 - читаем что записано в файле
//        Menu.ResultUser UserOutResult = new Menu.ResultUser();
//
//        if ((resultUserScore < UserOutResult.dataScore) && (boolUserScore < UserOutResult.dataScore)) {                  // 2 - проверяем значение которое набрали за последнее время
//            resultUserScore = UserOutResult.dataScore;                                                            // если значение Рекод - то записываем в Score
//            Log.d(TAG, "Score < dataScore  " + resultUserScore);
//            writeFileUser();                                                                            ///!!! ЗАПИСЫВАЕМ ТОЛЬКО ЕСЛИ
//        } else {                                                                                    //******* В общем смысле ELSE проверяет что выводить в class RECORD до запуска игры   ****//
//            if ((resultUserScore > boolUserScore)) {
//                recordUser = resultUserScore;                                        // 3 - Иначе если этот Score больше записанного РЕКОРД идет проверка
//                TextView myTextView = (TextView) findViewById(R.id.Id_userresult);                   // Далее идет вывод этого значения в record в class RECORD
//                String record = Integer.toString(resultUserScore);
//                myTextView.setText(record);
//            }
//            if (resultUserScore < boolUserScore)  {
//                recordUser = boolUserScore;                                    // 4 - если заначение boolScore2 записанное больше, чем то которое НАРАБОТАЛ ИГРОК, то выводить на экран его
//                TextView myTextView = (TextView) findViewById(R.id.Id_userresult);
//                String record = Integer.toString(boolUserScore);
//                myTextView.setText(record);
//            }
//        }
//    }
//
//
//    void writeFileUser() {
//        final String TAG = "lifecycle";
//        try {
//            Menu.ResultUser inpuUSERtResult = new Menu.ResultUser();
////          Menu.ResultNormal inputNORMALResult = new Menu.ResultNormal();
////          Menu.ResultHard inputHARDResult = new Menu.ResultHard();
//            TextView myTextView = (TextView) findViewById(R.id.Id_userresult);
//            String STROKA = Integer.toString(resultUserScore);
//            myTextView.setText(STROKA);
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(FILE_NAME, MODE_PRIVATE)));
//            Log.d(TAG, "Файл file - открыт:  " + FILE_NAME);
//            bw.write(STROKA);                                                                        // пишем данные
//            Log.d(TAG, "Запись файла " + STROKA);
//            bw.close();                                                                             // закрываем поток
//            Log.d(TAG, "Файл ЗАКРЫТ ");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    void readFileUser() {
//        final String TAG = "lifecycle";
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FILE_NAME)));
//            while ((str = br.readLine()) != null) {
//                Log.d(TAG, "Чтение файла " + str);
//                int UserScore = Integer.parseInt(str);
//                boolUserScore = UserScore;
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
