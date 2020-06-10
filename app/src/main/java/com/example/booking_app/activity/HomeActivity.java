package com.example.booking_app.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.graphics.Color;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booking_app.R;
import com.example.booking_app.fragment.FragmentHome;
import com.example.booking_app.fragment.FragmentUserInfo;
public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SharedPreferences sharedPreferences = this.getSharedPreferences("userinfo", MODE_PRIVATE);
//        if(sharedPreferences.getBoolean("signined", false)){
//            Fragment fragment = new FragmentUserInfo();
//            fragmentTransaction.add(R.id.frameContent, fragment);
//            fragmentTransaction.commit();
//        } else {
//            Fragment fragment = new FragmentHome();
//            fragmentTransaction.add(R.id.frameContent, fragment);
//            fragmentTransaction.commit();
//        }

        Fragment fragment = new FragmentHome();
        fragmentTransaction.add(R.id.frameContent, fragment);
        fragmentTransaction.commit();

    }

    public void AddFragment(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        switch (view.getId()){
            case R.id.linearMenuHome:
                fragment = new FragmentHome();
                break;
            case R.id.linearMenuProfile:
                fragment = new FragmentUserInfo();
                break;
        }
        fragmentTransaction.replace(R.id.frameContent, fragment);
        fragmentTransaction.commit();
    }
}
