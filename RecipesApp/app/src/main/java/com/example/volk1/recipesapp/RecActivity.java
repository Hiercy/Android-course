package com.example.volk1.recipesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class RecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec);

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("recep_full") && getIntent().hasExtra("image")) {
            String text = getIntent().getStringExtra("recep_full");
            String image = getIntent().getStringExtra("image");

            setText(text, image);
        }
    }

    private void setText(String message, String image) {
        TextView text = findViewById(R.id.recActivity_textView);
        text.setText(message);

        ImageView img = findViewById(R.id.imageView);
//        img.setImageResource(R.drawable.sand);
        Glide.with(this)
                .asBitmap()
                .load(image)
                .into(img);
    }
}
