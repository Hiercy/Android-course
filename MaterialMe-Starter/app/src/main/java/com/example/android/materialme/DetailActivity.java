package com.example.android.materialme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    public static final String VIEW_NAME_HEADER_IMAGE = "image_transition";
    public static final String VIEW_NAME_HEADER_TITLE = "title_transition";
    public static final String VIEW_NAME_HEADER_TEXT  = "text_transition";

    private SportsAdapter mAdapter;

    private ImageView sportsImage;
    private TextView sportsTitle;
    private TextView sportsInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        sportsImage = findViewById(R.id.sportsImageDetail);
        sportsTitle = findViewById(R.id.titleDetail);
        sportsInfo = findViewById(R.id.subTitleDetail);

//        getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transation));

        // getStringExtra(String) - gets only text
        // getIntExtra(name, defaultValue) - Retrieve extended data from the intent
        Glide.with(this).load(getIntent().getIntExtra("image_resources", 0)).into(sportsImage);
        sportsTitle.setText(getIntent().getStringExtra("title"));
        sportsInfo.setText(getIntent().getStringExtra("text"));
    }
}
