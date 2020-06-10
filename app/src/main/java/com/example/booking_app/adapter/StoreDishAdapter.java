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
import com.example.booking_app.activity.Clickdishitem;
import com.example.booking_app.models.dish.StoreDish;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class StoreDishAdapter extends RecyclerView.Adapter<StoreDishAdapter.Storedishholder> {
    Context context;
    ArrayList<StoreDish> storeDishes;
    private int selected = RecyclerView.NO_POSITION;
    Clickdishitem clickitem;

    public StoreDishAdapter(Context context, ArrayList<StoreDish> storeDishes, Clickdishitem clickitem) {
        this.context = context;
        this.storeDishes = storeDishes;
        this.clickitem = clickitem;
    }
    public class Storedishholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView dishimg;
        TextView dishname;
        TextView dishprice;
        ImageView additem;
        @SuppressLint("WrongViewCast")
        public Storedishholder(@NonNull View itemView) {
            super(itemView);
            dishimg = (ImageView) itemView.findViewById(R.id.dishimg);
            dishname = (TextView) itemView.findViewById(R.id.dishname);
            dishprice = (TextView) itemView.findViewById(R.id.price);
            additem = (ImageView) itemView.findViewById(R.id.additem);
            additem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickitem.click(getPosition());
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
            StoreDish storeDish = storeDishes.get(position);
            storedishholder.dishname.setText(storeDish.getName());
            Picasso.with(context).load(storeDish.getUrlImage()).into(storedishholder.dishimg);
            //storedishholder.dishimg.setImageResource(storeDishes.get(position).getUrl_image());
            storedishholder.dishprice.setText(storeDish.getPrice().toString());
    }

    @Override
    public int getItemCount() {
        return storeDishes.size();
    }


}
