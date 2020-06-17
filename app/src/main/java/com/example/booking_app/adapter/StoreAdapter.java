package com.example.booking_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_app.R;
import com.example.booking_app.models.store.DataStore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreHolder> implements Filterable {
    Context context;
    ArrayList<DataStore> listStore;
    ArrayList<DataStore> allStore;
    private OnStoreListener mOnStoreListener;


    public interface OnStoreListener {
        void onStoreClick(int position);

    }

    public void setOnStoreListener(OnStoreListener onStoreListener) {
        this.mOnStoreListener = onStoreListener;
    }

    public StoreAdapter(Context context, ArrayList<DataStore> listStore) {
        this.context = context;
        this.listStore = listStore;
        allStore = new ArrayList<>(listStore);

    }

    @NonNull
    @Override
    // khởi tạo view cho layout thiết kế bên ngoài
    public StoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.store_item, parent, false);
        StoreHolder storeHolder = new StoreHolder(view, mOnStoreListener);
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

    public class StoreHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageStore;
        TextView nameStore, addressStore;
        OnStoreListener onStoreListener;

        public StoreHolder(@NonNull View itemView, OnStoreListener onStoreListener) {
            super(itemView);
            imageStore = (ImageView) itemView.findViewById(R.id.imagestore);
            nameStore = (TextView) itemView.findViewById(R.id.namestore);
            addressStore = (TextView) itemView.findViewById(R.id.addressstore);
            itemView.setOnClickListener(this);
            this.onStoreListener = onStoreListener;
        }


        @Override
        public void onClick(View v) {
            onStoreListener.onStoreClick(getAbsoluteAdapterPosition());
        }
    }

    @Override
    public Filter getFilter() {
        return storeFilter;
    }

    private Filter storeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<DataStore> filterStore = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filterStore.addAll(allStore);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (DataStore store : allStore) {
                    if (store.getName().toLowerCase().contains(filterPattern)) {
                        filterStore.add(store);
                    }
                }

            }
            FilterResults results = new FilterResults();
            results.values = filterStore;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listStore.clear();
            listStore.addAll((ArrayList) results.values);
            notifyDataSetChanged();

        }
    };

}
