package com.example.booking_app.connection;

import com.example.booking_app.models.order.DataOrder;
import com.example.booking_app.models.order.OrderResponse;
import com.example.booking_app.models.store.StoreResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface OrderService {
    @GET("order/user/{id}")
    Call<OrderResponse> getOrderUser(@Header("Authorization") String authorization, @Path("id") int id);
}
