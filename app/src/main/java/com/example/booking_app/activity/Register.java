package com.example.booking_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.booking_app.R;

import org.w3c.dom.Text;

public class Register extends AppCompatActivity {

    ImageView img;
    EditText name, email, password;
    Button signup;
    TextView regis1, regis2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });
        regis2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        img = (ImageView) findViewById(R.id.logo);
        name = (EditText) findViewById(R.id.edtname);
        email= (EditText) findViewById(R.id.edtmail);
        password = (EditText) findViewById(R.id.edtpass);
        signup = (Button) findViewById(R.id.register);
        regis1 = (TextView) findViewById(R.id.regis1);
        regis2 = (TextView) findViewById(R.id.regis2);
    }
}
