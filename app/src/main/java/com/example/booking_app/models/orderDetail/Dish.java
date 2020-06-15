package com.example.booking_app.models.orderDetail;

import com.google.gson.annotations.SerializedName;

public class Dish {
    @SerializedName("id")
    private int id ;
    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private int price;

    @SerializedName("sale")
    private int sale;
    @SerializedName("store_id")
    private int store_id;
    @SerializedName("category_id")
    private int category_id;
    @SerializedName("url_image")
    private String url_image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }
}
