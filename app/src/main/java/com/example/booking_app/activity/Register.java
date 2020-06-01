package com.example.booking_app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booking_app.R;
import com.example.booking_app.connection.RetrofitClient;
import com.example.booking_app.connection.SOService;
import com.example.booking_app.models.user.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity implements View.OnClickListener {

    ImageView img;
    EditText name, email, password;
    Button signup;
    TextView regis1, regis2;
    SOService mSOService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        mSOService = RetrofitClient.getClient().create(SOService.class);

        signup.setOnClickListener(this);
        regis2.setOnClickListener(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.register:
                register();
                break;
            case R.id.regis2:
                Intent intent1 = new Intent(Register.this, Login.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.enter, R.anim.out);
                break;
        }
    }

    private void register(){
        boolean isnotempty = validate();
        if(isnotempty){
            String mail = email.getText().toString().trim();
            String name = this.name.getText().toString().trim();
            String pass = this.password.getText().toString().trim();

            Call<UserResponse> register = mSOService.register(mail, name, pass);
            register.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    UserResponse userResponse = response.body();

                    if(userResponse.getSuccess()){
                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.enter, R.anim.out);

                        SharedPreferences token = Register.this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = token.edit();
                        editor.putString("token", userResponse.getToken());
                        String id = String.valueOf(userResponse.getData().getId());
                        editor.putString("id", id);
                        editor.commit();

                        finish();
                    }else{
                        Toast.makeText(Register.this, "Please check your network condition", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {

                }
            });
        }
    }

    private void init(){
        img = (ImageView) findViewById(R.id.logo);
        name = (EditText) findViewById(R.id.edtname);
        email= (EditText) findViewById(R.id.edtmail);
        password = (EditText) findViewById(R.id.edtpassregister);
        signup = (Button) findViewById(R.id.register);
        regis1 = (TextView) findViewById(R.id.regis1);
        regis2 = (TextView) findViewById(R.id.regis2);
    }

    private boolean validate(){
        String mail = email.getText().toString().trim();
        String name = this.name.getText().toString().trim();
        String pass = this.password.getText().toString().trim();

        boolean result = false;
        if(mail.isEmpty() || name.isEmpty() || pass.isEmpty()){
            Toast.makeText(Register.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }
}
