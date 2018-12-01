package com.example.volk1.codingchallenge5;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.volk1.codingchallenge5.REPLY";

    private EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mLocationEditText = findViewById(R.id.store_edittext);
    }

    public void addToList(View view) {
        String apple = "Apples";
        String orange = "Oranges";
        String banana = "Bananas";
        String bread = "Bread";
        String candy = "Candy";
        String chees = "Chees";
        String eggs = "Eggs";
        String meat = "Meat";
        String milk = "Milk";
        String water = "Water";

        Intent replyIntent = new Intent();
        int id = view.getId();
        switch (id) {
            case R.id.apple:
                replyIntent.putExtra(EXTRA_REPLY, apple);
                setResult(RESULT_OK, replyIntent);
                break;
            case R.id.orange:
                replyIntent.putExtra(EXTRA_REPLY, orange);
                setResult(RESULT_OK, replyIntent);
                break;
            case R.id.banana:
                replyIntent.putExtra(EXTRA_REPLY, banana);
                setResult(RESULT_OK, replyIntent);
                break;
            case R.id.bread:
                replyIntent.putExtra(EXTRA_REPLY, bread);
                setResult(RESULT_OK, replyIntent);
                break;
            case R.id.candy:
                replyIntent.putExtra(EXTRA_REPLY, candy);
                setResult(RESULT_OK, replyIntent);
                break;
            case R.id.chees:
                replyIntent.putExtra(EXTRA_REPLY, chees);
                setResult(RESULT_OK, replyIntent);
                break;
            case R.id.egg:
                replyIntent.putExtra(EXTRA_REPLY, eggs);
                setResult(RESULT_OK, replyIntent);
                break;
            case R.id.meat:
                replyIntent.putExtra(EXTRA_REPLY, meat);
                setResult(RESULT_OK, replyIntent);
                break;
            case R.id.milk:
                replyIntent.putExtra(EXTRA_REPLY, milk);
                setResult(RESULT_OK, replyIntent);
                break;
            case R.id.water:
                replyIntent.putExtra(EXTRA_REPLY, water);
                setResult(RESULT_OK, replyIntent);
                break;
        }
//        replyIntent.putExtra(EXTRA_REPLY, apple);

        finish();
    }

    public void findStore(View view) {
        String location = mLocationEditText.getText().toString();

        Uri uri = Uri.parse("geo:0,0?q=" + location);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Log.d("SecondActivity", "Cannot do this :)");
    }
}
