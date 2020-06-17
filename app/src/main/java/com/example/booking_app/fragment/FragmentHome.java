package com.example.booking_app.fragment;

//import android.app.Fragment;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.booking_app.activity.Login;
import com.example.booking_app.activity.Storedetail;
import com.example.booking_app.adapter.StoreAdapter;
import com.example.booking_app.connection.APIUtils;
import com.example.booking_app.connection.StoreService;
import com.example.booking_app.models.store.DataStore;
import com.example.booking_app.models.store.StoreResponse;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_app.R;


public class FragmentHome extends Fragment {
    RecyclerView recyclerView;
    StoreService storeService;
    SearchView searchView;
    ViewFlipper viewFlipper;
    StoreAdapter storeAdapter;
    ArrayList<DataStore> listDataStore = new ArrayList<DataStore>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storeService = APIUtils.getStoreService();
    }
    public void ActionViewFlipper(){
        ArrayList<Integer> advertisements = new ArrayList<>();
        advertisements.add(R.drawable.trangchu1);
        advertisements.add(R.drawable.trangchu2);
        advertisements.add(R.drawable.trangchu3);
        advertisements.add(R.drawable.trangchu4);
        for (int i = 0; i< advertisements.size(); i++) {
            ImageView imageView = new ImageView(getActivity());

//            Picasso.with(getApplicationContext()).load(advertisements.get(i)).into(imageView);
            Picasso.with(getActivity()).load(advertisements.get(i)).into(imageView);
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
                if (response.isSuccessful()) {
                    final ArrayList<DataStore> listStore = response.body().getRows();
                    recyclerView.setHasFixedSize(true);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layoutManager);
                    storeAdapter = new StoreAdapter(getActivity(), listStore);
                    recyclerView.setAdapter(storeAdapter);
                    storeAdapter.setOnStoreListener(new StoreAdapter.OnStoreListener() {
                        @Override
                        public void onStoreClick(int position) {
                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("storeID", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("store_id", listStore.get(position).getId());
                            editor.commit();


                            Intent intent = new Intent(getActivity(), Storedetail.class);
                            intent.putExtra("StoreDetail", (Serializable) listStore.get(position));
                            intent.putExtra("urlImageStore", (Serializable) listStore.get(position).getUrlImage());
//                            Toast.makeText(HomeActivity.this,position+"===",Toast.LENGTH_SHORT).show();
                            startActivity(intent);

                        }


                    });
                }


            }
            @Override
            public void onFailure (Call < StoreResponse > call, Throwable t){
                Log.e("Loi", t.getMessage());
            }

        });
    }
    public boolean searchStore(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                storeAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;

    }
            @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        searchView = (SearchView) view.findViewById(R.id.search);
        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewflipper);
        recyclerView = (RecyclerView) view.findViewById(R.id.listStore);
        ActionViewFlipper();
        getAllStoreHome();
        searchStore();
        return view;
    }
}





