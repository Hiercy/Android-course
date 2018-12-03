package com.example.volk1.keyboarddialphone;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.phone_edittext);
        if (editText != null)
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    boolean handler = false;
                    if (i == EditorInfo.IME_ACTION_SEND) {
                        dialNumber();
                        handler = true;
                    }
                    return handler;
                }
            });
    }

    private void dialNumber() {
        EditText editText = findViewById(R.id.phone_edittext);
        String phoneNum = null;

        // concatenate "tel: " with the phone number string.
        if (editText != null)
            phoneNum = "tel:" + editText.getText().toString();

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(phoneNum));

        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Log.d("ImplicitIntent", "Cannot start");
    }
}
