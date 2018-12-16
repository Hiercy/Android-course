package com.example.volk1.fouranimatedimage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AnimationClass {

//    private ImageView mRedCircle;
//    private ImageView mBlueLine;
//    private ImageView mYellowSquare;
//    private ImageView mAndroid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView mRedCircle = findViewById(R.id.circle);
        ImageView mBlueLine = findViewById(R.id.blueLine);
        ImageView mYellowSquare = findViewById(R.id.yellowBlock);
        ImageView mAndroid = findViewById(R.id.android);

        explodeTransaction(this, mRedCircle);
        fadeTransaction(this, mYellowSquare);
        rotateTransaction(mBlueLine);
        switchAnimation(mAndroid, mBlueLine, new Intent(this, SecondActivity.class), this);
    }
}
