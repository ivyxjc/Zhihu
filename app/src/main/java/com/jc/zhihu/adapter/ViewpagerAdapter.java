package com.jc.zhihu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by jc on 11/27/2016.
 */

public class ViewpagerAdapter extends FragmentStatePagerAdapter {

    private List<String> mSuffixs;

    public ViewpagerAdapter(FragmentManager fm,List<String> suffixs){
        super(fm);
        mSuffixs=suffixs;


    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return mSuffixs.size();
    }
}
