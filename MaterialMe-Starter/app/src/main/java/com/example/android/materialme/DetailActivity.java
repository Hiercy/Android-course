package com.example.android.materialme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView sportsImage = findViewById(R.id.sportsImageDetail);
        TextView sportsTitle = findViewById(R.id.titleDetail);

        sportsTitle.setText(getIntent().getStringExtra("title"));

        // getStringExtra(String) - gets only text
        // getIntExtra(name, defaultValue) - Retrieve extended data from the intent
        Glide.with(this).load(getIntent().getIntExtra("image_resources", 0)).into(sportsImage);
    }
}
