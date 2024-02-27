package com.example.profi24.controller;

import com.example.profi24.data.Email;
import com.example.profi24.data.NameAndBalance;
import com.example.profi24.data.ResponseCreateUser;
import com.example.profi24.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {

    //регистрация юзера
    @POST("signup")
    Call<ResponseCreateUser> signUpByEmailAndPswrd(@Header("apikey") String apikey, @Body User user);

    //отправка кода на почту для восстановления пароля
    @POST("recover")
    Call<Void> sendCode(@Header("apikey") String apikey, @Body Email email);

    @GET("profile")
    Call<List<NameAndBalance>> getNameAndBallance(@Query("select")String select, @Header("apikey") String apikey);
}
