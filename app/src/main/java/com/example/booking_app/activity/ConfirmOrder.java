package com.example.booking_app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_app.R;
import com.example.booking_app.adapter.ConfirmOrderAdapter;
import com.example.booking_app.connection.APIUtils;
import com.example.booking_app.connection.OrderService;
import com.example.booking_app.models.dish.CartDish;
import com.example.booking_app.models.order.DishOrder;
import com.example.booking_app.models.order.Order;
import com.example.booking_app.models.order.OrderResponse;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmOrder extends AppCompatActivity {
    ImageView userimg;
    TextView username, useraddress, store, subtotal, ship, discount, total;
    TextView subtotalprice, shipfee, discountprice, totalprice;
    Button submit;
    RecyclerView listitem;
    BottomSheetDialog loadFindShipper;
    BottomSheetDialog cancelOrder;
    BottomSheetDialog successOrder;

    OrderService orderService;
    SharedPreferences sharedPreferences;

    ArrayList<CartDish> cartDish = new ArrayList<CartDish>();
    ConfirmOrderAdapter confirmOrderAdapter;
    int accept = 0;
    int store_id;
    CountDownTimer countDownTimer;

    ImageView backOrder;

    private final String URL_SERVER = "http://192.168.43.130:4000";
    ArrayList<DishOrder> listDishOrder = new ArrayList<>();

    private Socket mSocket;

    {
        try {
            mSocket = IO.socket(URL_SERVER);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        loadFindShipper = new BottomSheetDialog(this);
        cancelOrder = new BottomSheetDialog(this);
        successOrder = new BottomSheetDialog(this);
        cancelOrder.setContentView(R.layout.cancel_order);
        loadFindShipper.setContentView(R.layout.loading_find_shipper);
        successOrder.setContentView(R.layout.success_order);
        sharedPreferences = this.getSharedPreferences("storeID", MODE_PRIVATE);
        store_id = sharedPreferences.getInt("store_id", -1);
        sharedPreferences = this.getSharedPreferences("userinfo", MODE_PRIVATE);
        init();
        getItemOrder();


        backOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer = new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        loadFindShipper.show();
                    }

                    @Override
                    public void onFinish() {
                        Order order = new Order(sharedPreferences.getString("address", ""), sharedPreferences.getString("name", ""), Integer.parseInt(sharedPreferences.getString("phoneNumber", "")), "Thêm nhiều một chút", 20000, store_id, listDishOrder);
                        String tokenAuth = sharedPreferences.getString("token", "");
                        postOrder(order, tokenAuth);
                    }
                };
                countDownTimer.start();
            }
        });



    }
    public void init(){
        //user information
        userimg = (ImageView) findViewById(R.id.avtuserord);
        username = (TextView) findViewById(R.id.receivername);
        useraddress = (TextView) findViewById(R.id.receiveraddress);

        Picasso.with(getApplicationContext()).load(sharedPreferences.getString("avatar", "https://image.flaticon.com/icons/png/512/149/149071.png")).into(userimg);
        username.setText(sharedPreferences.getString("name", "") + " - " +sharedPreferences.getString("phoneNumber", ""));
        useraddress.setText(sharedPreferences.getString("address", ""));

        //store
        store = (TextView) findViewById(R.id.order_store_name);

        //money
        subtotal = (TextView) findViewById(R.id.subprice);
        subtotalprice = (TextView) findViewById(R.id.subtotalprice);
        ship = (TextView) findViewById(R.id.shipfee);
        shipfee = (TextView) findViewById(R.id.shipfee2);
        total = (TextView) findViewById(R.id.totalprice1);
        totalprice = (TextView) findViewById(R.id.totalprice2);

        //button
        submit = (Button) findViewById(R.id.submitorder);

        //list item order
        listitem = (RecyclerView) findViewById(R.id.list_item_order);

        backOrder = (ImageView) findViewById(R.id.backOrder);

        //


    }

    public void getItemOrder(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        listitem.setLayoutManager(linearLayoutManager);
        confirmOrderAdapter = new ConfirmOrderAdapter(ConfirmOrder.this, cartDish);
        listitem.setAdapter(confirmOrderAdapter);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");
        ArrayList<CartDish> cart = (ArrayList<CartDish>) bundle.getSerializable("cart");

        Double price = Double.valueOf(0);
        for (int i =0; i < cart.size(); i++){
            CartDish item = cart.get(i);
            cartDish.add(item);
            price += item.getPrice()*item.getQuantity();
            DishOrder dishOrder = new DishOrder(item.getId(), item.getQuantity(), (float) (item.getQuantity() * item.getPrice()));
            listDishOrder.add(dishOrder);
        }
        shipfee.setText(confirmOrderAdapter.convertMoney(20000.0));
        Double ttpr = price + 20000;
        subtotalprice.setText(confirmOrderAdapter.convertMoney(price));
        totalprice.setText(confirmOrderAdapter.convertMoney(ttpr));
    }



    public void postOrder(Order order, String auth) {
        orderService = APIUtils.getOrderService();
        Call<OrderResponse> postOrderResponse = orderService.postOrder(order, auth);
        postOrderResponse.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                OrderResponse orderResponse = response.body();
                if(orderResponse.isSuccess()) {
                    int order_id = orderResponse.getOrder_id();
                    mSocket.connect();
                    mSocket.emit("client-send-order",  order_id);
                    mSocket.on("shipper-receive-order-" + order_id, onNewMessage);
                    mSocket.on("server-cancel-order-" + order_id, onNewMessage1);
                }else{
                    cancelOrder.show();
                    clickOkCancelOrder(cancelOrder);
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Log.i("loi them order: ", t.getMessage());
            }
        });
    }
    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    accept = data.optInt("accept");
                    successOrder.show();
                    loadFindShipper.dismiss();
                    Button clickSuccessOrder = (Button) successOrder.findViewById(R.id.success_order1);
                    clickSuccessOrder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sharedPreferences.edit().putBoolean("check", true).commit();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        }
                    });
                };
            });
        };
    };

    private Emitter.Listener onNewMessage1 = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    accept = data.optInt("accept");
                    cancelOrder.show();
                    loadFindShipper.dismiss();
                    clickOkCancelOrder(cancelOrder);
                };
            });
        };
    };

    public void clickOkCancelOrder(final BottomSheetDialog bottomSheetDialog) {
        Button okCancel = (Button) bottomSheetDialog.findViewById(R.id.cancel_order1);
        okCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                loadFindShipper.dismiss();
            }
        });
    }
}