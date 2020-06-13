package com.example.booking_app.models.order;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Order {
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
    @SerializedName("store_id")
    private int store_id;
    @SerializedName("dish")
    private ArrayList<DishOrder> listDishOrder;

    public Order(String address, String name_recieve, double phone_recieve, String note, float ship_price, int store_id, ArrayList<DishOrder> listDishOrder) {
        this.address = address;
        this.name_recieve = name_recieve;
        this.phone_recieve = phone_recieve;
        this.note = note;
        this.ship_price = ship_price;
        this.store_id = store_id;
        this.listDishOrder = listDishOrder;
    }

    public String getAddress() {
        return address;
    }

    public String getName_recieve() {
        return name_recieve;
    }

    public double getPhone_recieve() {
        return phone_recieve;
    }

    public String getNote() {
        return note;
    }

    public float getShip_price() {
        return ship_price;
    }

    public int getStore_id() {
        return store_id;
    }

    public ArrayList<DishOrder> getListDishOrder() {
        return listDishOrder;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName_recieve(String name_recieve) {
        this.name_recieve = name_recieve;
    }

    public void setPhone_recieve(double phone_recieve) {
        this.phone_recieve = phone_recieve;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setShip_price(float ship_price) {
        this.ship_price = ship_price;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public void setListDishOrder(ArrayList<DishOrder> listDishOrder) {
        this.listDishOrder = listDishOrder;
    }
}
