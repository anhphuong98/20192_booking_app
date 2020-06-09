package com.example.booking_app.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.booking_app.R;
import com.example.booking_app.activity.DetailUserInfo;
import com.squareup.picasso.Picasso;

import static android.content.Context.MODE_PRIVATE;

public class FragmentUserInfo extends Fragment {
    private ImageView avatarUser;
    private TextView nameUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_user, container, false);
        avatarUser = (ImageView) view.findViewById(R.id.avatarUser);
        nameUser = (TextView) view.findViewById(R.id.nameUser);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userinfo", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String url = sharedPreferences.getString("avatar", "");
        nameUser.setText(name);
        Picasso.with(getActivity()).load(url).into(avatarUser);

        avatarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailUserInfo.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter, R.anim.out);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userinfo", MODE_PRIVATE);
        String u_name = sharedPreferences.getString("name", "");
        nameUser.setText(u_name);
        super.onResume();
    }
}
