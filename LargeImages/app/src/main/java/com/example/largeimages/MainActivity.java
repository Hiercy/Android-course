package com.example.largeimages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int toggle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeImage(View view){
        if (toggle == 0) {
            view.setBackgroundResource(R.drawable.dinosaur_large);
            toggle = 1;
        } else {
            // Add code to let your app sleep for two screen refreshes
            // before switching the background to the smaller image.
            // This means that instead of refreshing the screen every 16 ms,
            // your app now refreshes every 48 ms with new content.
            // This will be reflected in the bars displayed by the
            // Profile GPU Rendering tool.
            try {
                Thread.sleep(32); // two refreshes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            view.setBackgroundResource(R.drawable.ankylo);
            toggle = 0;
        }
    }
}
