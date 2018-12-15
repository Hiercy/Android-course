package com.example.android.materialme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private ArrayList<String> mText;
    private SportsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView sportsImage = findViewById(R.id.sportsImageDetail);
        TextView sportsTitle = findViewById(R.id.titleDetail);
        TextView sportsInfo = findViewById(R.id.subTitleDetail);

        // getStringExtra(String) - gets only text
        // getIntExtra(name, defaultValue) - Retrieve extended data from the intent
        Glide.with(this).load(getIntent().getIntExtra("image_resources", 0)).into(sportsImage);

        sportsTitle.setText(getIntent().getStringExtra("title"));
        sportsInfo.setText(getIntent().getStringExtra("text"));

    }
}
