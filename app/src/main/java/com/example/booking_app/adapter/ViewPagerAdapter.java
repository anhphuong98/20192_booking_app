package com.example.booking_app.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import  androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> listFragment = new ArrayList<>();
    private final ArrayList<String> listFragmetTitles = new ArrayList<>();


    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragmetTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listFragmetTitles.get(position);
    }
    public void addFragment(Fragment fragment, String title){
        listFragment.add(fragment);
        listFragmetTitles.add(title);

    }
}
