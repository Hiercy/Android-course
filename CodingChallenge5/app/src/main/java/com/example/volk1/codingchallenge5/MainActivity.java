package com.example.volk1.codingchallenge5;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Class name for Log tag
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    // Unique tag required for the intent extra
    public static final String EXTRA_MESSAGE = "com.example.volk1.codingchallenge5.MESSAGE";
    // Unique tag for the intent reply
    public static final int TEXT_REQUEST = 1;

    private static int count = 10;

    private TextView mApple;
    private TextView mOrange;
    private TextView mBanana;
    private TextView mBread;
    private TextView mCandy;
    private TextView mChees;
    private TextView mEggs;
    private TextView mMeat;
    private TextView mMilk;
    private TextView mWater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApple = findViewById(R.id.txtview_1);
        mOrange = findViewById(R.id.txtview_2);
        mBanana = findViewById(R.id.txtview_3);
        mBread = findViewById(R.id.txtview_4);
        mCandy = findViewById(R.id.txtview_5);
        mChees = findViewById(R.id.txtview_6);
        mEggs = findViewById(R.id.txtview_7);
        mMeat = findViewById(R.id.txtview_8);
        mMilk = findViewById(R.id.txtview_9);
        mWater = findViewById(R.id.txtview_10);

        // Restore the saved state.
        // See onSaveInstanceState() for what gets saved.
        if (savedInstanceState != null) {
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");
            // Show both the header and the message views. If isVisible is
            // false or missing from the bundle, use the default layout.
            if (isVisible) {
                mApple.setText(savedInstanceState.getString("reply_text"));
                mApple.setVisibility(View.VISIBLE);

                mOrange.setText(savedInstanceState.getString("reply_text"));
                mOrange.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mApple.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text", mApple.getText().toString());
        }
    }

    public void addItem(View view) {
        Log.d(LOG_TAG, "Button clicked!");

        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    private boolean isZero() {
        return count == 0;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);

                switch (count) {
                    case 10:
                        mApple.setText(reply);
                        mApple.setVisibility(View.VISIBLE);
                        count--;
                        break;
                    case 9:
                        mOrange.setText(reply);
                        mOrange.setVisibility(View.VISIBLE);
                        count--;
                        break;
                    case 8:
                        mBanana.setText(reply);
                        mBanana.setVisibility(View.VISIBLE);
                        count--;
                        break;
                    case 7:
                        mBread.setText(reply);
                        mBread.setVisibility(View.VISIBLE);
                        count--;
                        break;
                    case 6:
                        mCandy.setText(reply);
                        mCandy.setVisibility(View.VISIBLE);
                        count--;
                        break;
                    case 5:
                        mChees.setText(reply);
                        mChees.setVisibility(View.VISIBLE);
                        count--;
                        break;
                    case 4:
                        mEggs.setText(reply);
                        mEggs.setVisibility(View.VISIBLE);
                        count--;
                        break;
                    case 3:
                        mMeat.setText(reply);
                        mMeat.setVisibility(View.VISIBLE);
                        count--;
                        break;
                    case 2:
                        mMilk.setText(reply);
                        mMilk.setVisibility(View.VISIBLE);
                        count--;
                        break;
                    case 1:
                        mWater.setText(reply);
                        mWater.setVisibility(View.VISIBLE);
                        count--;
                        break;
                    default:
                        for (int i = 10; i > 0; i--) {
                            if (count <= 0)
                                count = i;
                        }
                        break;
                }
            }
        }
    }
}
