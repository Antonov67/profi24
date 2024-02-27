package com.example.profi24.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.profi24.R;
import com.example.profi24.data.ProfileActivity;
import com.example.profi24.data.User;
import com.example.profi24.utils.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {

    AppCompatCheckBox checkBox;
    MaterialButton button;
    TextView signIntext;
    TextInputLayout email, pswrd1, pswrd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        checkBox = findViewById(R.id.signupCheckBox);
        button = findViewById(R.id.button_signup);
        signIntext = findViewById(R.id.textView17);
        email = findViewById(R.id.emailAddress);
        pswrd1 = findViewById(R.id.passwordTextInputLayout);
        pswrd2 = findViewById(R.id.confirmPasswordTextInputLayout);

        //переход на активити 3 сессии
        startActivity(new Intent(SignUpActivity.this, ProfileActivity.class));

        //по нажатию на кнопку проверим корректность почты, галочку в чекбоксе и совпадение паролей
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String e = String.valueOf(email.getEditText().getText());
                String p1 = String.valueOf(pswrd1.getEditText().getText());
                String p2 = String.valueOf(pswrd2.getEditText().getText());

                //если галочка стоит и проверка корректности почты на шаблон и на то, что все символы маленькие и что пароль 2 раза введен одинаково
                if (checkBox.isChecked()
                        && isEmailValid(e)
                        && p1.equals(p2)
                        && e.equals(e.toLowerCase())){
                    // запишем данные юзера
                    Utils.user.setEmail(e);
                    Utils.user.setPassword(p1);
                    //откроем окно LogIn Activity
                    startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
                }
            }
        });
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void signin(View view) {
        startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
    }
}