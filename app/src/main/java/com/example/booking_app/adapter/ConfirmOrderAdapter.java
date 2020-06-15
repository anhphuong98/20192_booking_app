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
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ConfirmOrderAdapter extends RecyclerView.Adapter<ConfirmOrderAdapter.ConfirmorderHolder> {
    Context context;
    ArrayList<CartDish> listOrder;
    @NonNull
    @Override
    public ConfirmorderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.order_list_item, parent, false);
        ConfirmorderHolder confirmorderHolder = new ConfirmorderHolder(view);
        return confirmorderHolder;
    }

    public ConfirmOrderAdapter(Context context, ArrayList<CartDish> listOrder) {
        this.context = context;
        this.listOrder = listOrder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConfirmorderHolder holder, int position) {
        CartDish cartDish = listOrder.get(position);
        Picasso.with(context).load(cartDish.getImage()).into(holder.itemimg);
        holder.name.setText(cartDish.getName());
        Double price = cartDish.getPrice()*cartDish.getQuantity();
        holder.price.setText(convertMoney(price));
        holder.quantity.setText(Integer.toString(cartDish.getQuantity()) + " x ");
    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

    public class ConfirmorderHolder extends RecyclerView.ViewHolder {
        ImageView itemimg;
        TextView quantity, name, price;
        public ConfirmorderHolder(@NonNull View itemView) {
            super(itemView);
            itemimg = (ImageView) itemView.findViewById(R.id.order_item_img);
            quantity = (TextView) itemView.findViewById(R.id.order_item_quantity);
            name = (TextView) itemView.findViewById(R.id.order_item_name);
            price = (TextView) itemView.findViewById(R.id.order_item_price);
        }
    }
    public String convertMoney(Double money) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currentMoney = NumberFormat.getCurrencyInstance(localeVN);
        return currentMoney.format(money);
    }
}
