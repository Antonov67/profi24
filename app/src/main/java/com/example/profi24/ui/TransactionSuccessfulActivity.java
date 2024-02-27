package com.example.profi24.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.profi24.R;

import java.util.Random;

public class TransactionSuccessfulActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView, trackNumber;

    String track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_successful);

        imageView = findViewById(R.id.imageView6);
        textView = findViewById(R.id.textView14);
        trackNumber = findViewById(R.id.textView15);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        imageView.startAnimation(animation);

        //генерация трек-номера

        track = "";
        for (int i = 0; i < 4; i++) {
            Random r = new Random();
            int x = r.nextInt(8999) + 1000;
            track += x + "-";
        }

        track = track.substring(0, track.length() - 1);
        track = "R-" + track;

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                trackNumber.setText(track);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.setImageResource(R.drawable.galka);
                textView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
}