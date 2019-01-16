package com.example.volk1.workwithdatabase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.volk1.workwithdatabase.R;
import com.example.volk1.workwithdatabase.roomDB.random_id.UniqueID;

public class NewQuestionActivity extends AppCompatActivity {

    public static final String REPLY_TITLE = "title";
    public static final String REPLY_DESC = "desc";

    private EditText mEditTextTitle;
    private EditText mEditTextDiscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_question);

        mEditTextTitle = findViewById(R.id.editText_title);
        mEditTextDiscription = findViewById(R.id.editText_new_question_desc);

        Button button = findViewById(R.id.create_new_question);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditTextTitle.getText()) || TextUtils.isEmpty(mEditTextDiscription.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String title = mEditTextTitle.getText().toString();
                    String questionDesc = mEditTextDiscription.getText().toString();

                    replyIntent.putExtra(REPLY_TITLE, title);
                    replyIntent.putExtra(REPLY_DESC, questionDesc);

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
