package com.example.volk1.drawwablebattery;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mCountLevel = 0;

    private ImageView mBattery;

    private LevelListDrawable mLevelList;
    private Drawable mDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBattery = findViewById(R.id.imageView_battery);
    }

    public void levelUp(View view) {
        mCountLevel++;
        if (mCountLevel >= 8) {
            Toast.makeText(getApplicationContext(), "Battery is full", Toast.LENGTH_SHORT).show();
            mCountLevel--;
        } else {
            mDrawable = mBattery.getDrawable();
            mLevelList = (LevelListDrawable) mDrawable;
            mLevelList.setLevel(mCountLevel);
        }
    }

    public void levelDown(View view) {
        mCountLevel--;
        if (mCountLevel < 0) {
            Toast.makeText(getApplicationContext(), "Battery is empty", Toast.LENGTH_SHORT).show();
            mCountLevel++;
        } else {
            mDrawable = mBattery.getDrawable();
            mLevelList = (LevelListDrawable) mDrawable;
            mLevelList.setLevel(mCountLevel);
        }
    }
}
