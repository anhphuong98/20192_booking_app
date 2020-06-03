package com.example.booking_app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_app.R;
import com.example.booking_app.adapter.StoreDishAdapter;
import com.example.booking_app.models.dish.StoreDish;

import java.util.ArrayList;

public class Storedetail extends AppCompatActivity {
    ImageView storeimg, additem;
    TextView stname, steva, stopcltime, staddress, stphone;
    RecyclerView listdish;
    StoreDishAdapter storeDishAdapter;
    private ArrayList<StoreDish> stdish;
    private int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storedetail);
        init();
        addItem();

        listdish.setHasFixedSize(true);
        //use linear layout manager
        LinearLayoutManager layoutmanager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        listdish.setLayoutManager(layoutmanager);
        storeDishAdapter = new StoreDishAdapter(this, stdish);
        listdish.setAdapter(storeDishAdapter);

        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
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

    public void addItem(){
        stdish = new ArrayList<StoreDish>();
        stdish.add(new StoreDish(1, 1, 1, R.drawable.tradao, "10.000", 10, "tra dao"));
        stdish.add(new StoreDish(2, 1, 1, R.drawable.trasua, "12.000", 10, "tra sua"));
        stdish.add(new StoreDish(3, 1,2,R.drawable.thitnuong, "8.000", 10, "thit nuong"));
        stdish.add(new StoreDish(1, 1, 1, R.drawable.tradao, "10.000", 10, "tra dao"));
        stdish.add(new StoreDish(2, 1, 1, R.drawable.trasua, "12.000", 10, "tra sua"));
        stdish.add(new StoreDish(3, 1,2,R.drawable.thitnuong, "8.000", 10, "thit nuong"));
        stdish.add(new StoreDish(1, 1, 1, R.drawable.tradao, "10.000", 10, "tra dao"));
        stdish.add(new StoreDish(2, 1, 1, R.drawable.trasua, "12.000", 10, "tra sua"));
        stdish.add(new StoreDish(3, 1,2,R.drawable.thitnuong, "8.000", 10, "thit nuong"));
    }
}
