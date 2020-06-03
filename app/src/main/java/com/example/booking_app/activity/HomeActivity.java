package com.example.booking_app.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_app.R;
import com.example.booking_app.adapter.StoreAdapter;
import com.example.booking_app.connection.APIUtils;
import com.example.booking_app.connection.RetrofitClient;
import com.example.booking_app.connection.SOService;
import com.example.booking_app.connection.StoreService;
import com.example.booking_app.models.store.DataStore;
import com.example.booking_app.models.store.StoreResponse;
import com.example.booking_app.models.user.UserResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    StoreService storeService;
    SearchView searchView;
    ViewFlipper viewFlipper;
    LinearLayout linearMenuHome, linearMenuHistoryOrder, linearMenuProfile;
    TextView txtMenuHome,txtMenuHistoryOrder,txtMenuProfile;
    ArrayList<DataStore> listDataStore = new ArrayList<DataStore>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        storeService = APIUtils.getStoreService();
        init();
        ActionViewFlipper();
        getAllStoreHome();
    }



    private void init(){
        searchView = (SearchView) findViewById(R.id.search);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        linearMenuHistoryOrder = (LinearLayout)findViewById(R.id.linearMenuHistoryOrder);
        linearMenuHome = (LinearLayout)findViewById(R.id.linearMenuHome );
        linearMenuProfile = (LinearLayout)findViewById(R.id.linearMenuProfile);
        txtMenuHome = (TextView)findViewById(R.id.txtMenuHome);
        txtMenuHistoryOrder = (TextView)findViewById(R.id.txtMenuHistoryOrder);
        txtMenuProfile = (TextView) findViewById(R.id.txtMenuProfile);
        recyclerView = (RecyclerView) findViewById(R.id.listStore);
    }
    private void ActionViewFlipper(){
        ArrayList<String> advertisements = new ArrayList<>();
        advertisements.add("https://kenh14cdn.com/thumb_w/640/2018/10/4/photo1538615489250-1538615489250208741873.jpg");
        advertisements.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRQwPhEhVwtFFRgEwRsfxqiFh1afcrSY_q7MPAseGKwlvSiR9OC&usqp=CAU");
        advertisements.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS0E-BXHwVjyj3HeEA_ytk0iGJbcVdDXbwaO5TrDVBomMDuN-mp&usqp=CAU");
        advertisements.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTbF_eeJ1mDTjX6dfhdoCcSJpwkeln_Z_j1_mJ29y_jYeyHfjkD&usqp=CAU");
        for (int i = 0; i< advertisements.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());

//            Picasso.with(getApplicationContext()).load(advertisements.get(i)).into(imageView);
            Picasso.get().load(advertisements.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);

        }
        viewFlipper.setFlipInterval(5000);//muốn chạy trong bao lâu
        viewFlipper.setAutoStart(true);

    }

    public void getAllStoreHome() {
        Call<StoreResponse> responseStore = storeService.getAllStore();
        responseStore.enqueue(new Callback<StoreResponse>() {
            @Override
            public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                if(response.isSuccessful()){
                    listDataStore = response.body().getRows();
                    recyclerView.setHasFixedSize(true);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL , false);
                    recyclerView.setLayoutManager(layoutManager);
                    StoreAdapter storeAdapter = new StoreAdapter(getApplicationContext(), listDataStore);
                    recyclerView.setAdapter(storeAdapter);
                }
            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) {
                Log.e("Loi", t.getMessage());
            }
        });
    }

}
