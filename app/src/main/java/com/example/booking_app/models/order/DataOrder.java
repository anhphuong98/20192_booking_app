package com.example.booking_app.models.order;
import com.example.booking_app.models.store.DataStore;
import com.example.booking_app.models.user.DataUser;
import com.google.gson.annotations.SerializedName;

public class DataOrder {
    @SerializedName("id")
    int id;
    @SerializedName("address")
    String address;

    @SerializedName("time")
    String time;

    @SerializedName("status")
    int status;

    @SerializedName("user")
    DataUser user;

    @SerializedName("store")
    DataStore store;

    @SerializedName("totalPrice")
    int totalPrice;

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataUser getUser() {
        return user;
    }

    public void setUser(DataUser user) {
        this.user = user;
    }

    public DataStore getStore() {
        return store;
    }

    public void setStore(DataStore store) {
        this.store = store;
    }
}
