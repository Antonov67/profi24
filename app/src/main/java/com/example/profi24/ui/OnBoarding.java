package com.example.profi24.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.profi24.R;
import com.example.profi24.ui.data.OnBoardingRecord;

import java.util.ArrayDeque;
import java.util.Queue;

import kotlinx.coroutines.internal.ArrayQueue;

public class OnBoarding extends AppCompatActivity {

    //Создадим очередь для экранов
    ArrayDeque<OnBoardingRecord> deque = new ArrayDeque<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        //заполним очередь
        initDeque();

    }

    private void initDeque() {
        OnBoardingRecord record = new OnBoardingRecord(R.drawable.on_board_1,"Quick Delivery At Your Doorstep", "Enjoy quick pick-up and delivery to your destination");
        deque.offer(record);
        record = new OnBoardingRecord(R.drawable.on_board_2,"Flexible Payment", "Different modes of payment either before and after delivery without stress");
        deque.offer(record);
        record = new OnBoardingRecord(R.drawable.on_board_3,"Real-time Tracking", "Track your packages/items from the comfort of your home till final destination");
        deque.offer(record);

    }


}
