package com.example.booking_app.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_app.R;
import com.example.booking_app.adapter.StoreAdapter;
import com.example.booking_app.connection.APIUtils;
import com.example.booking_app.connection.StoreService;
import com.example.booking_app.fragment.FragmentHome;
import com.example.booking_app.fragment.FragmentUserInfo;
import com.example.booking_app.models.store.DataStore;
import com.example.booking_app.models.store.StoreResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
