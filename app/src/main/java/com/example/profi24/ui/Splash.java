package com.example.profi24.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

import com.example.profi24.R;

public class Splash extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.activity_splash2);
                new CountDownTimer(3000,1000){
                    @Override
                    public void onTick(long l) {

                    }
                    @Override
                    public void onFinish() {
                        startActivity(new Intent(Splash.this, OnBoarding.class));
                        finish();
                    }
                }.start();
            }
        }, SPLASH_DISPLAY_LENGTH);






    }
}