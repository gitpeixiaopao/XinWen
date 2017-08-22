package com.example.mywangyixinwen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by peiyan on 2017/8/8.
 */

public class MyAdapter extends FragmentPagerAdapter {
    public String[] str;
    public List<Fragment> fragments;
    public MyAdapter(FragmentManager fm,String[] str,List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
        this.str=str;
    }



    //接收传过来的值
    //返回对应位置的Fragment
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return str[position%str.length];
    }
}

