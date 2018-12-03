package com.example.volk1.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);
        mShareTextEditText = findViewById(R.id.share_edittext);
    }

    public void openWebsite(View view) {
        String url = mWebsiteEditText.getText().toString();

        // Parse the URI and create the intent.
        Uri webpage = Uri.parse(url);
        Intent intet = new Intent(Intent.ACTION_VIEW, webpage);

        // Find an activity to hand the intent and start that activity.
        if (intet.resolveActivity(getPackageManager()) != null)
            startActivity(intet);
        else
            Log.d("ImplicitIntents", "Can't handle this!");
    }

    public void openLocation(View view) {
        String loc = mLocationEditText.getText().toString();

        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Log.d("ImplicitIntents", "Can't handle this intent!");
    }

    public void shareText(View view) {
        String txt = mShareTextEditText.getText().toString();

        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(txt).
                startChooser();
    }

    public void takePicture(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Log.d("ImplicitIntents", "Can't handle this intent!");
    }
}