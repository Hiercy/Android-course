package com.example.volk1.quizzable;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewQuestion;
    private TextView mTextViewScore;

    private ProgressBar mProgressBar;

    private AlertDialog.Builder mBuilder;

    private Questions[] mQuestions = {
            new Questions(R.string.question_1, true),
            new Questions(R.string.question_2, false),
            new Questions(R.string.question_3, true),
            new Questions(R.string.question_4, false),
            new Questions(R.string.question_5, true),
            new Questions(R.string.question_6, false),
            new Questions(R.string.question_7, true),
            new Questions(R.string.question_8, false),
            new Questions(R.string.question_9, true),
            new Questions(R.string.question_10, false),
            new Questions(R.string.question_11, true),
            new Questions(R.string.question_12, false),
            new Questions(R.string.question_13, true)
    };

    int numberProgressBar;
    int currentQuestion;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewQuestion = findViewById(R.id.question);
        mTextViewScore = findViewById(R.id.score);
        mProgressBar = findViewById(R.id.progress);

        if (savedInstanceState != null) {
            currentQuestion = savedInstanceState.getInt("currentQuestion");
            score = savedInstanceState.getInt("score");
            numberProgressBar = savedInstanceState.getInt("numberProgressBar");

            mTextViewScore.setText("Score: " + score + "/13");

            initDialog();
        } else {
            currentQuestion = 0;
            score = 0;
            numberProgressBar = 1;
        }
    }

    public void answerFalse(View view) {
        checkAnswer(false);
        update();
    }

    public void answerTrue(View view) {
        checkAnswer(true);
        update();
    }

    private void displayToast(int correct) {
        Toast.makeText(getApplicationContext(),
                correct,
                Toast.LENGTH_SHORT).show();
    }

    private void update() {
        currentQuestion = (currentQuestion + 1) % mQuestions.length;
        numberProgressBar++;

        int quest = mQuestions[currentQuestion].getQuestion();

        initDialog();
        mTextViewQuestion.setText(quest);
    }

    private void checkAnswer(boolean userAnswer) {
        boolean correctAns = mQuestions[currentQuestion].isAnswerIsTrueOrNot();

        mProgressBar.setProgress(numberProgressBar, true);

        if (correctAns == userAnswer) {
            displayToast(R.string.correct_toast);
            score++;

            mTextViewScore.setText("Score: " + score + "/13");

        } else {
            displayToast(R.string.incorrect_toast);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("score", score);
        outState.putInt("currentQuestion", currentQuestion);
        outState.putInt("numberProgressBar", numberProgressBar);
    }

    private void initDialog() {
        if (currentQuestion == 0) {
            mBuilder = new AlertDialog.Builder(MainActivity.this);

            mBuilder
                    .setTitle("Game Over")
                    .setMessage("Your score: " + score + ". Bye. Have a beautiful time!")
                    .setCancelable(true)
                    .setNegativeButton("Close app",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    System.exit(0);
                                }
                            })

                    .setPositiveButton("Start again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            score = 0;
                            numberProgressBar = 1;

                            mTextViewScore.setText("Score: " + score + "/13");

                            mProgressBar.setProgress(score);
                        }
                    });
            AlertDialog alertDialog = mBuilder.create();
            alertDialog.show();
        }
    }
}
