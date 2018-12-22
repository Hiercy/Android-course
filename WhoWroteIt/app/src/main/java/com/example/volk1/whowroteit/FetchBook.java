package com.example.volk1.whowroteit;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class FetchBook extends AsyncTask<String, Void, String> {

    private WeakReference<TextView> mTitleText;
    private WeakReference<TextView> mAuthorText;

    public FetchBook(TextView titleText, TextView authorText) {
        mTitleText = new WeakReference<>(titleText);
        mAuthorText = new WeakReference<>(authorText);
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getBookInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            int i = 0;
            String title = null;
            String author = null;

            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            while (i < itemsArray.length() && (author == null && title == null)) {
                // Get the current item information
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                try {
                    title = volumeInfo.getString("title");
                    author = volumeInfo.getString("authors");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i++;
            }

            if (title != null && author != null) {
                mTitleText.get().setText(title);
                mAuthorText.get().setText(author);
            } else {
                mTitleText.get().setText(R.string.noResult);
                mAuthorText.get().setText("");
            }

        } catch (JSONException e) {
            e.printStackTrace();
            mTitleText.get().setText(R.string.noResult);
            mAuthorText.get().setText("");
            e.printStackTrace();
        }


    }
}
