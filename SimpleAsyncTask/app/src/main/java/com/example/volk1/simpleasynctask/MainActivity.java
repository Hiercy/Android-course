package com.example.volk1.simpleasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TEXT_STATE = "currentState";

    private TextView textView = null;
    private TextView progressBar;

    private ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView1);
        progressBar = findViewById(R.id.progress_bar);
        progressBar2 = findViewById(R.id.progressBar22);

        if (savedInstanceState != null) {
            textView.setText(savedInstanceState.getString(TEXT_STATE));
            progressBar.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    public void startTask(View view) {
        textView.setText("Napping...");
        new SimpleAsyncTask(textView, progressBar, progressBar2).execute();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(TEXT_STATE, textView.getText().toString());
        outState.putString(TEXT_STATE, progressBar.getText().toString());
    }
}
