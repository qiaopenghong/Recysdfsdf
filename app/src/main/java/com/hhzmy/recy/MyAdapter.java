package com.hhzmy.recy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by qiao on 2016/11/11.
 */
public class MyAdapter extends FragmentPagerAdapter{
    private String[] title;

    public MyAdapter(FragmentManager fm, String[] title) {
        super(fm);
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        MyFragment mf=new MyFragment();
        Bundle b=new Bundle();
        b.putString("title",title[position]);
        mf.setArguments(b);
        return mf;
    }

    @Override
    public int getCount() {
        return title.length;
    }
}
