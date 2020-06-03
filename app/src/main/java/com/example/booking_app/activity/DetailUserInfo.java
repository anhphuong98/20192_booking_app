package com.example.booking_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.booking_app.R;
import com.squareup.picasso.Picasso;

public class DetailUserInfo extends AppCompatActivity {

    private ImageView backUser;
    private ImageView avatar;
    private TextView name;
    private TextView email;
    private TextView address;
    private TextView phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user_info);
        addID();
        addData();
        backUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void addData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("userinfo", MODE_PRIVATE);
        String url_image = sharedPreferences.getString("avatar", "");
        Picasso.get().load(url_image).into(avatar);
        String u_name = sharedPreferences.getString("name", "");
        name.setText(u_name);
        String u_email = sharedPreferences.getString("email", "");
        email.setText(u_email);
        String u_address = sharedPreferences.getString("address", "");
        address.setText(u_address);
        String u_phone = sharedPreferences.getString("phoneNumber", "");
        phoneNumber.setText(u_phone);
    }
    public void addID() {
        avatar = (ImageView) findViewById(R.id.detail_image);
        name = (TextView) findViewById(R.id.detail_name);
        email = (TextView) findViewById(R.id.detail_email);
        address = (TextView) findViewById(R.id.detail_address);
        phoneNumber = (TextView) findViewById(R.id.detail_phone);
        backUser = (ImageView) findViewById(R.id.backUser);
    }

}
