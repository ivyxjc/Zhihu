package com.jc.zhihu.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jc.zhihu.R;
import com.jc.zhihu.adapter.RecyclerViewAdapter;
import com.jc.zhihu.base.BaseActivity;
import com.jc.zhihu.model.ListModel;
import com.jc.zhihu.network.API;
import com.jc.zhihu.network.HttpMethods;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ivyxjc on on 2016/11/25.
 * This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
 */

public class ListActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private List<ListModel> datas;

    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerView=(RecyclerView)findViewById(R.id.list_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        initData();

        mAdapter =new RecyclerViewAdapter(this,datas);
        mAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(String url, String title, String titleImage) {
                // TODO: 2016/11/25
            }
        });
        mRecyclerView.setAdapter(mAdapter);


    }

    private void initData(){
        // TODO: 2016/11/25

        //test datas
        datas=new ArrayList<>();

        API.ZhihuService zhihuService=(HttpMethods.getRetrofit()).create(API.ZhihuService.class);

        zhihuService.getList("xitucircle",20,0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ListModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplication(),"Error",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(List<ListModel> listModels) {
                        Toast.makeText(getApplication(),"success",Toast.LENGTH_LONG).show();
                        datas=listModels;
                        notifyDatasetChanged();
                    }
                });


    }

    @Override
    protected void notifyDatasetChanged() {
        notifyDatasetChanged(datas);
    }

    private void notifyDatasetChanged(List<ListModel> list){
        mAdapter.refresh(list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    protected int getDrawerLayouId() {
        return R.id.list_drawer_layout;
    }

    @Override
    protected int getNavigationViewId() {
        return R.id.list_nav_view;
    }

}