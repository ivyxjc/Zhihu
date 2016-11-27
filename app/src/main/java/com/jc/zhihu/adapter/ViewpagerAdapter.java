package com.jc.zhihu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;

import com.jc.zhihu.list.FragmentList;

import java.util.List;

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

        FragmentList fragment=FragmentList.newInstance(suffix);

        return fragment;
    }

    @Override
    public int getCount() {
        return mSuffixs.length;
    }
}
