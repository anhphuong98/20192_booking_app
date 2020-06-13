package com.example.booking_app.activity;


//import android.app.Fragment;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;


import com.example.booking_app.R;
import com.example.booking_app.fragment.FragmentHome;
import com.example.booking_app.fragment.FragmentOrder;
import com.example.booking_app.fragment.FragmentUserInfo;


public class HomeActivity extends AppCompatActivity {
//    TextView  txtMenuHome, txtMenuHistoryOrder, txtMenuProfile;
//    LinearLayout  linearMenuHistoryOrder,linearMenuHome, linearMenuProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        init();
//        changeColorMenu(txtMenuHome);

//        linearMenuHistoryOrder.setOnClickListener((View.OnClickListener) this);
//        linearMenuHome.setOnClickListener((View.OnClickListener) this);
//        linearMenuProfile.setOnClickListener((View.OnClickListener) this);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        setContentView(R.layout.activity_home);
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SharedPreferences sharedPreferences = this.getSharedPreferences("userinfo", MODE_PRIVATE);
        if(sharedPreferences.getBoolean("signined", false)){
            Fragment fragment = new FragmentUserInfo();
            fragmentTransaction.add(R.id.frameContent, fragment);
            fragmentTransaction.commit();
        } else {
            Fragment fragment = new FragmentHome();
            fragmentTransaction.add(R.id.frameContent, fragment);
            fragmentTransaction.commit();
        }

//        Fragment fragment = new FragmentHome();
//        fragmentTransaction.add(R.id.frameContent, fragment);
//        fragmentTransaction.commit();


    }

//    private void init() {
//        linearMenuHistoryOrder = (LinearLayout)findViewById(R.id.linearMenuHistoryOrder);
//        linearMenuHome = (LinearLayout)findViewById(R.id. linearMenuHome);
//        linearMenuProfile = (LinearLayout)findViewById(R.id.linearMenuProfile);
//        txtMenuHome = (TextView)findViewById(R.id.txtMenuHome);
//        txtMenuHistoryOrder = (TextView)findViewById(R.id.txtMenuHistoryOrder);
//        txtMenuProfile = (TextView)findViewById(R.id.txtMenuProfile);
//    }

    public void AddFragment(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        switch (view.getId()){
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
