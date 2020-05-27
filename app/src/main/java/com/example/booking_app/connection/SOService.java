package com.example.booking_app.connection;

import com.example.booking_app.models.user.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SOService {
    @POST("user/login")
    @FormUrlEncoded
    Call<UserResponse> logIn(@Field("email") String email, @Field("password") String password);

//    @POST("user/register")
//    Call<UserResponse> register(@Field())
}
