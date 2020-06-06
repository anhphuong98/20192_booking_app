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
import com.example.booking_app.models.store.DataStore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreHolder> {
    Context context;
    ArrayList<DataStore> listStore;

    public StoreAdapter(Context context, ArrayList<DataStore> listStore) {
        this.context = context;
        this.listStore = listStore;
    }

    @NonNull
    @Override
    // khởi tạo view cho layout thiết kế bên ngoài
    public StoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.store_item, parent,false);
        StoreHolder storeHolder = new StoreHolder(view);
        return storeHolder;
    }

    @Override
    //hỗ trợ get set các thuộc tính gán lên cho layout
    public void onBindViewHolder(@NonNull StoreHolder holder, int position) {
        DataStore store = listStore.get(position);
        holder.nameStore.setText(store.getName());
        holder.addressStore.setText(store.getAddress());
        Picasso.with(context).load(store.getUrlImage()).into(holder.imageStore);

    }

    @Override
    public int getItemCount() {
        return listStore.size();
    }

    public class StoreHolder extends RecyclerView.ViewHolder{
        ImageView imageStore;
        TextView nameStore, addressStore;

        public StoreHolder(@NonNull View itemView) {
            super(itemView);
            imageStore = (ImageView) itemView.findViewById(R.id.imagestore);
            nameStore = (TextView) itemView.findViewById(R.id.namestore);
            addressStore = (TextView) itemView.findViewById(R.id.addressstore);
        }
    }
}
