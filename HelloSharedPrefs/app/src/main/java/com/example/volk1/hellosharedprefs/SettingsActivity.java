package com.example.volk1.hellosharedprefs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    private String sharedPrefFile = "com.example.volk1.hellosharedprefs";

    // Current count
    private int mCount = 0;
    // Current background color
    private int mColor;

    // Key for current count
    private final String COUNT_KEY = "count";
    // Key for current color
    private final String COLOR_KEY = "color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void savePreferences(View view) {
        Toast.makeText(getApplicationContext(),
                R.string.saved,
                Toast.LENGTH_SHORT).show();
    }

    public void resetPreferences(View view) {
        mSharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        SharedPreferences.Editor editor = mSharedPreferences.edit();

        RadioGroup radioGroup = findViewById(R.id.preference_options);

        int selectOptions = radioGroup.getCheckedRadioButtonId();

        switch (selectOptions) {
            case R.id.delete_count:
                mCount = 0;

                editor.remove(COUNT_KEY);
                editor.putInt(COUNT_KEY, mCount);
                editor.apply();
                break;
            case R.id.delete_color:
                mColor = ContextCompat.getColor(this, R.color.default_background);

                editor.remove(COLOR_KEY);
                editor.putInt(COLOR_KEY, mColor);
                editor.apply();
                break;
            case R.id.delete_both:
                mCount = 0;
                mColor = ContextCompat.getColor(this, R.color.default_background);

                editor.putInt(COUNT_KEY, mCount);
                editor.putInt(COLOR_KEY, mColor);
                editor.clear();
                editor.apply();
                break;
        }
        Toast.makeText(getApplicationContext(),
                R.string.data_cleared,
                Toast.LENGTH_SHORT).show();
    }
}
