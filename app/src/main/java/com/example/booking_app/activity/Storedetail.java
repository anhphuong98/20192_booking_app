package com.example.booking_app.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_app.R;
import com.example.booking_app.adapter.CartAdapter;
import com.example.booking_app.adapter.StoreDishAdapter;
import com.example.booking_app.connection.APIUtils;
import com.example.booking_app.connection.DishService;
import com.example.booking_app.models.dish.CartDish;
import com.example.booking_app.models.dish.StoreDish;
import com.example.booking_app.models.dish.StoreDishResponse;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Storedetail extends AppCompatActivity  {
    ImageView storeimg, additem, cartdishimg;
    DishService dishService;
    TextView stname, steva, stopcltime, staddress, stphone, quantityincart;
    Button cartbtn;
    CardView cardView;
    RecyclerView listdish;
    StoreDishAdapter storeDishAdapter;
    ImageView x_icon;
    RecyclerView recycler_view_cart;
    private ArrayList<CartDish> listCartDish = new ArrayList<CartDish>();

    private ArrayList<StoreDish> stdish = new ArrayList<StoreDish>();
    private int in;

    private int quantity = 1;
    private int cartquantity = 0;

    Clickdishitem clickdishitem = new Clickdishitem() {
        @Override
        public void click(int id) {
            StoreDish dish = stdish.get(id);
            int position = 0;
            //CartDish cdish = new CartDish(dish.getId(), dish.getName(), dish.getPrice(), quantity, dish.getUrlImage())
            for(int i = 0; i < listCartDish.size(); i++){
                if(dish.getId() == listCartDish.get(i).getId()){
                    in = 1;
                    position = i;
                    break;
                } else {
                    in = 0;
                }
            }
            if (in == 1){
                CartDish cdish = listCartDish.get(position);
                cdish.setQuantity(cdish.getQuantity() + 1);
            } else {
                CartDish cartDish = new CartDish(dish.getId(), dish.getName(), dish.getPrice(), quantity, dish.getUrlImage());
                cartquantity += 1;
                listCartDish.add(cartDish);
            }

            quantityincart.setText(Integer.toString(cartquantity));
            if(cartquantity >= 1){
                cardView.setVisibility(View.VISIBLE);
            }

            cartbtn.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("cart", "blabla");
        if(cartquantity >= 1){

            cardView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onRestart() {
        Log.d("cart", "rt");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("cart", "pa");
    }

    @Override
    protected void onStop() {
        Log.d("cart", "st");
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storedetail);
        dishService = APIUtils.getDishService();
        init();

        getData();


    }

    public void init(){
        storeimg = (ImageView) findViewById(R.id.storeavt);
        stname = (TextView) findViewById(R.id.stname);
        steva = (TextView) findViewById(R.id.evaluation);
        stopcltime = (TextView) findViewById(R.id.opcltime);
        additem = (ImageView) findViewById(R.id.additem);
        staddress = (TextView) findViewById(R.id.staddress);
        stphone = (TextView) findViewById(R.id.stphone);
        cartdishimg = (ImageView) findViewById(R.id.cart_dish_image);
        listdish = (RecyclerView) findViewById(R.id.listitem);
        quantityincart = (TextView) findViewById(R.id.quantityincart);
        cardView = (CardView) findViewById(R.id.cardViewquantity);
        cartbtn = (Button) findViewById(R.id.cartbtn);
        cardView.setVisibility(View.INVISIBLE);
        cartbtn.setVisibility(View.INVISIBLE);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.cart_detail);

        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
                x_icon = (ImageView) bottomSheetDialog.findViewById(R.id.x_icon);
                if(x_icon != null) {
                    x_icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomSheetDialog.dismiss();
                        }
                    });
                }
                recycler_view_cart = (RecyclerView) bottomSheetDialog.findViewById(R.id.recycler_view_cart);
                recycler_view_cart.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                recycler_view_cart.setLayoutManager(layoutManager);
                CartAdapter cartAdapter = new CartAdapter(getApplicationContext(), listCartDish);
                recycler_view_cart.setAdapter(cartAdapter);

            }
        });

        if(x_icon != null) {
            x_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });
        }
    }

    public void getData(){
        Call<StoreDishResponse> storeDishResponseCall = dishService.getStoreDish(1);
        storeDishResponseCall.enqueue(new Callback<StoreDishResponse>() {
            @Override
            public void onResponse(Call<StoreDishResponse> call, Response<StoreDishResponse> response) {
                if(response.isSuccessful()){
                    stdish = response.body().getStoreDishes();
                    listdish.setHasFixedSize(true);
                    //use linear layout manager
                    LinearLayoutManager layoutmanager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    listdish.setLayoutManager(layoutmanager);
                    storeDishAdapter = new StoreDishAdapter(Storedetail.this, stdish, clickdishitem);
                    listdish.setAdapter(storeDishAdapter);
                }
            }

            @Override
            public void onFailure(Call<StoreDishResponse> call, Throwable t) {
                Log.e("loi", t.getMessage());
            }
        });
    }



}
