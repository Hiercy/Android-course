package com.example.volk1.quizzable;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewQuestion;
    private TextView mTextViewScore;

    private ProgressBar mProgressBar;

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

        currentQuestion = 0;
        score = 0;
        numberProgressBar = 1;
    }

    public void answerFalse(View view) {
        checkAnswer(false);
        update();
    }

    public void answerTrue(View view) {
        checkAnswer(true);
        update();
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_SHORT).show();
    }

    private void update() {
        currentQuestion = (currentQuestion + 1) % mQuestions.length;
        numberProgressBar++;

        int quest = mQuestions[currentQuestion].getQuestion();

        if (currentQuestion == 0) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder
                    .setTitle("Game Over")
                    .setMessage("Your score: " + score + ". Bye. Have a beautiful time!")
                    .setCancelable(true)
                    .setNegativeButton("Close app",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Runtime.getRuntime().exit(0);
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
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        mTextViewQuestion.setText(quest);
    }

    private void checkAnswer(boolean userAnswer) {
        boolean correctAns = mQuestions[currentQuestion].isAnswerIsTrueOrNot();

        mProgressBar.setProgress(numberProgressBar, true);

        if (correctAns == userAnswer) {
            Toast.makeText(getApplicationContext(), "Thats right!", Toast.LENGTH_SHORT).show();
            score++;
            StringBuilder stringBuilder = new StringBuilder();
            mTextViewScore.setText(stringBuilder
                    .append("Score: ")
                    .append(score)
                    .append("/13"));
        } else {
            Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT).show();
        }
    }
}
