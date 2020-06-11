package com.example.booking_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
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
    ConstraintLayout storedetail;
    ImageView storeimg, additem, cartdishimg, x_icon;
    DishService dishService;
    TextView stname, steva, stopcltime, staddress, stphone, quantityincart, delete_cart;
    Button cartbtn, confirmorder;
    CardView cardView;
    RecyclerView listdish;
    StoreDishAdapter storeDishAdapter;
    RecyclerView recycler_view_cart;
    private ArrayList<CartDish> listCartDish = new ArrayList<CartDish>();

    private ArrayList<StoreDish> stdish = new ArrayList<StoreDish>();
    private int in;

    private int quantity = 1;
    private int cartquantity = 0;
    public int updateRecycler = 0;
    public BottomSheetDialog bottomSheetDialog;
    public CartAdapter cartAdapter;

    Clickdishitem clickdishitem = new Clickdishitem() {
        @Override
        public void click(int id) {
            StoreDish dish = stdish.get(id);
            int position = 0;
            in = 0;

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
                cartquantity +=1;
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

    ChangeDishCartQuantity changeDishCartQuantity= new ChangeDishCartQuantity() {
        @Override
        public void changeQuantity(int id, View view) {
            CartDish cartDish = listCartDish.get(id);
            switch (view.getId()){
                case R.id.cart_dish_plus:
                    cartquantity +=1;
                    cartDish.setQuantity(cartDish.getQuantity() + 1);
                    break;
                case R.id.cart_dish_sub:
                    cartquantity -= 1;
                    cartDish.setQuantity(cartDish.getQuantity() - 1);
                    break;
            }

            if (cartDish.getQuantity()<=0){
                Log.d("testing", cartDish.getName());
                updateCart(listCartDish.indexOf(cartDish));

            }
            if(cartquantity <=0){
                bottomSheetDialog.dismiss();
                cardView.setVisibility(View.INVISIBLE);
                cartbtn.setVisibility(View.INVISIBLE);
            }
            quantityincart.setText(Integer.toString(cartquantity));
        }
    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storedetail);
        dishService = APIUtils.getDishService();
        init();
        getData();
        setupCart();

        confirmOrder();
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

        //bottom cart
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.cart_detail);
        recycler_view_cart = (RecyclerView) bottomSheetDialog.findViewById(R.id.recycler_view_cart);
        cartAdapter = new CartAdapter(getApplicationContext(), listCartDish, changeDishCartQuantity);
        confirmorder = (Button) bottomSheetDialog.findViewById(R.id.confirm_button);
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


    public void setupCart(){
        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cartbtn.setBackgroundColor(000000);
                bottomSheetDialog.show();
                delete_cart = (TextView) bottomSheetDialog.findViewById(R.id.delete_cart);
                x_icon = (ImageView) bottomSheetDialog.findViewById(R.id.x_icon);
                if(x_icon != null) {
                    x_icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomSheetDialog.dismiss();
                        }
                    });
                }
                delete_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("testing", "i am testing");
                        listCartDish.clear();
                        bottomSheetDialog.dismiss();
                        cartquantity = 0;
                        cartbtn.setVisibility(View.INVISIBLE);
                        cardView.setVisibility(View.INVISIBLE);
                    }
                });

                recycler_view_cart.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                recycler_view_cart.setLayoutManager(layoutManager);
                recycler_view_cart.setAdapter(cartAdapter);


            }
        });

        //cartbtn.setBackgroundColor(808080);
        if(x_icon != null) {
            x_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   bottomSheetDialog.dismiss();
                }
            });

        }

    }
    public void updateCart(int position){
            listCartDish.remove(position);
            recycler_view_cart.removeViewAt(position);
            cartAdapter.notifyItemRemoved(position);
            cartAdapter.notifyItemRangeChanged(position, listCartDish.size());
            cartAdapter.notifyDataSetChanged();
    }

    public void confirmOrder(){
            confirmorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ConfirmOrder.class);
                    startActivity(intent);
                }
            });
    }

}
