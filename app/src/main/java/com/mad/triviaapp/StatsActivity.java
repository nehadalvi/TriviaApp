package com.mad.triviaapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        ProgressBar progress = (ProgressBar) findViewById(R.id.progressPerformance);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_answers);
        int noOfQuestions,correctAnswers,incorrectAnswers=0;
        double percentage;
        ArrayList<Question> answers = (ArrayList<Question>) getIntent().getExtras().getSerializable("ANSWERS_KEY");

        noOfQuestions = answers.size();
        Log.d("no of qts=",noOfQuestions+"");

        for (int i=0;i<answers.size();i++ ) {
            int answerIndex = Integer.parseInt(answers.get(i).getAnswer());
            String answer = answers.get(i).getChoices()[answerIndex-1];
            if(answers.get(i).getUserAnswer().equals("null") || !(answers.get(i).getUserAnswer().equals(answer))){
                TextView qtText = new TextView(this);
                qtText.setText(answers.get(i).getQuestionText());
                TextView yourAns = new TextView(this);
                yourAns.setText(answers.get(i).getUserAnswer());
                yourAns.setBackgroundColor(Color.RED);
                TextView correctAns = new TextView(this);
                correctAns.setText(answer);
                linearLayout.addView(qtText);
                linearLayout.addView(yourAns);
                linearLayout.addView(correctAns);
                incorrectAnswers++;
                Log.d("incorrect=",incorrectAnswers+"");
            }
        }

        findViewById(R.id.btn_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        correctAnswers = noOfQuestions - incorrectAnswers;
        Log.d("correct=",correctAnswers+"");
        percentage = (correctAnswers * 100)/noOfQuestions;
        Log.d("perc",percentage+"");
        progress.setMax(100);
        progress.setProgress((int) percentage);
        TextView percText = (TextView) findViewById(R.id.tv_percentage);
        percText.setText(String.valueOf(percentage)+"%");
    }
}
