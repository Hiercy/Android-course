package com.example.volk1.simpleasynctask;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    private WeakReference<TextView> mTextView;
    private WeakReference<TextView> mProgressBar;

    private WeakReference<ProgressBar> mProgressBar2;

    SimpleAsyncTask(TextView textView, TextView progress, ProgressBar progressBar2) {
        mTextView = new WeakReference<>(textView);
        mProgressBar = new WeakReference<>(progress);
        mProgressBar2 = new WeakReference<>(progressBar2);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);
        int s = n * 200;
        int i = 0;
//        int j = 100;

        try {
            if (s == 0) {
                publishProgress(100);
                return "Awake at last after sleeping for " + s + " milliseconds!";
            }
//            while (i <= j) {
//                publishProgress(i);
//                i++;
//                Thread.sleep(1);
//            }
            if (i != s / 4) {
                for (i = 0; i < 25; i++) {
                    publishProgress(i);
                    i++;
                    Thread.sleep(1);
                }
            }
            if (i != s / 3) {
                for (i = 25; i < 33; i++) {
                    publishProgress(i);
                    i++;
                    Thread.sleep(1);
                }
            }
            if (i != s / 2) {
                for (i = 33; i < 50; i++) {
                    publishProgress(i);
                    i++;
                    Thread.sleep(1);
                }
            }
            if (i != s) {
                for (i = 50; i <= 100; i++) {
                    publishProgress(i);
                    i++;
                    Thread.sleep(1);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
        mProgressBar.get().setText("progress: " + (progress[0]) + "%");
        mProgressBar2.get().setVisibility(View.VISIBLE);
        mProgressBar2.get().setProgress(progress[0]);
    }

}
