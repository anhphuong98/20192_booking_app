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

public class ChangePhone extends AppCompatActivity {

    private EditText editPhone;
    private ImageView backPhone;
    private Button updatePhone;
    SOService soService;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);
        addData();
        Intent intent = getIntent();
        String phoneNumber = intent.getStringExtra("phoneUser");
        editPhone.setText(phoneNumber);
        backPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        updatePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = sharedPreferences.getInt("id", -1);
                String phoneEdt = editPhone.getText().toString();
                DataUser dataUser = new DataUser();
                dataUser.setId(id);
                dataUser.setPhone(phoneEdt);
                String tokenAuth = sharedPreferences.getString("token", "");
                changeUserAddress(id, dataUser, tokenAuth);
            }
        });
    }

    public void addData() {
        sharedPreferences = this.getSharedPreferences("userinfo", MODE_PRIVATE);
        editPhone = (EditText) findViewById(R.id.editPhone);
        backPhone= (ImageView) findViewById(R.id.backPhone);
        updatePhone = (Button) findViewById(R.id.updatePhone);
    }

    public void changeUserAddress(int id, DataUser dataUser, String auth) {
        soService = APIUtils.getSOService();
        Call<UpdateUserReponse> updateUserReponse = soService.updateUser(id, dataUser, auth);
        updateUserReponse.enqueue(new Callback<UpdateUserReponse>() {
            @Override
            public void onResponse(Call<UpdateUserReponse> call, Response<UpdateUserReponse> response) {
                if(response.isSuccessful()){
                    sharedPreferences.edit().putString("phoneNumber", editPhone.getText().toString()).commit();
                    Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UpdateUserReponse> call, Throwable t) {
                Log.e("Loi update user", t.getMessage());
            }
        });
    }
}
