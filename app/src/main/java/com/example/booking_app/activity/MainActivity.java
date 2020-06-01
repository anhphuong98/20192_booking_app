package com.example.booking_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.booking_app.R;

public class MainActivity extends AppCompatActivity {

    Button btn;
    private ImageView avatarUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        avatarUser = (ImageView) findViewById(R.id.avatarUser);

        avatarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailUserInfo.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.out);
            }
        });
//        btn = (Button) findViewById(R.id.main);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, Register.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.enter, R.anim.out);
//            }
//        });
    }
}
