package com.example.profi24.ui;

import static com.example.profi24.utils.Utils.APIKEY;
import static com.example.profi24.utils.Utils.BASE_URL;
import static com.example.profi24.utils.Utils.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.profi24.R;
import com.example.profi24.controller.API;
import com.example.profi24.data.ResponseCreateUser;
import com.example.profi24.utils.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogInActivity extends AppCompatActivity {

    TextInputEditText email, pswrd;
    MaterialButton button;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        email = findViewById(R.id.logInEmailAddressField);
        pswrd = findViewById(R.id.loginPassword);
        button = findViewById(R.id.button_signin);

        //Заполним данными из предыдущего окна
        email.setText(Utils.user.getEmail());
        pswrd.setText(Utils.user.getPassword());

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        //создадим юзера на сервере

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseCreateUser> call = api.signUpByEmailAndPswrd(APIKEY, user);
                call.enqueue(new Callback<ResponseCreateUser>() {
                    @Override
                    public void onResponse(Call<ResponseCreateUser> call, Response<ResponseCreateUser> response) {
                        Toast.makeText(LogInActivity.this, "User create", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseCreateUser> call, Throwable t) {
                        Toast.makeText(LogInActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void signup(View view) {
        startActivity(new Intent(LogInActivity.this, SignUpActivity.class));
    }

    public void forgot(View view) {
        startActivity(new Intent(LogInActivity.this, ForgotPasswordActivity.class));
    }
}