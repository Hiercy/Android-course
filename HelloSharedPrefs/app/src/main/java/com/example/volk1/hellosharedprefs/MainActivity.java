package com.example.volk1.hellosharedprefs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Intent mIntent;

    private SharedPreferences mSharedPreferences;
    private String sharedPrefFile = "com.example.volk1.hellosharedprefs";

    // Current count
    private int mCount = 0;
    // Current background color
    private int mColor;
    // Text view to display both count and color
    private TextView mShowCountTextView;

    // Key for current count
    private final String COUNT_KEY = "count";
    // Key for current color
    private final String COLOR_KEY = "color";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        // Initialize views
        mShowCountTextView = findViewById(R.id.count_textview);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        mCount = mSharedPreferences.getInt(COUNT_KEY, 0);
        mShowCountTextView.setText(String.format("%s", mCount));
        mColor = mSharedPreferences.getInt(COLOR_KEY, R.color.default_background);
        mShowCountTextView.setBackgroundColor(mColor);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putInt(COUNT_KEY, mCount);
        editor.putInt(COLOR_KEY, mColor);
        editor.apply();
    }

    /**
     * Handles the onClick for the background color buttons. Gets background
     * color of the button that was clicked, and sets the TextView background
     * to that color.
     *
     * @param view The view (Button) that was clicked.
     */
    public void changeBackground(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        mShowCountTextView.setBackgroundColor(color);
        mColor = color;
    }

    /**
     * Handles the onClick for the Count button. Increments the value of the
     * mCount global and updates the TextView.
     *
     * @param view The view (Button) that was clicked.
     */
    public void countUp(View view) {
        mCount++;
        mShowCountTextView.setText(String.format("%s", mCount));
    }

    public void openSetting(View view) {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }
}
