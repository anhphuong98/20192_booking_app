package com.example.booking_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.booking_app.R;

public class UserInfo extends AppCompatActivity {
    private ImageView avatarUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        avatarUser = (ImageView) findViewById(R.id.avatarUser);
        avatarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfo.this, DetailUserInfo.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.out);
            }
        });
    }
}
