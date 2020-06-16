package com.example.booking_app.models.orderDetail;

import com.example.booking_app.models.dish.StoreDish;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataOrderDetail {

        @SerializedName("id")
        private int id;
        @SerializedName("order_id")
        private int order_id;
        @SerializedName("quantity")
        private int quantity;
        @SerializedName("current_price")
        private int current_price;
        @SerializedName("name_dish")
        private String name_dish;
       @SerializedName("sale_dish")
       private float sale_dish;
       @SerializedName("url_image_dish")
       private String url_image_dish;
       @SerializedName("dish")
       private Dish dish;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(int current_price) {
        this.current_price = current_price;
    }

    public String getName_dish() {
        return name_dish;
    }

    public void setName_dish(String name_dish) {
        this.name_dish = name_dish;
    }

    public float getSale_dish() {
        return sale_dish;
    }

    public void setSale_dish(float sale_dish) {
        this.sale_dish = sale_dish;
    }

    public String getUrl_image_dish() {
        return url_image_dish;
    }

    public void setUrl_image_dish(String url_image_dish) {
        this.url_image_dish = url_image_dish;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}

