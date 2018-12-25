package com.example.volk1.webpagesourcecode;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    static String getPage(String mCodePage) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String line;

        try {
            Uri uri = Uri.parse(mCodePage);

            URL url = new URL(uri.toString());

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");

                if (builder.length() == 0) {
                    return null;
                }

                mCodePage = builder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mCodePage;
    }
}
