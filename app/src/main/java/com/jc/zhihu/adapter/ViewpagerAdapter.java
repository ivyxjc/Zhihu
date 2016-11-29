package com.jc.zhihu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.jc.zhihu.list.FragmentList;

/**
 * Created by jc on 11/27/2016.
 */

public class ViewpagerAdapter extends FragmentStatePagerAdapter {

    private String[] mSuffixs;
    private String[] mTitles;

    public ViewpagerAdapter(FragmentManager fm,String [] titles,String[] suffixs){
        super(fm);
        mSuffixs=suffixs;
        mTitles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        String suffix=mSuffixs[position];

        FragmentList fragment= FragmentList.newSingleton(suffix);
        Log.i(com.jc.zhihu.TAG.TAG,"position");
        return fragment;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getCount()    {
        return mSuffixs.length;
    }
}
