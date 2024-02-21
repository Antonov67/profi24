package com.example.profi24.ui;





import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.profi24.R;
import com.example.profi24.data.OnBoardingRecord;
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



        //Для работы кнопки Skip создадим SharedPreference
        SharedPreferences settings = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = settings.edit();

//        //эти строчки нужны, чтобы отменить SKIP и вернуть статус просмотра очереди экранов OnBoard, просто активируйте их и один раз запустите, потом снова закомментируйте их
//        prefEditor.putString("isSkip", "false");
//        prefEditor.putInt("index", 0);
//        prefEditor.apply();

        //При нажати на кнопку Skip запишем в настройки значение true для переменной isSkip
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // сохраняем его в настройках
                SharedPreferences.Editor prefEditor = settings.edit();
                prefEditor.putString("isSkip", "true");
                prefEditor.apply();
                //открываем Активити Holder
                startActivity(new Intent(OnBoarding.this, SignUpActivity.class));
            }
        });



        //Если ранее был нажат Skip, то сразу переходим на Активити Holder, а если нет, то работают экраны OnBoarding
        //читаем значение переменной isSkip из Настроек

        String isSkip = settings.getString("isSkip","не определено");
        //уберем из очереди экраны, которые уже были показаны
        int index = settings.getInt("index", 0);
        for (int i = 0; i < index; i++) {
            deque.poll();
        }

        if (isSkip.equals("true") || deque.size() == 1){
            //открываем Активити Holder
            startActivity(new Intent(OnBoarding.this, SignUpActivity.class));
        }else{
            //показываем экраны OnBoarding
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
                        //запишем текущее состояне очереди
                        SharedPreferences.Editor prefEditor = settings.edit();
                        prefEditor.putInt("index", settings.getInt("index", 0) + 1);
                        prefEditor.apply();

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
                        signupButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(OnBoarding.this, SignUpActivity.class));
                            }
                        });
                    }
                }
            });
        }



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
