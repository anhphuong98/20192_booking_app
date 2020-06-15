package com.example.booking_app.models.orderDetail;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderDetailResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private ArrayList<DataOrderDetail> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<DataOrderDetail> getData() {
        return data;
    }

    public void setData(ArrayList<DataOrderDetail> data) {
        this.data = data;
    }
}


