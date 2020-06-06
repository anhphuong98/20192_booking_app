package com.example.booking_app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booking_app.R;
import com.example.booking_app.activity.changeUserInfo.ChangeAddress;
import com.example.booking_app.activity.changeUserInfo.ChangeName;
import com.example.booking_app.activity.changeUserInfo.ChangePassword;
import com.example.booking_app.activity.changeUserInfo.ChangePhone;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailUserInfo extends AppCompatActivity {

    private ImageView backUser;
    private ImageView avatar;
    private TextView name;
    private TextView email;
    private TextView address;
    private TextView phoneNumber;

    private ImageView changeName;
    private ImageView changeAddress;
    private ImageView changePhone;
    private TextView changePass;
    SharedPreferences sharedPreferences;
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
        changeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailUserInfo.this, ChangeName.class);
                intent.putExtra("nameUser", name.getText().toString());
                startActivity(intent);
            }
        });
        changeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailUserInfo.this, ChangeAddress.class);
                intent.putExtra("addressUser", address.getText().toString());
                startActivity(intent);
            }
        });
        changePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailUserInfo.this, ChangePhone.class);
                intent.putExtra("phoneUser", phoneNumber.getText().toString());
                startActivity(intent);
            }
        });
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailUserInfo.this, ChangePassword.class);
                startActivity((intent));
            }
        });
    }

    public void addData() {
        sharedPreferences = this.getSharedPreferences("userinfo", MODE_PRIVATE);
        String url_image = sharedPreferences.getString("avatar", "");
        Picasso.with(DetailUserInfo.this).load(url_image).into(avatar);
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
        changeName = (ImageView) findViewById(R.id.changeName);
        changeAddress = (ImageView) findViewById(R.id.changeAddress);
        changePhone = (ImageView) findViewById(R.id.changePhone);
        changePass = (TextView) findViewById(R.id.changePass);
    }

    @Override
    protected void onResume() {
        String u_name = sharedPreferences.getString("name", "");
        name.setText(u_name);
        String u_address = sharedPreferences.getString("address", "");
        address.setText(u_address);
        String u_phone = sharedPreferences.getString("phoneNumber", "");
        phoneNumber.setText(u_phone);
        super.onResume();
    }
}
