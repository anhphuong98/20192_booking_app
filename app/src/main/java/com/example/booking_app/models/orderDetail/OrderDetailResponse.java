package com.example.booking_app.models.orderDetail;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class OrderDetailResponse {

        @SerializedName("success")
        Boolean success;

        @SerializedName("data")
        ArrayList<DataOrderDetail> data;

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public ArrayList<DataOrderDetail> getData() {
            return data;
        }

        public void setData(ArrayList<DataOrderDetail> data) {
            this.data = data;
        }
}


