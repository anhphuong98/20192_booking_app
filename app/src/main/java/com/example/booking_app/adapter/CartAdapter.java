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
import com.example.booking_app.activity.ChangeDishCartQuantity;
import com.example.booking_app.models.dish.CartDish;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private ArrayList<CartDish> listCartDish;
    ChangeDishCartQuantity changeDishCartQuantity;

    public CartAdapter(Context context, ArrayList<CartDish> listCartDish, ChangeDishCartQuantity changeDishCartQuantity) {
        this.context = context;
        this.listCartDish = listCartDish;
        this.changeDishCartQuantity = changeDishCartQuantity;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_dish_name;
        TextView cart_dish_price;
        ImageView cart_dish_image;
        TextView cart_dish_quantity;
        ImageView plus, minus;

        public CartViewHolder(View itemView) {
            super(itemView);
            cart_dish_name = (TextView) itemView.findViewById(R.id.cart_dish_name);
            cart_dish_price = (TextView) itemView.findViewById(R.id.cart_dish_price);
            cart_dish_image = (ImageView) itemView.findViewById(R.id.cart_dish_image);
            cart_dish_quantity = (TextView) itemView.findViewById(R.id.cart_dish_quantity);
            plus = (ImageView) itemView.findViewById(R.id.cart_dish_plus);
            minus = (ImageView) itemView.findViewById(R.id.cart_dish_sub);
            plus.setOnClickListener(this);
            minus.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            changeDishCartQuantity.changeQuantity(getPosition(), v);
            int n = Integer.parseInt(cart_dish_quantity.getText().toString());
            switch (v.getId()){
                case R.id.cart_dish_plus:
                    cart_dish_quantity.setText(String.valueOf(n+1));
                    break;
                case R.id.cart_dish_sub:
                    cart_dish_quantity.setText(String.valueOf(n-1));
            }

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
        holder.cart_dish_price.setText(convertMoney(listCartDish.get(position).getPrice()));
        Picasso.with(context).load(listCartDish.get(position).getImage()).into(holder.cart_dish_image);
       // holder.cart_dish_image.setImageResource(listCartDish.get(position).getImage());
        holder.cart_dish_quantity.setText(String.valueOf(listCartDish.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return listCartDish.size();
    }

    public String convertMoney(Double money) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currentMoney = NumberFormat.getCurrencyInstance(localeVN);
        return currentMoney.format(money);
    }
}
