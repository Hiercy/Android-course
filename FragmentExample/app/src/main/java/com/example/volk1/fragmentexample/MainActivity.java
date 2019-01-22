package com.example.volk1.fragmentexample;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    static final String STATE_FRAGMENT = "state_of_fragment";

    private Button mButton;
    private boolean isFragmentDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.open_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFragmentDisplayed) {
                    displayFragment();
                } else {
                    closeFragment();
                }
            }
        });

        if (savedInstanceState != null) {
            isFragmentDisplayed =
                    savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (isFragmentDisplayed) {
                // If the fragment is displayed, change button to "close".
                mButton.setText(R.string.close);
            }
        }
    }

    public void displayFragment() {
        SimpleFagment simpleFagment = SimpleFagment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransactionOne = fragmentManager.beginTransaction();

        fragmentTransactionOne
                .add(R.id.fragment_container, simpleFagment)
                .addToBackStack(null)
                .commit();

        mButton.setText(R.string.close);
        isFragmentDisplayed = true;
    }

    public void closeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        SimpleFagment simpleFagmentOne = (SimpleFagment) fragmentManager.findFragmentById(R.id.fragment_container);

        if (simpleFagmentOne != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFagmentOne).commit();
        }

        mButton.setText(R.string.open);
        isFragmentDisplayed = false;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the state of the fragment (true=open, false=closed).
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }
}