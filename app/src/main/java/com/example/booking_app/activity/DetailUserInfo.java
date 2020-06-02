package com.example.booking_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.booking_app.R;

public class DetailUserInfo extends AppCompatActivity {

    private ImageView backUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user_info);

        backUser = (ImageView) findViewById(R.id.backUser);
        backUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(DetailUserInfo.this, MainActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.enter, R.anim.in);
                onBackPressed();
            }
        });


    }

}
