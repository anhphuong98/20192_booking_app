package com.example.booking_app.models.order;

import com.google.gson.annotations.SerializedName;

public class OrderResponse {
    @SerializedName("success")
    private boolean success;
    @SerializedName("order_id")
    private int order_id;

    public OrderResponse() {

    }

    public OrderResponse(boolean success, int order_id) {
        this.success = success;
        this.order_id = order_id;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
