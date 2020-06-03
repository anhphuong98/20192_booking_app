package com.example.booking_app.connection;

import com.example.booking_app.models.store.StoreResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StoreService {
    @GET("store")
    Call<StoreResponse> getAllStore();
}
