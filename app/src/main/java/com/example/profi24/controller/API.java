package com.example.profi24.controller;

import com.example.profi24.data.Email;
import com.example.profi24.data.ResponseCreateUser;
import com.example.profi24.data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface API {

    //регистрация юзера
    @POST("signup")
    Call<ResponseCreateUser> signUpByEmailAndPswrd(@Header("apikey") String apikey, @Body User user);

    //отправка кода на почту для восстановления пароля
    @POST("recover")
    Call<Void> sendCode(@Header("apikey") String apikey, @Body Email email);
}
