package com.example.booking_app.models.user;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("token")
    String token;

    @SerializedName("success")
    Boolean success;

    @SerializedName("data")
    DataUser data;

    public UserResponse(){}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DataUser getData() {
        return data;
    }

    public void setData(DataUser data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
