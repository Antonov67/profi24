package com.example.profi24.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.profi24.R;
import com.example.profi24.ui.data.OnBoardingRecord;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayDeque;




public class OnBoarding extends AppCompatActivity {

    ImageView imageView;
    TextView title, text, t1, t2;
    MaterialButton skipButton, nextButton, signupButton;

    //Создадим очередь для экранов
    ArrayDeque<OnBoardingRecord> deque = new ArrayDeque<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        //
        skipButton = findViewById(R.id.skip_button);
        nextButton = findViewById(R.id.next_button);
        signupButton = findViewById(R.id.sign_up_onboarding);
        imageView = findViewById(R.id.imageOnBoard);
        text = findViewById(R.id.textOnBoard);
        title = findViewById(R.id.textTitleOnBoard);
        t1 = findViewById(R.id.textView);
        t2 = findViewById(R.id.textView2);

        //заполним очередь
        initDeque();

        //заполним первый экран первым элементом очереди и удалим его из очереди
        OnBoardingRecord record = deque.poll();
        imageView.setImageResource(record.getImage());
        title.setText(record.getTitle());
        text.setText(record.getText());

        //нажимаем на кнопку next
        //если очередь не пустая, то заполняем новый экран
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (deque.size() > 0){
                    //берем очередной элемент очереди и показываем его на экране
                    OnBoardingRecord record = deque.poll();
                    imageView.setImageResource(record.getImage());
                    title.setText(record.getTitle());
                    text.setText(record.getText());
                }
                //если очередь после извлечения элемента стала пустой, то прячем кнопки skip и next
                // и показываем кнопку SignUP и ссылку на SignIn
                if (deque.size() == 0){
                    skipButton.setVisibility(View.GONE);
                    nextButton.setVisibility(View.GONE);
                    signupButton.setVisibility(View.VISIBLE);
                    t1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    //сделаем переход на пустое Активити Holder
                    t2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(OnBoarding.this, HolderActivity.class));
                        }
                    });
                }
            }
        });

    }

    private void initDeque() {
        OnBoardingRecord record = new OnBoardingRecord(R.drawable.on_board_1,"Quick Delivery At Your \n Doorstep", "Enjoy quick pick-up and delivery to \n your destination");
        deque.offer(record);
        record = new OnBoardingRecord(R.drawable.on_board_2,"Flexible Payment", "Different modes of payment either \n before and after delivery without \n stress");
        deque.offer(record);
        record = new OnBoardingRecord(R.drawable.on_board_3,"Real-time Tracking", "Track your packages/items from the \n comfort of your home till final destination");
        deque.offer(record);

    }


}
