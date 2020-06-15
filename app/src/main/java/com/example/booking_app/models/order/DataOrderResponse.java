package com.example.booking_app.models.order;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DataOrderResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private ArrayList<DataOrder> data;
    public DataOrderResponse() {

    }

    public DataOrderResponse(boolean success, ArrayList<DataOrder> data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<DataOrder> getData() {
        return data;
    }

    public void setData(ArrayList<DataOrder> data) {
        this.data = data;
    }
}
