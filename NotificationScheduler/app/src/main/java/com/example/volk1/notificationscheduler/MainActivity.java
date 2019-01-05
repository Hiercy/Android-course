package com.example.volk1.notificationscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int JOB_ID = 0;

    private JobScheduler mScheduler;

    private Switch mDeviceIdle;
    private Switch mDeviceCharging;

    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDeviceIdle = findViewById(R.id.idleSwitch);
        mDeviceCharging = findViewById(R.id.chargingSwitch);

        mSeekBar = findViewById(R.id.seekBar);

        final TextView seekBarProgress = findViewById(R.id.seekBarProgress);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i > 0) {
                    seekBarProgress.setText(i + " s");
                } else {
                    seekBarProgress.setText("Not Set");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void scheduleJob(View view) {
        mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        RadioGroup radioGroup = findViewById(R.id.networkOptions);

        int selectNetwork = radioGroup.getCheckedRadioButtonId();
        int selectNetworkOption = JobInfo.NETWORK_TYPE_NONE;

        int seekBarInteger = mSeekBar.getProgress();
        boolean seekBarSet = seekBarInteger > 0;

        switch (selectNetwork) {
            case R.id.noNetwork:
                selectNetworkOption = JobInfo.NETWORK_TYPE_NONE;
                break;
            case R.id.anyNetwork:
                selectNetworkOption = JobInfo.NETWORK_TYPE_ANY;
                break;
            case R.id.wifiNetwork:
                selectNetworkOption = JobInfo.NETWORK_TYPE_UNMETERED;
                break;
        }

        ComponentName componentName = new ComponentName(
                getPackageName(),
                NotificationJobService.class.getName());

        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, componentName)
                .setRequiredNetworkType(selectNetworkOption)
                .setRequiresDeviceIdle(mDeviceIdle.isChecked())
                .setRequiresCharging(mDeviceCharging.isChecked());

        if (seekBarSet) {
            builder.setOverrideDeadline(seekBarInteger * 1000);
        }

        boolean constraintSet = (selectNetworkOption != JobInfo.NETWORK_TYPE_NONE)
                || mDeviceCharging.isChecked()
                || mDeviceIdle.isChecked()
                || seekBarSet;

        if (constraintSet) {
            JobInfo jobInfo = builder.build();
            mScheduler.schedule(jobInfo);

            Toast.makeText(this, "Job Scheduled, job will run when " +
                    "the constraints are met.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please set at least one constraint " +
                    "the constraints are met.", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelJob(View view) {
        if (mScheduler != null) {
            mScheduler.cancelAll();
            mScheduler = null;
            Toast.makeText(this, "Jobs cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
