package com.example.booking_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booking_app.R;

public class Login extends AppCompatActivity implements View.OnClickListener{

    ImageView logo;
    EditText mail, pass;
    TextView txt1, txt2;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        login.setOnClickListener(this);

        txt2.setOnClickListener(this);
    }
    private void init(){
        logo = (ImageView) findViewById(R.id.logologin);
        mail = (EditText) findViewById(R.id.edtmaillogin);
        pass = (EditText) findViewById(R.id.edtpasslogin);
        txt1 = (TextView) findViewById(R.id.login1);
        txt2 = (TextView) findViewById(R.id.login2);
        login = (Button) findViewById(R.id.login);
    }


    public void onClick(View v){
        switch (v.getId()){
            case R.id.login:
                break;
            case R.id.login2:
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private Boolean validate(){
        Boolean result = false;
        String email = mail.getText().toString();
        String password = pass.getText().toString();

        if(email.isEmpty() && password.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        };
        return result;
    }
}
