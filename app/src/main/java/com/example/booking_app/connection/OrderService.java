package com.example.booking_app.connection;

import com.example.booking_app.models.order.Order;
import com.example.booking_app.models.order.OrderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OrderService {
    @POST("order")
    Call<OrderResponse> postOrder(@Body Order order, @Header("Authorization") String auth);

}
