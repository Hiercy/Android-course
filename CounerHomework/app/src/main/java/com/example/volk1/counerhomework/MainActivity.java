package com.example.volk1.counerhomework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static int count = 0;
    private TextView mTextCount;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "onCreate");
        mTextCount = findViewById(R.id.text_count);
        mEditText = findViewById(R.id.edit_text);

        if (savedInstanceState != null)
            mTextCount.setText(savedInstanceState.getString("reply_text"));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("reply_text", mTextCount.getText().toString());
    }

    public void incrementCounter(View view) {
        count++;
        mTextCount.setText(String.format("%s", count));
    }

    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }
}
