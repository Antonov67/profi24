package com.example.profi24.data;

import static com.example.profi24.utils.Utils.APIKEY;
import static com.example.profi24.utils.Utils.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.profi24.R;
import com.example.profi24.controller.API;
import com.example.profi24.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    TextView name, balance;

    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.textView19);
        balance = findViewById(R.id.textView20);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<List<NameAndBalance>> call = api.getNameAndBallance("*", APIKEY);
        call.enqueue(new Callback<List<NameAndBalance>>() {
            @Override
            public void onResponse(Call<List<NameAndBalance>> call, Response<List<NameAndBalance>> response) {

                if (response.body() != null){
                    Toast.makeText(ProfileActivity.this, response.body().get(0).balance, Toast.LENGTH_SHORT).show();
                     name.setText(response.body().get(0).fio);
                     balance.setText(response.body().get(0).balance);
                }
            }

            @Override
            public void onFailure(Call<List<NameAndBalance>> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}