package com.example.booking_app.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
    ImageView returnListOrder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail_item);
        init();
        getOrderById();
        returnListOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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
        returnListOrder = (ImageView) findViewById(R.id.returnListOrder);
    }
    public String convertMoney(float money) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currentMoney = NumberFormat.getCurrencyInstance(localeVN);
        return currentMoney.format(money);
    }
    public void getOrderById(){
        orderService = APIUtils.getOrderService();
        SharedPreferences tokenCache = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        String token = tokenCache.getString("token","");
        final DataOrder order = (DataOrder)  getIntent().getSerializableExtra("OrderDetail");
        nameStore.setText(order.getStore().getName());
        addressStore.setText(order.getStore().getAddress());
        String infoCustom = order.getName_recieve() + " - 0" +(int)(order.getPhone_recieve());
        nameCustom.setText(infoCustom.toString());
        addressCustom.setText(order.getAddress());
        String s = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(order.getTime());
        timeOrder.setText(s);
        feeShip.setText(convertMoney(order.getShip_price()));
        Call<OrderDetailResponse> orderDetailResponse = orderService.getOrderById(token,order.getId());

        orderDetailResponse.enqueue(new Callback<OrderDetailResponse>() {
            @Override
            public void onResponse(Call<OrderDetailResponse> call, Response<OrderDetailResponse> response) {
                OrderDetailResponse orderDetailResponse = response.body();
                if(orderDetailResponse != null){
                    if (orderDetailResponse.isSuccess()){
                        ArrayList<DataOrderDetail> listDishOrder = orderDetailResponse.getData();
                        for(DataOrderDetail dishOrder:listDishOrder){
                            sumPriceOrder += (float) dishOrder.getCurrent_price()*dishOrder.getQuantity();
                        }
                        priceOrder.setText(convertMoney(sumPriceOrder));
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
