package com.example.sensorsurvey;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;

    // Individual light and proximity sensors.
    private Sensor mSensorProximity;
    private Sensor mSensorLight;
    private Sensor mSensorHumidity;

    // TextViews to display current sensor values
    private TextView mTextSensorLight;
    private TextView mTextSensorProximity;
    private TextView mTextSensorHumidity;

    // ImageView to display changing image
    private ImageView mImageView;
    ViewGroup.LayoutParams mParams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.iv_android);
        mParams = mImageView.getLayoutParams();

        mTextSensorLight = findViewById(R.id.label_light);
        mTextSensorProximity = findViewById(R.id.label_proximity);
        mTextSensorHumidity = findViewById(R.id.label_humidity);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorHumidity = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        String sensor_not_found = getResources().getString(R.string.error_no_sensor);

        if (mSensorProximity == null) {
            mTextSensorProximity.setText(sensor_not_found);
        }
        if (mSensorLight == null) {
            mTextSensorLight.setText(sensor_not_found);
        }
        if (mSensorHumidity == null) {
            mTextSensorHumidity.setText(R.string.error_no_sensor);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mSensorLight != null) {
            mSensorManager.registerListener(this, mSensorLight, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorProximity != null) {
            mSensorManager.registerListener(this, mSensorProximity, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorHumidity != null) {
            mSensorManager.registerListener(this, mSensorHumidity, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        int sensorType = sensorEvent.sensor.getType();
        float currentVal = sensorEvent.values[0];

        switch (sensorType) {
            case Sensor.TYPE_LIGHT:
                mTextSensorLight.setText(getResources().getString(
                        R.string.label_light, currentVal));

                if (currentVal > 0) {
                    int clr = (int) currentVal;
                    setActivityBackgroundColor(Color.rgb(clr, clr, clr));
                } else {
                    setActivityBackgroundColor(Color.RED);
                }
                break;
            case Sensor.TYPE_PROXIMITY:
                mTextSensorProximity.setText(getResources().getString(
                        R.string.label_proximity, currentVal));
                mParams.width = (int) currentVal;
                mParams.height = (int) currentVal;
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                mTextSensorHumidity.setText(getResources().getString(
                        R.string.label_humidity, currentVal));
                break;
            default:
                break;
        }

    }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
