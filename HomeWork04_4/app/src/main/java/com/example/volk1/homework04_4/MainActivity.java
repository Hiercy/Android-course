package com.example.volk1.homework04_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.volk1.homework04_4.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDonutView(View view) {
        Intent intent = new Intent(MainActivity.this, DonutActivity.class);
        startActivity(intent);
    }

    public void showIceCreamView(View view) {
        Intent intent = new Intent(MainActivity.this, IceCreamActivity.class);
        startActivity(intent);
    }

    public void showFroyoView(View view) {
        Intent intent = new Intent(MainActivity.this, FroyoActivity.class);
        startActivity(intent);
    }
}
