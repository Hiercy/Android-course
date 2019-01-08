package com.example.volk1.musicapp;

import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private SoundPool mSoundPool;

    int note1_c;
    int note2_d;
    int note3_e;
    int note4_f;
    int note5_g;
    int note6_a;
    int note7_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSoundPool = new SoundPool.Builder()
                .setMaxStreams(7)
                .build();

        note1_c = mSoundPool.load(getApplicationContext(), R.raw.note1_c, 1);
        note2_d = mSoundPool.load(getApplicationContext(), R.raw.note2_d, 1);
        note3_e = mSoundPool.load(getApplicationContext(), R.raw.note3_e, 1);
        note4_f = mSoundPool.load(getApplicationContext(), R.raw.note4_f, 1);
        note5_g = mSoundPool.load(getApplicationContext(), R.raw.note5_g, 1);
        note6_a = mSoundPool.load(getApplicationContext(), R.raw.note6_a, 1);
        note7_b = mSoundPool.load(getApplicationContext(), R.raw.note7_b, 1);
    }

    public void soundC(View view) {
        mSoundPool.play(note1_c,
                1.0f,
                1.0f,
                0,
                0,
                1.0f);
    }

    public void soundD(View view) {
        mSoundPool.play(note2_d,
                1.0f,
                1.0f,
                0,
                0,
                1.0f);
    }

    public void soundE(View view) {
        mSoundPool.play(note3_e,
                1.0f,
                1.0f,
                0,
                0,
                1.0f);
    }

    public void soundF(View view) {
        mSoundPool.play(note4_f,
                1.0f,
                1.0f,
                0,
                0,
                1.0f);
    }

    public void soundG(View view) {
        mSoundPool.play(note5_g,
                1.0f,
                1.0f,
                0,
                0,
                1.0f);
    }

    public void soundA(View view) {
        mSoundPool.play(note6_a,
                1.0f,
                1.0f,
                0,
                0,
                1.0f);
    }

    public void soundB(View view) {
        mSoundPool.play(note7_b,
                1.0f,
                1.0f,
                0,
                0,
                1.0f);
    }
}