package com.example.volk1.recipesapp;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec);

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("recep_full")) {
            String text = getIntent().getStringExtra("recep_full");
            setText(text);
        }
    }

    private void setText(String message) {
        TextView text = findViewById(R.id.recActivity_textView);
        text.setText(message);
    }
}
