package com.example.booking_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_app.R;
import com.example.booking_app.adapter.StoreDishAdapter;
import com.example.booking_app.connection.APIUtils;
import com.example.booking_app.connection.DishService;
import com.example.booking_app.models.dish.StoreDish;
import com.example.booking_app.models.dish.StoreDishResponse;
import com.example.booking_app.models.store.DataStore;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Storedetail extends AppCompatActivity  {
    ImageView storeimg, additem;
    DishService dishService;
    TextView stname, steva, stopcltime, staddress, stphone;
    RecyclerView listdish;
    StoreDishAdapter storeDishAdapter;
    private ArrayList<StoreDish> stdish = new ArrayList<StoreDish>();
    private int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storedetail);
        dishService = APIUtils.getDishService();
        init();
       // addItem();

        getData();


//        additem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    public void init(){
        storeimg = (ImageView) findViewById(R.id.storeavt);
        stname = (TextView) findViewById(R.id.stname);
        steva = (TextView) findViewById(R.id.evaluation);
        stopcltime = (TextView) findViewById(R.id.opcltime);
        additem = (ImageView) findViewById(R.id.additem);
        staddress = (TextView) findViewById(R.id.staddress);
        stphone = (TextView) findViewById(R.id.stphone);
        listdish = (RecyclerView) findViewById(R.id.listitem);
    }

    public void getData(){
        DataStore store = (DataStore) getIntent().getSerializableExtra("StoreDetail");
        Call<StoreDishResponse> storeDishResponseCall = dishService.getStoreDish(store.getId());
        storeDishResponseCall.enqueue(new Callback<StoreDishResponse>() {
            @Override
            public void onResponse(Call<StoreDishResponse> call, Response<StoreDishResponse> response) {
                if(response.isSuccessful()){
                    stdish = response.body().getStoreDishes();
                    listdish.setHasFixedSize(true);
                    //use linear layout manager
                    LinearLayoutManager layoutmanager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    listdish.setLayoutManager(layoutmanager);
                    storeDishAdapter = new StoreDishAdapter(Storedetail.this, stdish);
                    listdish.setAdapter(storeDishAdapter);
                }
            }

            @Override
            public void onFailure(Call<StoreDishResponse> call, Throwable t) {
                Log.e("loi", t.getMessage());
            }
        });
    }

//    public void addItem(){
//        stdish = new ArrayList<StoreDish>();
//        stdish.add(new StoreDish(1, 1, 1, R.drawable.tradao, "10.000", 10, "tra dao"));
//        stdish.add(new StoreDish(2, 1, 1, R.drawable.trasua, "12.000", 10, "tra sua"));
//        stdish.add(new StoreDish(3, 1,2,R.drawable.thitnuong, "8.000", 10, "thit nuong"));
//        stdish.add(new StoreDish(1, 1, 1, R.drawable.tradao, "10.000", 10, "tra dao"));
//        stdish.add(new StoreDish(2, 1, 1, R.drawable.trasua, "12.000", 10, "tra sua"));
//        stdish.add(new StoreDish(3, 1,2,R.drawable.thitnuong, "8.000", 10, "thit nuong"));
//        stdish.add(new StoreDish(1, 1, 1, R.drawable.tradao, "10.000", 10, "tra dao"));
//        stdish.add(new StoreDish(2, 1, 1, R.drawable.trasua, "12.000", 10, "tra sua"));
//        stdish.add(new StoreDish(3, 1,2,R.drawable.thitnuong, "8.000", 10, "thit nuong"));
//    }
}
