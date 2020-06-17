package com.example.booking_app.adapter;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_app.R;
import com.example.booking_app.models.order.DataOrder;
import com.example.booking_app.models.store.DataStore;
import com.example.booking_app.models.store.StoreResponse;
import com.squareup.picasso.Picasso;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Callback;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {
    Context context;
    ArrayList<DataOrder> listOrder;
    private OnOrderListener mOnOrderListener;

    public interface OnOrderListener{
        void onOrderClick(int position);

    }
    public void setOrderListener(OrderAdapter.OnOrderListener onOrderListener) {
        this.mOnOrderListener = onOrderListener;
    }

    public OrderAdapter(Context context, ArrayList<DataOrder> listOrder) {
        this.context = context;
        this.listOrder = listOrder;

    }

    @NonNull
    @Override
    // khởi tạo view cho layout thiết kế bên ngoài
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.order_item, parent,false);
        OrderHolder orderHolder = new OrderHolder(view,mOnOrderListener);
        return orderHolder;
    }

    @Override
    //hỗ trợ get set các thuộc tính gán lên cho layout
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {
        DataOrder order = listOrder.get(position);
        holder.namestore.setText(order.getStore().getName());
        holder.addressStore.setText(order.getStore().getAddress());
        String s = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(order.getTime());
        holder.timeOrder.setText(s);
        setProcess(order.getStatus(), holder);
        Picasso.with(context).load(order.getStore().getUrlImage()).into(holder.imageStore);


    }
    public void setProcess(int status, OrderHolder holder) {
        switch (status) {
            case 1:
                holder.processOrder.setText("Đang giao");
                holder.processOrder.setTextColor(Color.parseColor("#EA882A"));
                break;
            case 2:
                holder.processOrder.setText("Hoàn thành");
                holder.processOrder.setTextColor(Color.GREEN);
                break;
            case 3:
                holder.processOrder.setText("Đã hủy");
                holder.processOrder.setTextColor(Color.RED);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

    public class OrderHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView namestore, addressStore,priceOrder,timeOrder, processOrder;
        ImageView imageStore;
        OnOrderListener onOrderListener;

        public OrderHolder(@NonNull View itemView, OnOrderListener onOrderListener ) {
            super(itemView);
            imageStore = (ImageView) itemView.findViewById(R.id.imagestore1);
            namestore = (TextView) itemView.findViewById(R.id.namestore1);
            addressStore = (TextView) itemView.findViewById(R.id.addressStore1);
//            priceOrder = (TextView) itemView.findViewById(R.id.priceOrder);
            timeOrder = (TextView) itemView.findViewById(R.id.timeOrder1);
            processOrder = (TextView) itemView.findViewById(R.id.processOrder);
            itemView.setOnClickListener(this);
            this.onOrderListener = onOrderListener;
        }

        public OrderHolder(@NonNull View itemView) {
            super(itemView);
        }


        @Override
        public void onClick(View v) {
            onOrderListener.onOrderClick(getAbsoluteAdapterPosition());
        }
    }
}
