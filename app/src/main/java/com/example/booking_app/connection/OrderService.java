package com.example.booking_app.connection;
import com.example.booking_app.models.order.DataOrderResponse;
import com.example.booking_app.models.order.Order;
import com.example.booking_app.models.order.OrderResponse;
import com.example.booking_app.models.orderDetail.OrderDetailResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderService {
    @GET("order/user/{id}")
    Call<DataOrderResponse> getOrderUser(@Header("Authorization") String authorization, @Path("id") int id);
    @GET("orderDetail/{id}")
    Call<OrderDetailResponse> getOrderById(@Header("Authorization") String authorization, @Path("id") int id);
    @POST("order")
    Call<OrderResponse> postOrder(@Body Order order, @Header("Authorization") String auth);
    @GET("currentOrder/user/{id}")
    Call<DataOrderResponse> getCurrentOrder(@Header("Authorization") String authorization, @Path("id") int id);

}
