package com.example.booking_app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_app.R;
import com.example.booking_app.adapter.OrderAdapter;
import com.example.booking_app.connection.APIUtils;
import com.example.booking_app.connection.OrderService;
import com.example.booking_app.models.order.DataOrder;
import com.example.booking_app.models.order.OrderResponse;
import com.example.booking_app.models.store.DataStore;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderUserDetail extends AppCompatActivity {
    ImageView returnHome;
    TextView orderDetailTitle,nameStore,addressStore,custom,nameCustom,addressCustom,timeOrder,detailOrder,priceOrder,feeShip,totalPrice;
    RecyclerView listDish;
    OrderService orderService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderService = APIUtils.getOrderById();
        setContentView(R.layout.order_detail_item);
        init();
    }

    private void init() {
        listDish = (RecyclerView) findViewById(R.id.listDish);
        returnHome = (ImageView)findViewById(R.id.returnHome);
        orderDetailTitle = (TextView)findViewById(R.id.orderDetailTitle);
        nameStore = (TextView)findViewById(R.id.nameStore);
        addressStore = (TextView)findViewById(R.id.addressStore);
        custom = (TextView)findViewById(R.id.custom);
        nameCustom = (TextView)findViewById(R.id.nameCustom);
        addressCustom = (TextView)findViewById(R.id.addressCustom);
        timeOrder = (TextView)findViewById(R.id.timeOrder);
        detailOrder = (TextView)findViewById(R.id.detailOrder);
        priceOrder = (TextView)findViewById(R.id.priceOrder);
        feeShip = (TextView)findViewById(R.id.feeShip);
        totalPrice = (TextView)findViewById(R.id.totalPrice);
    }

//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.order_detail_item, container, false);
//        listDish = (RecyclerView) view.findViewById(R.id.listDish);
//        returnHome = (ImageView)view.findViewById(R.id.returnHome);
//        orderDetailTitle = (TextView)view.findViewById(R.id.orderDetailTitle);
//        nameStore = (TextView)view.findViewById(R.id.nameStore);
//        addressStore = (TextView)view.findViewById(R.id.addressStore);
//        custom = (TextView)view.findViewById(R.id.custom);
//        nameCustom = (TextView)view.findViewById(R.id.nameCustom);
//        addressCustom = (TextView)view.findViewById(R.id.addressCustom);
//        timeOrder = (TextView)view.findViewById(R.id.timeOrder);
//        detailOrder = (TextView)view.findViewById(R.id.detailOrder);
//        priceOrder = (TextView)view.findViewById(R.id.priceOrder);
//        feeShip = (TextView)view.findViewById(R.id.feeShip);
//        totalPrice = (TextView)view.findViewById(R.id.totalPrice);
//
//     return view;
//    }

    public void getOrderById(){
        SharedPreferences tokenCache = getApplication().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        String token = tokenCache.getString("token","");
        DataOrder order = (DataOrder) this.getIntent().getSerializableExtra("OrderDetail");

        nameStore.setText(order.getStore().getName());
        System.out.print("aaaaaa" + order.getStore().getName());
        addressCustom.setText(order.getStore().getAddress());


        orderService.getOrderUser(token,order.getId()).enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                OrderResponse orderResponse = response.body();
                if(orderResponse != null){
                    if (orderResponse.getSuccess()){
                        ArrayList<DataOrder> listOrder = orderResponse.getData();
                        listDish.setHasFixedSize(true);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                        listDish.setLayoutManager(layoutManager);
                        OrderAdapter orderAdapter = new OrderAdapter(getApplicationContext(),listOrder);
                        listDish.setAdapter(orderAdapter);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Chưa có đơn hàng nào!",Toast.LENGTH_SHORT).show();

                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Chưa có đơn hàng nào!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Log.e("Loi", t.getMessage());
            }
        });

    }


}
