package com.example.booking_app.models.order;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OrderResponse {
    @SerializedName("success")
    Boolean success;

    @SerializedName("data")
    ArrayList<DataOrder> data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ArrayList<DataOrder> getData() {
        return data;
    }

    public void setData(ArrayList<DataOrder> data) {
        this.data = data;
    }
}
