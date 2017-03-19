package com.mad.triviaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import static com.mad.triviaapp.R.drawable.trivia;

public class MainActivity extends AppCompatActivity implements GetQuiz.IData{
    LinearLayout imageHolder;
    ProgressBar loadTrivia;
    TextView message;
    ImageView triviaImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://dev.theappsdr.com/apis/trivia_json/index.php";
        imageHolder = (LinearLayout) findViewById(R.id.linear_trivia_image);
        imageHolder.removeAllViews();

        findViewById(R.id.btn_start_trivia).setEnabled(false);
        message = new TextView(this);
        message.setText("Loading Trivia");
        message.setGravity(Gravity.CENTER);
        message.setTextColor(Color.BLACK);
        message.setTextSize(20);
        imageHolder.addView(message);
        loadTrivia = new ProgressBar(this);
        loadTrivia.setVisibility(View.VISIBLE);
        imageHolder.addView(loadTrivia);

        new GetQuiz(this).execute(url);

        findViewById(R.id.btn_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void sendData(final ArrayList<Question> questions) {

        findViewById(R.id.btn_start_trivia).setEnabled(true);

        imageHolder.removeAllViews();
        triviaImg = new ImageView(this);
        triviaImg.setImageResource(trivia);
        imageHolder.addView(triviaImg);
        message = new TextView(this);
        message.setText("Trivia Ready");
        message.setGravity(Gravity.CENTER);
        message.setTextColor(Color.BLACK);
        message.setTextSize(20);
        imageHolder.addView(message);


        findViewById(R.id.btn_start_trivia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TriviaActivity.class);
                intent.putExtra("QUESTIONS_KEY",questions);
                startActivity(intent);
            }
        });

    }
}
