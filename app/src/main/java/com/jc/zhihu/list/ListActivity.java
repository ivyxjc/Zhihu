package com.jc.zhihu.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jc.zhihu.Constant;
import com.jc.zhihu.R;
import com.jc.zhihu.adapter.RecyclerViewAdapter;
import com.jc.zhihu.base.BaseListActivity;
import com.jc.zhihu.detail.DetailActivity;
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

public class ListActivity extends BaseListActivity {

    private RecyclerView mRecyclerView;
    private List<ListModel> datas;

    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.status_bar_color));

        getWindow() .getDecorView() .setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        initData();
        setAdapter();

    }



    private void setAdapter(){
        mAdapter =new RecyclerViewAdapter(this,datas);
        mAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(String url, String title, String titleImage) {
                Intent intent=new Intent(getApplication(), DetailActivity.class);
                intent.putExtra(Constant.LIST_DETAIL_TITLE,title);
                intent.putExtra(Constant.LIST_DETAIL_TITLE_IMAGE,titleImage);
                intent.putExtra(Constant.LIST_DETAIL_DETAIL_URL,url);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }



    @Override
    protected void initData(){

        datas=new ArrayList<>();

        API.ZhihuService zhihuService=(HttpMethods.getRetrofit()).create(API.ZhihuService.class);

        zhihuService.getList("xitucircle",20,0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ListModel>>() {
                    @Override
                    public void onCompleted() {
                        // TODO: 11/25/2016
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO: 11/25/2016
                    }

                    @Override
                    public void onNext(List<ListModel> listModels) {
                        datas=listModels;
                        notifyDatasetChanged();
                    }
                });
    }

    @Override
    protected void initView(){
        mRecyclerView=(RecyclerView)findViewById(R.id.list_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void setView(){

    }


    private void notifyDatasetChanged() {
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