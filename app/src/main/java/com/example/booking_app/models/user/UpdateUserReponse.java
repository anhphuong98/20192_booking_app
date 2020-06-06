package com.example.booking_app.models.user;

import com.google.gson.annotations.SerializedName;

public class UpdateUserReponse {
    @SerializedName("success")
    private Boolean success;
    @SerializedName("message")
    private String message;
    public UpdateUserReponse() {

    }
    public UpdateUserReponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
