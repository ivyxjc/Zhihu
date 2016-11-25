package com.jc.zhihu.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.jc.zhihu.Constant;
import com.jc.zhihu.R;

import com.jc.zhihu.TAG;
import com.jc.zhihu.base.BaseActivity;
import com.jc.zhihu.model.DetailModel;
import com.jc.zhihu.utils.ImageLoadUtil;

import java.util.List;

/**
 * Created by jc on 11/24/2016.
 */

public class DetailActivity extends BaseActivity{

    private ImageView mImageView;
    private WebView mWebView;
    private ProgressBar mProgressBar;

    private String mUrl;
    private String mTitleImage;
    private String mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG.DETAIL_ACTIVITY,mUrl);
    }


    @Override
    protected void initData(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        mUrl=bundle.getSerializable(Constant.LIST_DETAIL_DETAIL_URL).toString();
        mTitle=bundle.getSerializable(Constant.LIST_DETAIL_TITLE).toString();
        mTitleImage=bundle.getSerializable(Constant.LIST_DETAIL_TITLE_IMAGE).toString();
        mUrl="https://zhuanlan.zhihu.com"+mUrl;
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
//        mWebView.loadUrl(mUrl);
        mWebView.loadUrl("file:///android_asset/zhihu.html");
        ImageLoadUtil.load(getApplication(),mImageView,mTitleImage);
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected int getDrawerLayouId() {
        return R.id.detail_drawer_layout;
    }

    @Override
    protected int getNavigationViewId() {
        return R.id.detail_nav_view;
    }

}
