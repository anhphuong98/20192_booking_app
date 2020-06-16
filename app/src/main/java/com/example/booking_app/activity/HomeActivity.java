package com.example.booking_app.activity;
import androidx.annotation.ColorInt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.booking_app.R;
import com.example.booking_app.fragment.FragmentHistoryOrder;
import com.example.booking_app.fragment.FragmentHome;
import com.example.booking_app.fragment.FragmentOrder;
import com.example.booking_app.fragment.FragmentUserInfo;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtMenuHome, txtMenuHistoryOrder, txtMenuProfile;
    LinearLayout linearMenuHistoryOrder,linearMenuHome, linearMenuProfile;
    SharedPreferences sharedPreferences;
    boolean check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        setContentView(R.layout.activity_home);
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SharedPreferences sharedPreferences = this.getSharedPreferences("userinfo", MODE_PRIVATE);
        if(sharedPreferences.getBoolean("signined", false)){
            if(check == false) {
                Fragment fragment = new FragmentUserInfo();
                fragmentTransaction.add(R.id.frameContent, fragment);
                fragmentTransaction.commit();
            }else{
                Fragment fragment =  new FragmentOrder();
                fragmentTransaction.add(R.id.frameContent, fragment);
                fragmentTransaction.commit();
                sharedPreferences.edit().putBoolean("check", false).commit();
            }

        } else {
            Fragment fragment = new FragmentHome();
            fragmentTransaction.add(R.id.frameContent, fragment);
            fragmentTransaction.commit();
        }

        findViewById(R.id.linearMenuHome).setOnClickListener(this);
        findViewById(R.id.linearMenuHistoryOrder).setOnClickListener(this);
        findViewById(R.id.linearMenuProfile).setOnClickListener(this);

    }

    public void init() {
        sharedPreferences = this.getSharedPreferences("userinfo", MODE_PRIVATE);
        check = sharedPreferences.getBoolean("check", false);
        linearMenuHistoryOrder = (LinearLayout)findViewById(R.id.linearMenuHistoryOrder);
        linearMenuHome = (LinearLayout)findViewById(R.id.linearMenuHome);
        linearMenuProfile = (LinearLayout)findViewById(R.id.linearMenuProfile);
        txtMenuHome = (TextView)findViewById(R.id.txtMenuHome);
        txtMenuHistoryOrder = (TextView)findViewById(R.id.txtMenuHistoryOrder);
        txtMenuProfile = (TextView)findViewById(R.id.txtMenuProfile);
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.linearMenuHome:

                fragment = new FragmentHome();
                break;
            case R.id.linearMenuProfile:
                fragment = new FragmentUserInfo();
                break;
            case R.id.linearMenuHistoryOrder:
                fragment = new FragmentOrder();
                break;

        }
        fragmentTransaction.replace(R.id.frameContent, fragment);
        fragmentTransaction.commit();
    }

//    public void changeColorMenu(TextView tv){
//        tv.setTextColor(Color.RED);
//        tv.setTypeface(Typeface.DEFAULT_BOLD);
//    }
}
