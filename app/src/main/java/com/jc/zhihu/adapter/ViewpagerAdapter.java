package com.jc.zhihu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.jc.zhihu.list.FragmentTab;

/**
 * Created by jc on 11/27/2016.
 */

public class ViewpagerAdapter extends FragmentStatePagerAdapter {

    private String[] mSuffixs;

    public ViewpagerAdapter(FragmentManager fm,String[] suffixs){
        super(fm);
        mSuffixs=suffixs;
    }

    @Override
    public Fragment getItem(int position) {
        String suffix=mSuffixs[position];

        FragmentTab fragment= FragmentTab.newInstance(suffix);
        Log.i(com.jc.zhihu.TAG.TAG,"position");
        return fragment;
    }

    @Override
    public int getCount()    {
        return mSuffixs.length;
    }
}
