package com.jc.zhihu.list;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.jc.zhihu.Constant;
import com.jc.zhihu.R;
import com.jc.zhihu.adapter.ViewpagerAdapter;
import com.jc.zhihu.list.base.BaseFragment;

/**
 * Created by ivyxjc on on 2016/11/28.
 * This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
 */

public class FragmentTab extends BaseFragment {

    private int mTitleId;
    private int mSuffixId;
    private String[] mArraysSuffix;
    private String[] mArraysTitle;
    private TabLayout tabLayout;

    private ViewPager mViewPager;
    private ViewpagerAdapter mViewPagerAdapter;


    public static FragmentTab newSingleton(int titleId, int suffixId){
        FragmentTab fragment=new FragmentTab();
        Bundle bundle=new Bundle();
        bundle.putInt(Constant.LIST_ACTIVITY_NAV_TITLE,titleId);
        bundle.putInt(Constant.LIST_ACTIVITY_NAV_SUFFIX,suffixId);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    protected void initImmediateData() {
        Bundle bundle=getArguments();
        if(bundle!=null && !bundle.isEmpty()){
            mTitleId=bundle.getInt(Constant.LIST_ACTIVITY_NAV_TITLE);
            mSuffixId=bundle.getInt(Constant.LIST_ACTIVITY_NAV_SUFFIX);
        }else{
            mTitleId=R.array.develop;
            mSuffixId=R.array.develop_suffix;
        }

        mArraysSuffix=getResources().getStringArray(mSuffixId);
        mArraysTitle=getResources().getStringArray(mTitleId);
        dataLoaded();
    }

    @Override
    protected void initLazyData(int limit, int offset, int slug) {

    }

    @Override
    protected void initView() {
        tabLayout = (TabLayout)view.findViewById(R.id.tab_layout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        mViewPager = (ViewPager)view.findViewById(R.id.view_pager);
        mViewPager.setOffscreenPageLimit(10);

    }


    @Override
    protected void setView() {
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initAdapter() {
        mViewPagerAdapter = new ViewpagerAdapter(getChildFragmentManager(),mArraysTitle, mArraysSuffix);
    }

}
