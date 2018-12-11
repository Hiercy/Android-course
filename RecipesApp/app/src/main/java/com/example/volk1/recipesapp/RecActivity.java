package com.example.volk1.recipesapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class RecActivity extends AppCompatActivity {

    public String image;

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

            setText(text);
        }
    }

    private void setText(String message) {

        TextView text = findViewById(R.id.recActivity_textView);
        text.setText(message);

        onClick();
//        Glide.with(this)
//                .asBitmap()
//                .load(image)
//                .into(img);
    }

    private void onClick() {
        AsyncRequest ar = new AsyncRequest();
        ar.execute();
    }

    public class AsyncRequest extends AsyncTask<Void, String, Bitmap> {
        @Override
        protected Bitmap doInBackground(Void... voids) {
            InputStream in;
            Bitmap bmp = null;

            image = getIntent().getStringExtra("image");
            try {
                in = new URL(image).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            ImageView img = findViewById(R.id.imageView);
            img.setImageBitmap(result);
        }
    }
}
