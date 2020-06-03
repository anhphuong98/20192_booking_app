package com.example.booking_app.connection;

public class APIUtils {
    private APIUtils() {

    }
    public static String API_URL = "http://192.168.43.22:8080/api/";
    public static SOService getSOService() {
        return RetrofitClient.getClient(API_URL).create(SOService.class);
    }
    public static StoreService getStoreService() {
        return RetrofitClient.getClient(API_URL).create(StoreService.class);
    }
}
