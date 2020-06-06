package com.example.booking_app.connection;

import com.example.booking_app.models.user.DataUser;
import com.example.booking_app.models.user.UpdateUserReponse;
import com.example.booking_app.models.user.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SOService {
    @POST("user/login")
    @FormUrlEncoded
    Call<UserResponse> logIn(@Field("email") String email, @Field("password") String password);

    @POST("user/register")
    @FormUrlEncoded
    Call<UserResponse> register(@Field("email") String email, @Field("name") String name, @Field("password") String password);

    @PUT("user/{id}")
    Call<UpdateUserReponse> updateUser(@Path("id") int id, @Body DataUser user, @Header("Authorization") String auth);
}
