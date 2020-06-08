package com.example.booking_app.connection;

public class APIUtils {
    private APIUtils() {

    }
    public static String API_URL = "http://172.20.10.6:8080/api/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(API_URL).create(SOService.class);
    }
    public static StoreService getStoreService() {
        return RetrofitClient.getClient(API_URL).create(StoreService.class);
    }
    public static DishService getDishService(){
        return RetrofitClient.getClient(API_URL).create(DishService.class);
    }
}
