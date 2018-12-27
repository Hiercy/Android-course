package com.example.volk1.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "com.volk1.quiz.MainActivity";

    private Button mFalseButton;
    private Button mTrueButton;

    private ImageButton mNextButton;
    private ImageButton mPreviousButton;

    private TextView mQuestionTextView;

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
        Log.d(TAG, "onCreate called!");
        setContentView(R.layout.activity_main);

        mFalseButton = findViewById(R.id.buttonFalse);
        mTrueButton = findViewById(R.id.buttonTrue);

        mNextButton = findViewById(R.id.buttonNext);
        mPreviousButton = findViewById(R.id.buttonPrevious);

        mQuestionTextView = findViewById(R.id.questionTextView);

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

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("currentQuestion");
            initData();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop called!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called!");
    }

    private void initData() {
        int question = questions[currentIndex].getTextResID();
        mQuestionTextView.setText(question);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentQuestion", currentIndex);
    }
}
