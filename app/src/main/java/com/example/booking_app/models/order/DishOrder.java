package com.example.booking_app.models.order;

import com.google.gson.annotations.SerializedName;

public class DishOrder {
    @SerializedName("dish_id")
    private int dish_id;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("current_price")
    private float current_price;
    public DishOrder() {

    }
    public DishOrder(int dish_id, int quantity, float current_price) {
        this.dish_id = dish_id;
        this.quantity = quantity;
        this.current_price = current_price;
    }

    public int getDish_id() {
        return dish_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getCurrent_price() {
        return current_price;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCurrent_price(float current_price) {
        this.current_price = current_price;
    }
}
