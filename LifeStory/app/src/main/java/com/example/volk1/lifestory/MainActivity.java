package com.example.volk1.lifestory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewStory;

    private Button mButtonA1;
    private Button mButtonA2;

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewStory = findViewById(R.id.tv_story);
        mButtonA1 = findViewById(R.id.btn_red_answer);
        mButtonA2 = findViewById(R.id.btn_blue_answer);

        mButtonA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == 0) {
                    mTextViewStory.setText(R.string.T3_Story);
                    mButtonA1.setText(R.string.T3_Ans1);
                    mButtonA2.setText(R.string.T3_Ans2);
                    index = 1;
                } else if (index == 1) {
                    mTextViewStory.setText(R.string.T6_End);
                    mButtonA1.setVisibility(Button.GONE);
                    mButtonA2.setVisibility(Button.GONE);
                } else if (index == 2) {
                    mTextViewStory.setText(R.string.T3_Story);
                    mButtonA1.setText(R.string.T3_Ans1);
                    mButtonA2.setText(R.string.T3_Ans2);
                    index = 3;
                } else if (index == 3) {
                    mTextViewStory.setText(R.string.T6_End);
                    mButtonA1.setVisibility(Button.GONE);
                    mButtonA2.setVisibility(Button.GONE);
                }
            }
        });

        mButtonA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == 0) {
                    mTextViewStory.setText(R.string.T2_Story);
                    mButtonA1.setText(R.string.T2_Ans1);
                    mButtonA2.setText(R.string.T2_Ans2);
                    index = 2;
                } else if (index == 1) {
                   mTextViewStory.setText(R.string.T5_End);
                   mButtonA1.setVisibility(Button.GONE);
                   mButtonA2.setVisibility(Button.GONE);
               } else if (index == 2) {
                    mTextViewStory.setText(R.string.T4_End);
                    mButtonA1.setVisibility(Button.GONE);
                    mButtonA2.setVisibility(Button.GONE);
                } else if (index == 3) {
                    mTextViewStory.setText(R.string.T5_End);
                    mButtonA1.setVisibility(Button.GONE);
                    mButtonA2.setVisibility(Button.GONE);
                }
            }
        });
    }
}
