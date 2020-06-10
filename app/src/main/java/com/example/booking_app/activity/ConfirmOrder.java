package com.example.booking_app.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_app.R;
import com.example.booking_app.adapter.ConfirmOrderAdapter;
import com.example.booking_app.models.dish.CartDish;

import java.util.ArrayList;

public class ConfirmOrder extends AppCompatActivity {
    ImageView userimg;
    TextView username, useraddress, store, subtotal, ship, discount, total;
    TextView subtotalprice, shipfee, discountprice, totalprice;
    TextView edit;
    Button submit;
    RecyclerView listitem;

    ArrayList<CartDish> cartDish = new ArrayList<CartDish>();
    ConfirmOrderAdapter confirmOrderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        init();
        getItemOrder();
    }
    public void init(){
        //user information
        userimg = (ImageView) findViewById(R.id.avtuserord);
        username = (TextView) findViewById(R.id.receivername);
        useraddress = (TextView) findViewById(R.id.receiveraddress);

        //store
        store = (TextView) findViewById(R.id.order_store_name);

        //money
        subtotal = (TextView) findViewById(R.id.subprice);
        subtotalprice = (TextView) findViewById(R.id.subtotalprice);
        ship = (TextView) findViewById(R.id.shipfee);
        shipfee = (TextView) findViewById(R.id.shipfee2);
        discount = (TextView) findViewById(R.id.discountfee);
        discountprice = (TextView) findViewById(R.id.discountfee2);
        total = (TextView) findViewById(R.id.totalprice1);
        totalprice = (TextView) findViewById(R.id.totalprice2);

        //button
        edit = (TextView) findViewById(R.id.edit);
        submit = (Button) findViewById(R.id.submitorder);

        //list item order
        listitem = (RecyclerView) findViewById(R.id.list_item_order);
    }

    public void getItemOrder(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        listitem.setLayoutManager(linearLayoutManager);
        confirmOrderAdapter = new ConfirmOrderAdapter(ConfirmOrder.this, cartDish);
        listitem.setAdapter(confirmOrderAdapter);

    }
}