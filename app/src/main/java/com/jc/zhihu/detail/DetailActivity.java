package com.jc.zhihu.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.jc.zhihu.Constant;
import com.jc.zhihu.R;

import com.jc.zhihu.TAG;
import com.jc.zhihu.detail.base.BaseDetailActiivty;
import com.jc.zhihu.model.DetailModel;
import com.jc.zhihu.network.API;
import com.jc.zhihu.network.HttpMethods;
import com.jc.zhihu.utils.HtmlUtil;
import com.jc.zhihu.utils.ImageLoadUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jc on 11/24/2016.
 */

public class DetailActivity extends BaseDetailActiivty {

    private ImageView mImageView;
    private WebView mWebView;
    private ProgressBar mProgressBar;


    private int mSlug;
    private String mUrl;
    private String mTitleImage;
    private String mTitle;
    private String mHtmlContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Toolbar mToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(mToolbar);

        initView();
        initData();
        setView();
    }


    @Override
    protected void initData(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        mUrl=bundle.getSerializable(Constant.LIST_DETAIL_DETAIL_URL).toString();
        mTitle=bundle.getSerializable(Constant.LIST_DETAIL_TITLE).toString();
        mTitleImage=bundle.getSerializable(Constant.LIST_DETAIL_TITLE_IMAGE).toString();
        mSlug=Integer.parseInt(bundle.getSerializable(Constant.LIST_DETAIL_SLUG).toString());

        API.ZhihuService zhihuService=(HttpMethods.getRetrofit()).create(API.ZhihuService.class);
        zhihuService.getDetail(mSlug)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DetailModel>() {
                    @Override
                    public void onCompleted() {
                        //todo
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO: 11/26/2016
                    }

                    @Override
                    public void onNext(DetailModel detailModel) {
                        mHtmlContent=detailModel.getContent();
                        Log.i(TAG.DETAIL_ACTIVITY,"mslug: "+mSlug);
                        Log.i(TAG.DETAIL_ACTIVITY,"conent: "+detailModel.getContent());
                        mWebView.loadDataWithBaseURL(null, HtmlUtil.getHtml(mHtmlContent),HtmlUtil.getMimeType(),HtmlUtil.getCoding(),null);
                    }
                });

    }

    @Override
    protected void initView(){
        mImageView=(ImageView)findViewById(R.id.detail_image);
        mProgressBar=(ProgressBar)findViewById(R.id.detail_progress_bar);
        mWebView=(WebView)findViewById(R.id.detail_webview);
        initWebView();
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress==100){
                    progressCompleted();
                }else{
                    progressUnCompleted(newProgress);
                }
            }
            private void progressCompleted(){
                mProgressBar.setProgress(100);
                mProgressBar.setVisibility(View.INVISIBLE);
            }
            private void progressUnCompleted(int newProgress){
                mProgressBar.setProgress(newProgress);
            }
        });
    }

    private void initWebView(){
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(false);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(false);
    }

    @Override
    protected void setView() {

        ImageLoadUtil.load(getApplication(),mImageView,mTitleImage);
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }




}
