package com.example.booking_app.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_app.R;
import com.example.booking_app.adapter.ConfirmOrderAdapter;
import com.example.booking_app.adapter.OrderAdapter;
import com.example.booking_app.adapter.OrderDetailAdapter;
import com.example.booking_app.connection.APIUtils;
import com.example.booking_app.connection.OrderService;
import com.example.booking_app.models.dish.StoreDish;
import com.example.booking_app.models.dish.StoreDishResponse;
import com.example.booking_app.models.order.DataOrder;
import com.example.booking_app.models.order.DataOrderResponse;
import com.example.booking_app.models.orderDetail.DataOrderDetail;
import com.example.booking_app.models.orderDetail.Dish;
import com.example.booking_app.models.orderDetail.OrderDetailResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderUserDetail extends AppCompatActivity {
    ImageView returnHome;
    TextView orderDetailTitle,nameStore,addressStore,custom,nameCustom,addressCustom,timeOrder,detailOrder,priceOrder,feeShip,totalPrice;
    RecyclerView listDish;
    OrderService orderService;
    float sumPriceOrder ;
    float totalPriceOrder;
    private ArrayList<Dish> dish = new ArrayList<Dish>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderService = APIUtils.getOrderById();
        setContentView(R.layout.order_detail_item);
        init();
        getOrderById();
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

    public void getOrderById(){
        SharedPreferences tokenCache = getApplication().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        String token = tokenCache.getString("token","");
        final DataOrder order = (DataOrder)  getIntent().getSerializableExtra("OrderDetail");
        nameStore.setText(order.getStore().getName());
        addressCustom.setText(order.getStore().getAddress());
        String infoCustom = order.getName_recieve() + "-" + order.getPhone_recieve();
        nameCustom.setText(infoCustom.toString());
        addressCustom.setText(order.getAddress());
        timeOrder.setText(order.getTime());
        feeShip.setText(String.valueOf(order.getShip_price()));
        Call<OrderDetailResponse> orderDetailResponse = orderService.getOrderById(token,order.getId());

        orderDetailResponse.enqueue(new Callback<OrderDetailResponse>() {
            @Override
            public void onResponse(Call<OrderDetailResponse> call, Response<OrderDetailResponse> response) {
                OrderDetailResponse orderDetailResponse = response.body();
                if(orderDetailResponse != null){
                    if (orderDetailResponse.isSuccess()){
                        ArrayList<DataOrderDetail> listDishOrder = orderDetailResponse.getData();
                        for(DataOrderDetail dishOrder:listDishOrder){
                            sumPriceOrder += dishOrder.getCurrent_price()*dishOrder.getQuantity();
                        }
                        priceOrder.setText(String.valueOf(sumPriceOrder));
                        totalPriceOrder = sumPriceOrder + order.getShip_price();
                        totalPrice.setText(String.valueOf(totalPriceOrder));
                        listDish.setHasFixedSize(true);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                        listDish.setLayoutManager(layoutManager);
                        OrderDetailAdapter orderDetailAdapter = new OrderDetailAdapter(getApplicationContext(), listDishOrder);
                        listDish.setAdapter(orderDetailAdapter);
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
            public void onFailure(Call<OrderDetailResponse> call, Throwable t) {
                Log.e("Loi", t.getMessage());
            }


        });

    }


}
