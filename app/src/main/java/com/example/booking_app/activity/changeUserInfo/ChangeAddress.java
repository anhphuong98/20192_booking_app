package com.example.booking_app.activity.changeUserInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.booking_app.R;
import com.example.booking_app.connection.APIUtils;
import com.example.booking_app.connection.SOService;
import com.example.booking_app.models.user.DataUser;
import com.example.booking_app.models.user.UpdateUserReponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeAddress extends AppCompatActivity {

    private EditText editAddress;
    private ImageView backAddress;
    private Button updateAddress;
    SOService soService;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_address);
        addData();
        Intent intent = getIntent();
        String address = intent.getStringExtra("addressUser");
        editAddress.setText(address);
        backAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        updateAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = sharedPreferences.getInt("id", -1);
                String addressEdt = editAddress.getText().toString();
                DataUser dataUser = new DataUser();
                dataUser.setId(id);
                dataUser.setAddress(addressEdt);
                String tokenAuth = sharedPreferences.getString("token", "");
                changeUserAddress(id, dataUser, tokenAuth);
            }
        });
    }

    public void addData() {
        sharedPreferences = this.getSharedPreferences("userinfo", MODE_PRIVATE);
        editAddress = (EditText) findViewById(R.id.editAddress);
        backAddress= (ImageView) findViewById(R.id.backAddress);
        updateAddress = (Button) findViewById(R.id.updateAddress);
    }

    public void changeUserAddress(int id, DataUser dataUser, String auth) {
        soService = APIUtils.getSOService();
        Call<UpdateUserReponse> updateUserReponse = soService.updateUser(id, dataUser, auth);
        updateUserReponse.enqueue(new Callback<UpdateUserReponse>() {
            @Override
            public void onResponse(Call<UpdateUserReponse> call, Response<UpdateUserReponse> response) {
                if(response.isSuccessful()){
                    sharedPreferences.edit().putString("address", editAddress.getText().toString()).commit();
                    Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UpdateUserReponse> call, Throwable t) {
                Log.e("Loi update name user", t.getMessage());
            }
        });
    }

}
