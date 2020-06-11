package com.example.booking_app.connection;

public class APIUtils {
    public APIUtils() {

    }
<<<<<<< HEAD
    public static String API_URL = "http://192.168.43.22:4000/api/";
=======

    public static String API_URL = "http://192.168.1.185:8080/api/";

>>>>>>> e65eeab05825fa7625690092528f23d813ec419a

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
