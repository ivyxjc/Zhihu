package com.jc.zhihu.list;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jc.zhihu.Constant;
import com.jc.zhihu.R;
import com.jc.zhihu.adapter.RecyclerViewAdapter;
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
 * Created by jc on 11/27/2016.
 */

public class FragmentList extends Fragment {

    private RecyclerView mRecyclerView;
    private View mView;
    private List<ListModel> datas;
    private RecyclerViewAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list,container,false);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.list_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mView=view;
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData("xitucircle");
        setAdapter();
    }


    private void setAdapter(){
        mAdapter =new RecyclerViewAdapter(getActivity(),datas);
        mAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(String url, String title, String titleImage, int slug) {
                Intent intent=new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(Constant.LIST_DETAIL_TITLE,title);
                intent.putExtra(Constant.LIST_DETAIL_TITLE_IMAGE,titleImage);
                intent.putExtra(Constant.LIST_DETAIL_DETAIL_URL,url);
                intent.putExtra(Constant.LIST_DETAIL_SLUG,slug);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }


    protected void initData(String suffix){

        datas=new ArrayList<>();

        API.ZhihuService zhihuService=(HttpMethods.getRetrofit()).create(API.ZhihuService.class);

        zhihuService.getList(suffix,20,0)
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



    private void notifyDatasetChanged() {
        notifyDatasetChanged(datas);
    }

    private void notifyDatasetChanged(List<ListModel> list){
        mAdapter.refresh(list);
    }




}
