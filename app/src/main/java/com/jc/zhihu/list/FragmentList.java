package com.jc.zhihu.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jc.zhihu.Constant;
import com.jc.zhihu.R;
import com.jc.zhihu.adapter.RecyclerViewAdapter;
import com.jc.zhihu.adapter.ViewpagerAdapter;
import com.jc.zhihu.list.base.BaseFragment;
import com.jc.zhihu.model.ListModel;

import java.util.List;

/**
 * Created by ivyxjc on on 2016/11/28.
 * This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
 */

public class FragmentList extends BaseFragment {

    private int mTitleId;
    private int mSuffixId;
    private String[] mArraysSuffix;
    private String[] mArraysTitle;
    private TabLayout tabLayout;

    private ViewPager mViewPager;
    private ViewpagerAdapter mViewPagerAdapter;


    public static FragmentList newSingleton(int titleId,int suffixId){
        FragmentList fragment=new FragmentList();
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
    protected void initLazyData() {

    }

    @Override
    protected void initView() {
        tabLayout = (TabLayout)view.findViewById(R.id.tab_layout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        mViewPager = (ViewPager)view.findViewById(R.id.view_pager);

    }



    @Override
    protected void setView() {
        initAdapter();
        mViewPager.setAdapter(mViewPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
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
