package com.example.booking_app.models.orderDetail;

import com.example.booking_app.models.dish.StoreDish;
import com.google.gson.annotations.SerializedName;

public class DataOrderDetail {

        @SerializedName("id")
        int quantity;

        @SerializedName("current_price")
        int current_price;

        @SerializedName("dish")
        Dish dish;

        public int getCurrent_price() {
            return current_price;
        }

        public void setCurrent_price(int current_price) {
            this.current_price = current_price;
        }

        public Dish Dish() {
            return dish;
        }

        public void setDish(Dish dish) {
            this.dish = dish;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

    }

