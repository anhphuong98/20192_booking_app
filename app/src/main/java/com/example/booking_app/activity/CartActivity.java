package com.example.booking_app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_app.R;
import com.example.booking_app.adapter.CartAdapter;
import com.example.booking_app.models.dish.CartDish;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    Button cart;
    ImageView x_icon;
    RecyclerView recycler_view_cart;
    TextView xoa;
    private ArrayList<CartDish> listCartDish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        cart = (Button) findViewById(R.id.cart);
        addItem();
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.cart_detail);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
                x_icon = (ImageView) bottomSheetDialog.findViewById(R.id.x_icon);
                xoa = (TextView) bottomSheetDialog.findViewById(R.id.delete_cart);
                if(x_icon != null) {
                    x_icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomSheetDialog.dismiss();
                        }
                    });
                }
                if(xoa != null){
                    xoa.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            System.out.println("da xoa cartdetail");
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
//        if(x_icon != null) {
//            x_icon.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    bottomSheetDialog.dismiss();
//                }
//            });
//        }
    }

    public void addItem() {
        listCartDish = new ArrayList<CartDish>();
        listCartDish.add(new CartDish("Trà sữa", "13000đ", 2, R.drawable.tradao));
        listCartDish.add(new CartDish("Trà đào", "14000đ", 2, R.drawable.trasua));
        listCartDish.add(new CartDish("Thịt xiên", "15000đ", 2, R.drawable.thitnuong));
    }
}
