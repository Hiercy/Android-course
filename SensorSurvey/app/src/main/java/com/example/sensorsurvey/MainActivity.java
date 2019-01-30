package com.example.sensorsurvey;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;

    // Individual light and proximity sensors.
    private Sensor mSensorProximity;
    private Sensor mSensorLight;

    // TextViews to display current sensor values
    private TextView mTextSensorLight;
    private TextView mTextSensorProximity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextSensorLight = findViewById(R.id.label_light);
        mTextSensorProximity = findViewById(R.id.label_proximity);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        String sensor_not_found = getResources().getString(R.string.error_no_sensor);

        if (mSensorProximity == null) {
            mTextSensorProximity.setText(sensor_not_found);
        }
        if (mSensorLight == null) {
            mTextSensorLight.setText(sensor_not_found);
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
                break;
            case Sensor.TYPE_PROXIMITY:
                mTextSensorProximity.setText(getResources().getString(
                        R.string.label_proximity, currentVal));
                break;
            default:
                break;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
