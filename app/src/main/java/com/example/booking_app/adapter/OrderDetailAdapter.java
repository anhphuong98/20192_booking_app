package com.example.booking_app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_app.R;
import com.example.booking_app.models.dish.CartDish;
import com.example.booking_app.models.orderDetail.DataOrderDetail;
import com.example.booking_app.models.orderDetail.Dish;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class OrderDetailAdapter extends RecyclerView.Adapter< OrderDetailAdapter.OrderDetailViewHolder> {
    ArrayList<DataOrderDetail> listDish;
    Context mContext;

    public OrderDetailAdapter(Context mContext, ArrayList<DataOrderDetail> listDish) {
        this.listDish = listDish;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_list_item, parent, false);
        OrderDetailViewHolder viewHolder = new OrderDetailViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {
        DataOrderDetail dishOrder = listDish.get(position);
        Picasso.with(mContext).load(dishOrder.getDish().getUrl_image()).into(holder.itemimg);
        holder.name.setText(dishOrder.getDish().getName());
        holder.price.setText(String.valueOf(dishOrder.getCurrent_price()));
        holder.quantity.setText(" x " + Integer.toString(dishOrder.getQuantity()));

    }

    @Override
    public int getItemCount() {
        return listDish.size();
    }

    public class OrderDetailViewHolder extends RecyclerView.ViewHolder {

        ImageView itemimg;
        TextView quantity, name, price;

        public OrderDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            itemimg = (ImageView) itemView.findViewById(R.id.order_item_img);
            quantity = (TextView) itemView.findViewById(R.id.order_item_quantity);
            name = (TextView) itemView.findViewById(R.id.order_item_name);
            price = (TextView) itemView.findViewById(R.id.order_item_price);
        }
    }
}


