package com.example.volk1.workwithdatabase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.volk1.workwithdatabase.R;

import static com.example.volk1.workwithdatabase.MainActivity.EXTRA_DATA_UPDATE_ANSWER;
import static com.example.volk1.workwithdatabase.MainActivity.EXTRA_DATA_UPDATE_ID;
import static com.example.volk1.workwithdatabase.MainActivity.EXTRA_DATA_UPDATE_QUESTION;
import static com.example.volk1.workwithdatabase.MainActivity.EXTRA_DATA_UPDATE_TITLE;

public class NewQuestionActivity extends AppCompatActivity {

    public static final String REPLY_ID = "id";
    public static final String REPLY_TITLE = "title";
    public static final String REPLY_DESC = "desc";
    public static final String REPLY_ANSWER = "answer";

    private EditText mEditTextTitle;
    private EditText mEditTextDescription;
    private EditText mEditTextAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_question);

        final int id = -1;

        mEditTextTitle = findViewById(R.id.editText_title);
        mEditTextDescription = findViewById(R.id.editText_new_question_desc);
        mEditTextAnswer = findViewById(R.id.editText_new_answer);

        final Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String title = extras.getString(EXTRA_DATA_UPDATE_TITLE, "");
            String question = extras.getString(EXTRA_DATA_UPDATE_QUESTION, "");
            String answer = extras.getString(EXTRA_DATA_UPDATE_ANSWER, "");
//            int id = extras.getInt(EXTRA_DATA_UPDATE_ID, -1);

            if (!title.isEmpty()
                    || !question.isEmpty()
                    || !answer.isEmpty()) {
                mEditTextTitle.setText(title);
                mEditTextTitle.setSelection(title.length());
                mEditTextTitle.requestFocus();

                mEditTextDescription.setText(question);
                mEditTextAnswer.setText(answer);
            }
        }

        Button button = findViewById(R.id.create_new_question);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();

                String title = mEditTextTitle.getText().toString().replaceAll("\\s+", " ").trim();
                String questionDesc = mEditTextDescription.getText().toString().replaceAll("\\s+", " ").trim();
                String answer = mEditTextAnswer.getText().toString().replaceAll("\\s+", " ").trim();

                if (TextUtils.isEmpty(title)
                        || TextUtils.isEmpty(questionDesc)
                        || TextUtils.isEmpty(answer)) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    if (title.equals("") || questionDesc.equals("") || answer.equals("")) {
                        setResult(RESULT_CANCELED, replyIntent);
                    } else {
                        replyIntent.putExtra(REPLY_TITLE, title);
                        replyIntent.putExtra(REPLY_DESC, questionDesc);
                        replyIntent.putExtra(REPLY_ANSWER, answer);

                        if (extras != null && extras.containsKey(EXTRA_DATA_UPDATE_ID)) {
                            int id = extras.getInt(EXTRA_DATA_UPDATE_ID, -1);
                            if (id != -1) {
                                replyIntent.putExtra(REPLY_ID, id);
                            }
                        }
                        setResult(RESULT_OK, replyIntent);
                    }
                }
                finish();
            }
        });
    }
}
