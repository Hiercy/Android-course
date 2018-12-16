package com.example.volk1.fouranimatedimage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class SecondActivity extends AnimationClass {

//    private ImageView mRedCircle;
//    private ImageView mBlueLine;
//    private ImageView mYellowSquare;
//    private ImageView mAndroid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialize the views.
        ImageView mRedCircle = findViewById(R.id.circle);
        ImageView mBlueLine = findViewById(R.id.blueLine);
        ImageView mYellowSquare = findViewById(R.id.yellowBlock);
        ImageView mAndroid = findViewById(R.id.android);

        // Set the methods from the base class to the appropriate ImageViews.
        explodeTransaction(this, mRedCircle);
        fadeTransaction(this, mYellowSquare);
        rotateTransaction(mBlueLine);
        switchAnimation(mAndroid, mBlueLine, new Intent(this, SecondActivity.class), this);
    }
}
