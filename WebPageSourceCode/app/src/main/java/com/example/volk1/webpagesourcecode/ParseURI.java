package com.example.volk1.webpagesourcecode;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;


public class ParseURI extends AsyncTaskLoader<String> {
    private String mCodePage;

    public ParseURI(@NonNull Context context, String mCodePage) {
        super(context);
        this.mCodePage = mCodePage;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getPage(mCodePage);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        forceLoad();
    }
}
