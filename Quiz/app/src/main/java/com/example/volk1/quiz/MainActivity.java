package com.example.volk1.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button mFalseButton;
    private Button mTrueButton;

    private Button mNextButton;
    private Button mPreviousButton;

    private TextView mAnswerTextView;

    private Question[] questions = {
            new Question(R.string.question_africa, true),
            new Question(R.string.question_americas, false),
            new Question(R.string.question_asia, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_oceans, true)
    };
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFalseButton = findViewById(R.id.buttonFalse);
        mTrueButton = findViewById(R.id.buttonTrue);

        mNextButton = findViewById(R.id.buttonNext);
        mPreviousButton = findViewById(R.id.buttonPervious);

        mAnswerTextView = findViewById(R.id.answerTextView);

        initData();

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1) % questions.length;
                initData();
            }
        });

        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex - 1) % questions.length;
                initData();
            }
        });
    }

    private void initData() {
        int question = questions[currentIndex].getTextResID();
        mAnswerTextView.setText(question);
    }

    private void checkAnswer(boolean userAnswer) {
        boolean answerIsTrue = questions[currentIndex].IsAnswerCorrect();
        int message = 0;
        if (userAnswer == answerIsTrue) {
            message = R.string.correct_answer;
        } else {
            message = R.string.incorrect_answer;
        }
        Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_SHORT).show();
    }
}
