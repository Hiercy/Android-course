package com.example.volk1.wheatherapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.net.URL;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private static final String OPEN_WEATHER = "api.openweathermap.org/data/2.5/weather?q=%s&units=metric";
    private static final String APP_ID = "d99db4c4b8343fa2a97ca15896a38186";

    String LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;

    private ImageButton mButtonChangeCity;

    private TextView mTextViewDegree;
    private TextView mTextViewCountry;
    private TextView mTextViewStatus;

    private ImageView mImageViewStatusImage;

    LocationManager mLocationManager;
    LocationListener mLocationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonChangeCity = findViewById(R.id.change_city);
        mTextViewDegree = findViewById(R.id.degree);
        mTextViewCountry = findViewById(R.id.country);
        mTextViewStatus = findViewById(R.id.status_textView);
        mImageViewStatusImage = findViewById(R.id.status_image);

        mButtonChangeCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectCityActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null) {
            String city = data.getStringExtra("city");
            mTextViewCountry.setText(city);
        }
    }

//    public void getJSON(RequestParams params) {
//        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
//
//        asyncHttpClient.get(OPEN_WEATHER, params, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//
//
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
//
//
//            }
//        });
//    }

    @Override
    public void onResume() {
        super.onResume();

        getWeatherForCurrentLocation();
    }

    private void getWeatherForCurrentLocation() {
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
    }
}
