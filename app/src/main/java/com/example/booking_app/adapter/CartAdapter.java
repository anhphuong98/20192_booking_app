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
import com.example.booking_app.models.dish.CartDish;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private ArrayList<CartDish> listCartDish;


    public CartAdapter(Context context, ArrayList<CartDish> listCartDish) {
        this.context = context;
        this.listCartDish = listCartDish;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView cart_dish_name;
        TextView cart_dish_price;
        ImageView cart_dish_image;
        TextView cart_dish_quantity;

        public CartViewHolder(View itemView) {
            super(itemView);
            cart_dish_name = (TextView) itemView.findViewById(R.id.cart_dish_name);
            cart_dish_price = (TextView) itemView.findViewById(R.id.cart_dish_price);
            cart_dish_image = (ImageView) itemView.findViewById(R.id.cart_dish_image);
            cart_dish_quantity = (TextView) itemView.findViewById(R.id.cart_dish_quantity);
        }
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_cart_dish, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.cart_dish_name.setText(listCartDish.get(position).getName());
        holder.cart_dish_price.setText(listCartDish.get(position).getPrice());
        holder.cart_dish_image.setImageResource(listCartDish.get(position).getImage());
        holder.cart_dish_quantity.setText(String.valueOf(listCartDish.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return listCartDish.size();
    }
}
