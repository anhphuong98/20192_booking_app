package com.example.booking_app.connection;

public class APIUtils {
    public APIUtils() {

    }
    public static String API_URL = "http:/192.168.1.186:4000/api/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(API_URL).create(SOService.class);
    }
    public static StoreService getStoreService() {
        return RetrofitClient.getClient(API_URL).create(StoreService.class);
    }
    public static DishService getDishService(){
        return RetrofitClient.getClient(API_URL).create(DishService.class);
    }
    public static OrderService getOrderService() {
        return RetrofitClient.getClient(API_URL).create(OrderService.class);
    }
}
