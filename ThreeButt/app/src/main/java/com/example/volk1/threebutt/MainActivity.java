package com.example.volk1.threebutt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

//    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE";

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, SecondActivity.class);
    }

    public void btnThree(View view) {
        String message = "Button Three Clicked";
//        Log.d(LOG_TAG, "BUTTON THREE CLICKED");
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void btnTwo(View view) {
        String message = "Button Two Clicked";
//        Log.d(LOG_TAG, "BUTTON TWO CLICKED");
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void btnOne(View view) {
        String message = "Button One Clicked";
//        Log.d(LOG_TAG, "BUTTON CLICKED");
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    // Need to click two times, idk why
//    public void clicked(View view) {
//        Button btnOne = findViewById(R.id.btn_one);
//
//        btnOne.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String message = "Button One";
//                intent.putExtra(EXTRA_MESSAGE, message);
//                startActivity(intent);
//            }
//        });
//    }
}
