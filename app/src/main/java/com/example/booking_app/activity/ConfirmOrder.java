package com.example.booking_app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
    TextView edit;
    Button submit;
    RecyclerView listitem;

    OrderService orderService;
    SharedPreferences sharedPreferences;

    ArrayList<CartDish> cartDish = new ArrayList<CartDish>();
    ConfirmOrderAdapter confirmOrderAdapter;
    ArrayList<DishOrder> listDishOrder = new ArrayList<>();
//    editor.putString("token", "Bearer "+ userResponse.getToken());
//                        editor.putBoolean("signined", true);
//                        editor.putInt("id", userResponse.getData().getId());
//                        editor.putString("avatar", userResponse.getData().getUrl());
//                        editor.putString("email", userResponse.getData().getEmail());
//                        editor.putString("address", userResponse.getData().getAddress());
//                        editor.putString("phoneNumber", userResponse.getData().getPhone());
//                        editor.putString("password", userResponse.getData().getPassword());
//                        editor.putString("name", userResponse.getData().getName());

    private final String URL_SERVER = "http://192.168.1.186:4000";
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
        sharedPreferences = this.getSharedPreferences("userinfo", MODE_PRIVATE);

        init();
        getItemOrder();

        final Order order = new Order(sharedPreferences.getString("address", ""), "Nguyen Anh Phuong",372109881, "Mang nhanh len anh oi", 20000, 1, listDishOrder);
        final String tokenAuth = sharedPreferences.getString("token", "");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postOrder(order, tokenAuth);
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
        Intent intent = this.getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");
        ArrayList<CartDish> cart = (ArrayList<CartDish>) bundle.getSerializable("cart");
        System.out.println("Ngan abc gi do: " + cart.get(0).getName());

        Double price = Double.valueOf(0);
        for (int i =0; i < cart.size(); i++){
            CartDish item = cart.get(i);
            cartDish.add(item);
            price += item.getPrice()*item.getQuantity();
        }
        shipfee.setText(confirmOrderAdapter.convertMoney(20000.0));
        Double ttpr = price + 20000;
        subtotalprice.setText(confirmOrderAdapter.convertMoney(price));
        totalprice.setText(confirmOrderAdapter.convertMoney(ttpr));
    }



    public void postOrder(Order order, String auth) {
        for(int i=0; i < cartDish.size(); i++){
            listDishOrder.add(new DishOrder(cartDish.get(i).getId(), cartDish.get(i).getQuantity(), (float) (cartDish.get(i).getQuantity()*cartDish.get(i).getPrice())));
        }
        orderService = APIUtils.getOrderService();
        Call<OrderResponse> postOrderResponse = orderService.postOrder(order, auth);
        postOrderResponse.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                OrderResponse orderResponse = response.body();
                int order_id = orderResponse.getOrder_id();
                mSocket.connect();
                mSocket.emit("client-send-order",  order_id);
                mSocket.on("shipper-receive-order-" + order_id, onNewMessage);
                mSocket.on("server-cancel-order-" + order_id, onNewMessage1);
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
                    String message;
                    message = data.optString("except");
                    System.out.println("Nhan roi" + message);
                    System.out.println(args[0]);
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
                    String message;
                    message = data.optString("except");
                    System.out.println("Huy roi" + message);
                    System.out.println(args[0]);
                };
            });
        };
    };
}