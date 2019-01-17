package com.example.volk1.workwithdatabase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.volk1.workwithdatabase.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView mTextViewTitle = findViewById(R.id.detail_title);
        TextView mTextViewDesc = findViewById(R.id.detail_question_description);

        final Intent intent = getIntent();
        mTextViewTitle.setText(intent.getStringExtra("title"));
        mTextViewDesc.setText(intent.getStringExtra("question"));

        final Button button = findViewById(R.id.check_answer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = intent.getStringExtra("answer");
                checkAnswer(answer);
            }
        });
    }

    private void checkAnswer(String answer) {
        EditText mEditTextAnswer = findViewById(R.id.answer);
        String userInput = mEditTextAnswer.getText().toString();

        if (userInput.equals(answer)) {
            displayToast("You're Right! Congrats!");
        } else {
            displayToast("Sorry, that's wrong answer :(. Try again!");
        }
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_SHORT).show();
    }
}
