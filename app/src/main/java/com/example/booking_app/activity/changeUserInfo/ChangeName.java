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
import com.example.booking_app.activity.DetailUserInfo;
import com.example.booking_app.connection.APIUtils;
import com.example.booking_app.connection.SOService;
import com.example.booking_app.models.user.DataUser;
import com.example.booking_app.models.user.UpdateUserReponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeName extends AppCompatActivity {
    private EditText editName;
    private ImageView backName;
    private Button updateName;
    SOService soService;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        addData();
        Intent intent = getIntent();
        String name = intent.getStringExtra("nameUser");
        editName.setText(name);
        backName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        updateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = sharedPreferences.getInt("id", -1);
                String nameEdt = editName.getText().toString();
                DataUser dataUser = new DataUser();
                dataUser.setId(id);
                dataUser.setName(nameEdt);
                String tokenAuth = sharedPreferences.getString("token", "");
                changeUserName(id, dataUser, tokenAuth);
            }
        });
    }

    public void addData() {
        sharedPreferences = this.getSharedPreferences("userinfo", MODE_PRIVATE);
        editName = (EditText) findViewById(R.id.editName);
        backName = (ImageView) findViewById(R.id.backName);
        updateName = (Button) findViewById(R.id.updateName);
    }
    public void changeUserName(int id, DataUser dataUser, String auth) {
        soService = APIUtils.getSOService();
        Call<UpdateUserReponse> updateUserReponse = soService.updateUser(id, dataUser, auth);
        updateUserReponse.enqueue(new Callback<UpdateUserReponse>() {
            @Override
            public void onResponse(Call<UpdateUserReponse> call, Response<UpdateUserReponse> response) {
                if(response.isSuccessful()){
                    sharedPreferences.edit().putString("name", editName.getText().toString()).commit();
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
