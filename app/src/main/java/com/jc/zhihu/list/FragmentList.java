package com.jc.zhihu.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.jc.zhihu.Constant;
import com.jc.zhihu.R;
import com.jc.zhihu.TAG;
import com.jc.zhihu.adapter.RecyclerViewAdapter;
import com.jc.zhihu.detail.DetailActivity;
import com.jc.zhihu.list.base.BaseFragment;
import com.jc.zhihu.model.ListModel;
import com.jc.zhihu.network.API;
import com.jc.zhihu.network.HttpMethods;
import com.jc.zhihu.utils.CacheUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Created by jc on 11/27/2016.
 */

public class FragmentList extends BaseFragment {

    private RecyclerView mRecyclerView;
    private ArrayList<ListModel> datas;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    String suffix;


    private boolean isAllDataLoaded=false;


    public static FragmentList newSingleton(String suffix){
        FragmentList fragment=new FragmentList();
        Bundle bundle=new Bundle();
        bundle.putString(Constant.FRAGMENT_LIST_TAB,suffix);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLazyData(Constant.LIMIT,0,-1);
    }

    @Override
    protected void initView() {
        mRecyclerView=(RecyclerView)view.findViewById(R.id.list_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    @Override
    protected void initImmediateData(){

        Bundle bundle=getArguments();

        if (bundle!=null && !bundle.isEmpty()) {
             suffix= bundle.getString(Constant.FRAGMENT_LIST_TAB);
        }else{
            suffix="daily";
        }
        datas=(ArrayList<ListModel>) CacheUtil.load(getActivity(), CacheUtil.calFilename(suffix));
        if(datas!=null && datas.size()!=0){
            dataLoaded();
        }else{
            datas=new ArrayList<>();
        }
    }


    @Override
    protected void initLazyData(int limit, final int offset,int slug) {
        if(!isDataLoaded){
            if(limit!=-1&&offset!=-1){
                initLazyDataFunc(Constant.LIMIT,0,true);
            }
        }else {
            if (limit != -1 && offset != -1) {
                initLazyDataFunc(limit,offset,false);
            }
        }
    }

    private void initLazyDataFunc(int limit, final int offset, final boolean setView){
        API.ZhihuService zhihuService=(HttpMethods.getRetrofit()).create(API.ZhihuService.class);
        zhihuService.getList(suffix,limit,offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ListModel>>() {
                    @Override
                    public void onCompleted() {
                        // TODO: 11/25/2016
                        Log.i(TAG.FRAGMENT_LIST,"onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO: 2016/11/29
                        Log.i(TAG.FRAGMENT_LIST,e.toString());
                    }
                    @Override
                    public void onNext(List<ListModel> listModels) {
                        dataLoaded();
                        datas.addAll(listModels);
                        CacheUtil.save(getActivity(),CacheUtil.calFilename(suffix),datas);
                        if(setView){
                            setView();
                        }
                        notifyDatasetChanged();
                        if(listModels.size()<Constant.LIMIT){
                            Log.i(TAG.FRAGMENT_LIST,"list size:" +listModels.size());
                            isAllDataLoaded=true;
                        }
                    }
                });
    }

    @Override
    protected void setView() {
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                Log.i(TAG.FRAGMENT_LIST,"datas size: "+datas.size());
//                Log.i(TAG.FRAGMENT_LIST,"lastvisible: "+lastVisibleItem);
//                Log.i(TAG.FRAGMENT_LIST,"recycleItem: "+mRecyclerViewAdapter.getItemCount());
//                Log.i(TAG.FRAGMENT_LIST,"isAllData: "+isAllDataLoaded);
                if (newState == SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 >= mRecyclerViewAdapter.getItemCount()
                        && !isAllDataLoaded){
                    Log.i(TAG.FRAGMENT_LIST,"datas size: "+datas.size());
                    Log.i(TAG.FRAGMENT_LIST,"recycleItem: "+mRecyclerViewAdapter.getItemCount());
                    initLazyData(Constant.LIMIT,datas.size(),-1);
                }
            }
        });
    }




    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_tab;
    }

    @Override
    protected void initAdapter() {
        mRecyclerViewAdapter =new RecyclerViewAdapter(getActivity(),datas);
        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
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
    }

    private void notifyDatasetChanged() {
        notifyDatasetChanged(datas);
    }

    private void notifyDatasetChanged(List<ListModel> list){
        mRecyclerViewAdapter.refresh(list);
    }


}
