package com.example.booking_app.connection;

import com.example.booking_app.models.dish.StoreDishResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DishService {
    @GET("getStoreDish/{id}")
    Call<StoreDishResponse> getStoreDish(@Path("id") int id);
}
