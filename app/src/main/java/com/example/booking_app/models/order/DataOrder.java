package com.example.booking_app.models.order;
import com.example.booking_app.models.store.DataStore;
import com.example.booking_app.models.user.DataUser;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;

public class DataOrder implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("address")
    private String address;
    @SerializedName("name_recieve")
    private String name_recieve;
    @SerializedName("phone_recieve")
    private double phone_recieve;
    @SerializedName("note")
    private String note;
    @SerializedName("ship_price")
    private float ship_price;
    @SerializedName("time")
    private Timestamp time;
    @SerializedName("status")
    private int status;
    @SerializedName("shipper_id")
    private int shipper_id;
    @SerializedName("store_id")
    private int store_id;
    @SerializedName("store")
    private DataStore store;

    public DataOrder() {

    }

    public DataOrder(int id, int user_id, String address, String name_recieve, double phone_recieve, String note, float ship_price, Timestamp time, int status, int shipper_id, int store_id, DataStore store) {
        this.id = id;
        this.user_id = user_id;
        this.address = address;
        this.name_recieve = name_recieve;
        this.phone_recieve = phone_recieve;
        this.note = note;
        this.ship_price = ship_price;
        this.time = time;
        this.status = status;
        this.shipper_id = shipper_id;
        this.store_id = store_id;
        this.store = store;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName_recieve() {
        return name_recieve;
    }

    public void setName_recieve(String name_recieve) {
        this.name_recieve = name_recieve;
    }

    public double getPhone_recieve() {
        return phone_recieve;
    }

    public void setPhone_recieve(double phone_recieve) {
        this.phone_recieve = phone_recieve;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public float getShip_price() {
        return ship_price;
    }

    public void setShip_price(float ship_price) {
        this.ship_price = ship_price;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getShipper_id() {
        return shipper_id;
    }

    public void setShipper_id(int shipper_id) {
        this.shipper_id = shipper_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public DataStore getStore() {
        return store;
    }

    public void setStore(DataStore store) {
        this.store = store;
    }
}
