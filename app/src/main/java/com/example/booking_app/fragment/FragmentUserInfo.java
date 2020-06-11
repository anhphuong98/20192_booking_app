package com.example.booking_app.fragment;

//import android.app.Fragment;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.booking_app.R;
import com.example.booking_app.activity.DetailUserInfo;
import com.example.booking_app.activity.HomeActivity;
import com.example.booking_app.activity.Login;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import static android.content.Context.MODE_PRIVATE;

public class FragmentUserInfo extends Fragment {
    private ImageView avatarUser;
    private TextView nameUser;
    private Button btnLogin;
    LinearLayout linearLogout;
    BottomSheetDialog bottomSheetDialog;
    BottomSheetDialog logoutSheetDialog;
    View view;
    SharedPreferences sharedPreferences;
    TextView cancelLogout;
    TextView exceptLogout;





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottomSheetDialog = new BottomSheetDialog(getActivity());
        logoutSheetDialog = new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(R.layout.confirm_logout);
        logoutSheetDialog.setContentView(R.layout.loading_logout);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_user, container, false);
        init();
        if(sharedPreferences.getBoolean("signined", false)) {
            processLogined();
        } else {
            processLogouted();
        }
        return view;
    }

    public void init() {
        sharedPreferences = getActivity().getSharedPreferences("userinfo", MODE_PRIVATE);
        avatarUser = (ImageView) view.findViewById(R.id.avatarUser);
        nameUser = (TextView) view.findViewById(R.id.nameUser);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        linearLogout = (LinearLayout) view.findViewById(R.id.layoutLogout);
    }

    public void processLogined() {
        String name = sharedPreferences.getString("name", "");
        String url = sharedPreferences.getString("avatar", "https://image.flaticon.com/icons/png/512/149/149071.png");
        nameUser.setText(name);
        btnLogin.setVisibility(View.GONE);
        nameUser.setVisibility(View.VISIBLE);
        linearLogout.setVisibility(View.VISIBLE);

        Picasso.with(getActivity()).load(url).into(avatarUser);
        avatarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailUserInfo.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter, R.anim.out);
            }
        });
        linearLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
                cancelLogout = (TextView) bottomSheetDialog.findViewById(R.id.cancelLogout);
                exceptLogout = (TextView) bottomSheetDialog.findViewById(R.id.exceptLogout);
                if(cancelLogout != null) {
                    cancelLogout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomSheetDialog.dismiss();
                        }
                    });
                }
                if(exceptLogout != null) {
                    exceptLogout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sharedPreferences.edit().clear().commit();
                            bottomSheetDialog.dismiss();

                            CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    logoutSheetDialog.show();
                                }

                                @Override
                                public void onFinish() {
                                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                                    startActivity(intent);
                                }
                            };
                            countDownTimer.start();

                        }
                    });
                }
            }
        });
    }

    public void processLogouted() {
        avatarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter, R.anim.out);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter, R.anim.out);
            }
        });
    }

    @Override
    public void onResume() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userinfo", MODE_PRIVATE);
        String u_name = sharedPreferences.getString("name", "");
        nameUser.setText(u_name);
        super.onResume();
    }
}
