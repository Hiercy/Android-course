package com.example.volk1.scoorekeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String STATE_SCORE_1 = "Team Score 1";
    static final String STATE_SCORE_2 = "Team Score 2";

    private int score1;
    private int score2;

    private TextView mScoreText1;
    private TextView mScoreText2;

    private ImageButton mPlusTeamOne;
    private ImageButton mPlusTeamTwo;

//    private AnimationDrawable mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScoreText1 = findViewById(R.id.score1);
        mScoreText2 = findViewById(R.id.score2);

        mPlusTeamOne = findViewById(R.id.increase_team1);


        mPlusTeamTwo = findViewById(R.id.increase_team2);
//        mPlusTeamTwo.setBackgroundResource(R.drawable.select_plus_background);


        if (savedInstanceState != null) {
            score1 = savedInstanceState.getInt(STATE_SCORE_1);
            score2 = savedInstanceState.getInt(STATE_SCORE_2);

            mScoreText1.setText(String.valueOf(score1));
            mScoreText2.setText(String.valueOf(score2));
        }
    }

    public void descreaseScore(View view) {
        int viewID = view.getId();
        switch (viewID) {
            case R.id.decrease_team1:
                score1--;
                mScoreText1.setText(String.valueOf(score1));
                break;
            case R.id.decrease_team2:
                score2--;
                mScoreText2.setText(String.valueOf(score2));
                break;
        }
    }

    public void increaseScore(View view) {
        int viewID = view.getId();
        switch (viewID) {
            case R.id.increase_team1:
                score1++;
                mScoreText1.setText(String.format("%s", score1));
                break;
            case R.id.increase_team2:
                score2++;
                mScoreText2.setText(String.format("%s", score2));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // Change the label of the menu based on the state of the app.
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Check if the correct item was clicked.
        if (item.getItemId() == R.id.night_mode) {
            // Get the night mode state of the app.
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            // Set the theme mode for the restarted activity.
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            // Recreate the activity for the theme change to take effect.
            recreate();
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_SCORE_1, score1);
        outState.putInt(STATE_SCORE_2, score2);

        super.onSaveInstanceState(outState);
    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocused) {
//        super.onWindowFocusChanged(hasFocused);
//
//        mPlusTeamOne.setBackgroundResource(R.drawable.select_plus_background);
//        mAnimation = (AnimationDrawable) mPlusTeamOne.getBackground();
//        mAnimation.start();
//    }
}
