package com.example.booking_app.adapter;
import android.content.Context;
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

import java.util.ArrayList;

import retrofit2.Callback;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {
    Context context;
    ArrayList<DataOrder> listOrder;
    private OnStoreListener mOnStoreListener;


    public interface OnStoreListener{
        void onStoreClick(int position);

    }
    public void setOnStoreListener(OnStoreListener onStoreListener) {
        this.mOnStoreListener = onStoreListener;
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
        OrderHolder orderHolder = new OrderHolder(view,mOnStoreListener);
        return orderHolder;
    }

    @Override
    //hỗ trợ get set các thuộc tính gán lên cho layout
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {
        DataOrder order = listOrder.get(position);
        holder.namestore.setText(order.getStore().getName());
        holder.addressStore.setText(order.getStore().getAddress());
//        holder.priceOrder.setText(order.getTotalPrice());
        holder.timeOrder.setText(order.getTime());
        Picasso.with(context).load(order.getStore().getUrlImage()).into(holder.imageStore);


    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

    public class OrderHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView namestore, addressStore,priceOrder,timeOrder;
        ImageView imageStore;
        OnStoreListener onStoreListener;

        public OrderHolder(@NonNull View itemView, OnStoreListener onStoreListener ) {
            super(itemView);
            imageStore = (ImageView) itemView.findViewById(R.id.imagestore);
            namestore = (TextView) itemView.findViewById(R.id.namestore);
            addressStore = (TextView) itemView.findViewById(R.id.addressStore);
//            priceOrder = (TextView) itemView.findViewById(R.id.priceOrder);
            timeOrder = (TextView) itemView.findViewById(R.id.timeOrder);
            itemView.setOnClickListener(this);
            this.onStoreListener = onStoreListener;
        }

        public OrderHolder(@NonNull View itemView) {
            super(itemView);
        }


        @Override
        public void onClick(View v) {
            onStoreListener.onStoreClick(getAbsoluteAdapterPosition());
        }
    }
}
