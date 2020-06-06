package com.example.booking_app.activity.changeUserInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




import com.example.booking_app.R;
import com.example.booking_app.connection.APIUtils;
import com.example.booking_app.connection.SOService;
import com.example.booking_app.models.user.DataUser;
import com.example.booking_app.models.user.UpdateUserReponse;

import org.mindrot.jbcrypt.BCrypt;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {
    EditText password1;
    EditText password2;
    EditText password3;
    Button updatePassword;

    SOService soService;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        addId();
        updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBlankPassword()){
                    Toast.makeText(ChangePassword.this, "Password cannot be blank", Toast.LENGTH_SHORT).show();
                }else if(!checkMatchPassword()){
                    Toast.makeText(ChangePassword.this, "Password is no match", Toast.LENGTH_SHORT).show();
                }else if(!checkTruePassword()){
                    Toast.makeText(ChangePassword.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    int id = sharedPreferences.getInt("id", -1);
                    String passEdt = password3.getText().toString();
                    DataUser dataUser = new DataUser();
                    dataUser.setId(id);
                    dataUser.setPassword(passEdt);
                    String tokenAuth = sharedPreferences.getString("token", "");
                    changePasswordUser(id, dataUser, tokenAuth);
                    String hashPw = BCrypt.hashpw(password3.getText().toString(), BCrypt.gensalt(10));
                    sharedPreferences.edit().putString("password", hashPw).commit();
                }
            }
        });

    }

    public void addId() {
        password1 = (EditText) findViewById(R.id.editPassword1);
        password2 = (EditText) findViewById(R.id.editPassword2);
        password3 = (EditText) findViewById(R.id.editPassword3);
        updatePassword = (Button) findViewById(R.id.updatePassword);
        sharedPreferences = this.getSharedPreferences("userinfo", MODE_PRIVATE);
    }

    public boolean checkBlankPassword() {
        String text1 = password1.getText().toString();
        String text2 = password2.getText().toString();
        String text3 = password3.getText().toString();
        if(text1.equals("") || text2.equals("") || text3.equals("")){
            Toast.makeText(this, "Password cannot be blank", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
    public boolean checkMatchPassword() {
        String text1 = password1.getText().toString();
        String text2 = password2.getText().toString();
        if(text1.equals(text2)){
            return true;
        }
        return false;
    }

    public boolean checkTruePassword() {
        String pwd = sharedPreferences.getString("password", "");
        String text1 = password1.getText().toString();
        return BCrypt.checkpw(text1, pwd);
    }


    public void changePasswordUser(int id, DataUser dataUser, String auth) {
        soService = APIUtils.getSOService();
        Call<UpdateUserReponse> updateUserReponse = soService.updateUser(id, dataUser, auth);
        updateUserReponse.enqueue(new Callback<UpdateUserReponse>() {
            @Override
            public void onResponse(Call<UpdateUserReponse> call, Response<UpdateUserReponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Updated Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateUserReponse> call, Throwable t) {
                Log.e("Loi update user", t.getMessage());
            }
        });
    }
}
