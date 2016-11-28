package com.jc.zhihu.list;

import android.app.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jc.zhihu.Constant;
import com.jc.zhihu.R;
import com.jc.zhihu.TAG;
import com.jc.zhihu.adapter.RecyclerViewAdapter;
import com.jc.zhihu.adapter.ViewpagerAdapter;
import com.jc.zhihu.model.ListModel;

import java.util.List;

/**
 * Created by ivyxjc on on 2016/11/28.
 * This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
 */

public class FragmentList extends Fragment {
    private RecyclerView mRecyclerView;
    private List<ListModel> datas;

    private RecyclerViewAdapter mAdapter;

    private int mTitleId;
    private int mSuffixId;
    private String[] mArraysSuffix;
    private String[] mArraysTitle;

    private FragmentActivity mContext;

    public static FragmentList newInstance(int titleId,int suffixId){
        FragmentList fragment=new FragmentList();
        Bundle bundle=new Bundle();
        bundle.putInt(Constant.LIST_ACTIVITY_NAV_TITLE,titleId);
        bundle.putInt(Constant.LIST_ACTIVITY_NAV_SUFFIX,suffixId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        mContext=(FragmentActivity) context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_list,container,false);
        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tab_layout);
        for(String s:mArraysTitle){
            tabLayout.addTab(tabLayout.newTab().setText(s));
        }
        Log.i(TAG.TAG,"tablayout");
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)view.findViewById(R.id.view_pager);
        final ViewpagerAdapter adapter = new ViewpagerAdapter(getChildFragmentManager(), mArraysSuffix);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(4);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i(com.jc.zhihu.TAG.TAG,"onTabSelected");
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }


}
