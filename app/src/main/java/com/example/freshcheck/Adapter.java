package com.example.freshcheck;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class Adapter extends FragmentStateAdapter {
    private Context pcontext;
    int total;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    public Adapter(FragmentManager fm, Lifecycle lifecycle){
        super(fm, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    public void addFragment(Fragment fragment) {
        fragmentList.add(fragment);
    }

    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                WeekFragment week = new WeekFragment();
                return week;

            case 1:
                MonthFragment month = new MonthFragment();
                return month;

            case 2:
                YearFragment year = new YearFragment();
                return year;

            default:
                return null;
        }
    }

    @Override
    public int getItemCount(){
        return fragmentList.size();
    }
}