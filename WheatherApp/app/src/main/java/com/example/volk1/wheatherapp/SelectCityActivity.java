package com.example.volk1.wheatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class SelectCityActivity extends AppCompatActivity {

    private EditText mEditText;
    private ImageButton mImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        mEditText = findViewById(R.id.choose_a_city);
        mImageButton = findViewById(R.id.back);

        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handler = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String city = mEditText.getText().toString();

                    Intent intent = new Intent();

                    intent.putExtra("city", city);

                    setResult(Activity.RESULT_OK, intent);

                    handler = true;
                    finish();
                }
                return handler;
            }
        });

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
