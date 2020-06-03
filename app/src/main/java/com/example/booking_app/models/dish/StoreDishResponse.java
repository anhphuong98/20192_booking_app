package com.example.booking_app.models.dish;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StoreDishResponse {
    @SerializedName("count")
    private int count;
    @SerializedName("rows")
    private ArrayList<StoreDish> storeDishes;

    public StoreDishResponse(){}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<StoreDish> getStoreDishes() {
        return storeDishes;
    }

    public void setStoreDishes(ArrayList<StoreDish> storeDishes) {
        this.storeDishes = storeDishes;
    }
}
