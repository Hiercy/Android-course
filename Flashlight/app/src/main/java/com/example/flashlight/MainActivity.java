package com.example.flashlight;

import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton mImageButton;

    private boolean flashLightStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final boolean hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        mImageButton = findViewById(R.id.ib_flash_light);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasCameraFlash) {
                    if (flashLightStatus) {
                        flashLightOff();
                    } else {
                        flashLightOn();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "No flash avalible",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void flashLightOn() {
        CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            assert cameraManager != null;
            String camera = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(camera, true);
            flashLightStatus = true;
            mImageButton.setImageResource(R.drawable.on);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void flashLightOff() {
        CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            assert cameraManager != null;
            String camera = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(camera, false);
            flashLightStatus = false;
            mImageButton.setImageResource(R.drawable.off);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
