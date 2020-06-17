package com.example.booking_app.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_app.R;
import com.example.booking_app.activity.OrderUserDetail;
import com.example.booking_app.adapter.OrderAdapter;
import com.example.booking_app.connection.APIUtils;
import com.example.booking_app.connection.OrderService;
import com.example.booking_app.models.order.DataOrder;
import com.example.booking_app.models.order.DataOrderResponse;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCurrentOrder extends Fragment {
    RecyclerView recyclerView;
    public FragmentCurrentOrder() {
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OrderService orderService = APIUtils.getOrderService();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_currentorder, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.listCurrentOrder);
        getCurrentOrder();
        return view;
    }

    public void getCurrentOrder(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token","");
        int idUser = sharedPreferences.getInt("id",-1);
        OrderService orderService = APIUtils.getOrderService();
        orderService.getCurrentOrder(token, idUser).enqueue(new Callback<DataOrderResponse>() {
            @Override
            public void onResponse(Call<DataOrderResponse> call, Response<DataOrderResponse> response) {
                DataOrderResponse dataOrderResponse = response.body();
                if(dataOrderResponse != null){
                    if (dataOrderResponse.isSuccess()){
                        final ArrayList<DataOrder> listOrder = dataOrderResponse.getData();
                        recyclerView.setHasFixedSize(true);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(layoutManager);
                        OrderAdapter orderAdapter = new OrderAdapter(getActivity(),listOrder);
                        recyclerView.setAdapter(orderAdapter);
                        orderAdapter.setOrderListener(new OrderAdapter.OnOrderListener() {
                            @Override
                            public void onOrderClick(int position) {
                                Intent intent = new Intent(getActivity(), OrderUserDetail.class);
                                intent.putExtra("OrderDetail", (Serializable) listOrder.get(position));
                                startActivity(intent);

                            }


                        });
                    }
                    else {
                        Toast.makeText(getActivity(),"Chưa có đơn hàng nào!",Toast.LENGTH_SHORT).show();

                    }
                }
                else{
                    Toast.makeText(getActivity(),"Chưa có đơn hàng nào!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataOrderResponse> call, Throwable t) {
                Log.e("Loi", t.getMessage());
            }
        });
    }
}
