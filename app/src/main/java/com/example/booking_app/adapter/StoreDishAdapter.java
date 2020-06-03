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
import com.example.booking_app.models.dish.StoreDish;

import java.util.ArrayList;

public class StoreDishAdapter extends RecyclerView.Adapter<StoreDishAdapter.Storedishholder> {
    Context context;
    ArrayList<StoreDish> storeDishes;

    public StoreDishAdapter(Context context, ArrayList<StoreDish> storeDishes) {
        this.context = context;
        this.storeDishes = storeDishes;
    }
    public class Storedishholder extends RecyclerView.ViewHolder {
        ImageView dishimg;
        TextView dishname;
        TextView dishprice;
        public Storedishholder(@NonNull View itemView) {
            super(itemView);
            dishimg = (ImageView) itemView.findViewById(R.id.dishimg);
            dishname = (TextView) itemView.findViewById(R.id.dishname);
            dishprice = (TextView) itemView.findViewById(R.id.price);
        }
    }
    @NonNull
    @Override
    public Storedishholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemview = inflater.inflate(R.layout.store_dish_item, viewGroup, false);
        return new Storedishholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull Storedishholder storedishholder, int position) {
            storedishholder.dishname.setText(storeDishes.get(position).getName());
            storedishholder.dishimg.setImageResource(storeDishes.get(position).getUrl_image());
            storedishholder.dishprice.setText(storeDishes.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return storeDishes.size();
    }


}
