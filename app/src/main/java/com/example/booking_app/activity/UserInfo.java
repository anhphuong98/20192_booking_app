package com.example.booking_app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booking_app.R;
import com.squareup.picasso.Picasso;

public class UserInfo extends AppCompatActivity {
    private ImageView avatarUser;
    private TextView nameUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        avatarUser = (ImageView) findViewById(R.id.avatarUser);
        nameUser = (TextView) findViewById(R.id.nameUser);
        addData();
        avatarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfo.this, DetailUserInfo.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.out);
            }
        });
    }
    public void addData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("userinfo", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String url = sharedPreferences.getString("avatar", "");
        nameUser.setText(name);
        Picasso.with(UserInfo.this).load(url).into(avatarUser);
    }
}
