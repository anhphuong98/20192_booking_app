package com.example.booking_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_app.R;
import com.example.booking_app.models.orderDetail.DataOrderDetail;

import java.util.ArrayList;


public class OrderDetailAdapter extends RecyclerView.Adapter< OrderDetailAdapter.OrderDetailViewHolder> {
    ArrayList<DataOrderDetail> listDish;
    Context mContext;

    public  OrderDetailAdapter(ArrayList<DataOrderDetail> listDish, Context mContext) {
        this.listDish = listDish;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_detail_item,parent,false);
        OrderDetailViewHolder viewHolder = new OrderDetailViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listDish.size();
    }

    public class OrderDetailViewHolder extends RecyclerView.ViewHolder{

        public OrderDetailViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}

