package com.example.booking_app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booking_app.R;
import com.example.booking_app.connection.APIUtils;
import com.example.booking_app.connection.SOService;
import com.example.booking_app.models.user.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener{

    ImageView logo;
    EditText mail, pass;
    TextView txt1, txt2;
    Button login;
    SOService mSOService;
    ProgressBar pg;
    ImageView backLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        mSOService = APIUtils.getSOService();
        login.setOnClickListener(this);

        txt2.setOnClickListener(this);
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void init(){
        logo = (ImageView) findViewById(R.id.logologin);
        mail = (EditText) findViewById(R.id.edtmaillogin);
        pass = (EditText) findViewById(R.id.edtpasslogin);
        txt1 = (TextView) findViewById(R.id.login1);
        txt2 = (TextView) findViewById(R.id.login2);
        login = (Button) findViewById(R.id.login);
        pg = (ProgressBar) findViewById(R.id.loadlogin);
        backLogin = (ImageView) findViewById(R.id.backLogin);
    }


    public void onClick(View v){

        switch (v.getId()){
            case R.id.login:
                boolean isnotempty = validate();
                if(isnotempty) {
                    pg.setVisibility(View.VISIBLE);
                    login();
                }
                break;
            case R.id.login2:
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void login(){
        boolean isnotempty = validate();
        if(isnotempty) {
            String email = mail.getText().toString().trim();
            String password = pass.getText().toString().trim();
            Log.d("login", email + " - "+ password);
            Call<UserResponse> login = mSOService.logIn(email, password);
            login.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    UserResponse userResponse = response.body();
//                    Log.d("loginres", "This is login " + userResponse.toString());
                    if(userResponse.getSuccess()){

//                        Intent intent = new Intent(Login.this, Storedetail.class);
//                        startActivity(intent);

                        Intent receiveintent = Login.this.getIntent();
                        SharedPreferences token = Login.this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = token.edit();
                        editor.putString("token", "Bearer "+ userResponse.getToken());
                        editor.putBoolean("signined", true);
                        editor.putInt("id", userResponse.getData().getId());
                        editor.putString("avatar", userResponse.getData().getUrl());
                        editor.putString("email", userResponse.getData().getEmail());
                        editor.putString("address", userResponse.getData().getAddress());
                        editor.putString("phoneNumber", userResponse.getData().getPhone());
                        editor.putString("password", userResponse.getData().getPassword());
                        editor.putString("name", userResponse.getData().getName());
                        editor.commit();

                        if(receiveintent.getStringExtra("fromPage") != null && receiveintent.getStringExtra("fromPage").equals("confirmorder")){
                            onBackPressed();
                        } else {
                            pg.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(Login.this, HomeActivity.class);
                            startActivity(intent);

                        }

                        finish();
                    } else {
                        pg.setVisibility(View.INVISIBLE);
                        Toast.makeText(Login.this, "Email or password is not valid", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(Login.this, "Please check your network condition", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    private boolean validate(){
        boolean result = false;
        String email = mail.getText().toString();
        String password = pass.getText().toString();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        };
        return result;
    }
}
