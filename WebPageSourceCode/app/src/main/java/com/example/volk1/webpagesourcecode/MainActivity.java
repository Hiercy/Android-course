package com.example.volk1.webpagesourcecode;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, LoaderManager.LoaderCallbacks<String> {

    private Spinner mSpinner;
    private EditText mEditText;
    private TextView mTextView;

    private String spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = findViewById(R.id.spinner);
        mEditText = findViewById(R.id.editText);
        mTextView = findViewById(R.id.textView_inside);

        mSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
    }

    public void gePage(View view) {
        String queryString = spinner + mEditText.getText().toString();
        int pattern = mEditText.getText().toString().length();

        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager != null) {
            manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected() && pattern != 0) {
            Bundle bundle = new Bundle();
            bundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, bundle, this);

            mTextView.setText("Loading...");
        } else {
            if (pattern == 0) {
                mTextView.setText("Cannot open this url. Check the input and try again.");
            } else {
                mTextView.setText("Check your internet connection and try again.");
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String[] choose = getResources().getStringArray(R.array.type);
        spinner = choose[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Do nothing
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        String queryString = null;
        if (bundle != null) {
            queryString = bundle.getString("queryString");
        }
        return new ParseURI(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        mTextView.setText(s);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // Do nothing
    }
}
