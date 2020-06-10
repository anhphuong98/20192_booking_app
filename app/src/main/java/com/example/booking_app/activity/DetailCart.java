package com.example.booking_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booking_app.R;

public class DetailCart extends AppCompatActivity  {

    TextView delall;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_detail);
        init();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailCart.this, ConfirmOrder.class);
                startActivity(intent);
            }
        });
    }

    public void init(){
        confirm = (Button) findViewById(R.id.confirm_button);
    }

}
