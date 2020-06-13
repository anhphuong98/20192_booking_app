package com.example.booking_app.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.booking_app.models.user.DataUser;
import com.example.booking_app.models.user.UpdateUserReponse;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

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

    private final String URL_SERVER = "http://192.168.1.186:8080";
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


        init();
        addItem();
        getItemOrder();

        // doan code gia lap them order
        DishOrder dishOrder1 = new DishOrder(1, 3, 21000);
        DishOrder dishOrder2 = new DishOrder(13, 2, 30000);
        ArrayList<DishOrder> listDishOrder = new ArrayList<>();
        listDishOrder.add(dishOrder1);
        listDishOrder.add(dishOrder2);
        final Order order = new Order("Yen Phong - Bac Ninh", "Nguyen Anh Phuong",372109881, "Mang nhanh len anh oi", 20000, 1, listDishOrder);
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
        sharedPreferences = this.getSharedPreferences("userinfo", MODE_PRIVATE);
    }

    public void getItemOrder(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        listitem.setLayoutManager(linearLayoutManager);
        confirmOrderAdapter = new ConfirmOrderAdapter(ConfirmOrder.this, cartDish);
        listitem.setAdapter(confirmOrderAdapter);

    }
    public void addItem(){
        cartDish.add(new CartDish(1, "thit nuong", (double) 10000, 2, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3DM6YTLcOuFyU&psig=AOvVaw3vI8s6NHvKPHOBRmQE6R-1&ust=1591860504857000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCJCkjdLc9ukCFQAAAAAdAAAAABAD"));
        cartDish.add(new CartDish(2, "thit nuong mat ong", (double) 10000, 2, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3DM6YTLcOuFyU&psig=AOvVaw3vI8s6NHvKPHOBRmQE6R-1&ust=1591860504857000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCJCkjdLc9ukCFQAAAAAdAAAAABAD"));
    }
    public void postOrder(Order order, String auth) {
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