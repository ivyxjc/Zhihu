package xyz.ivyxjc.zhihu.detail;

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

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xyz.ivyxjc.zhihu.Constant;
import xyz.ivyxjc.zhihu.R;
import xyz.ivyxjc.zhihu.TAG;
import xyz.ivyxjc.zhihu.detail.base.BaseDetailActiivty;
import xyz.ivyxjc.zhihu.model.DetailModel;
import xyz.ivyxjc.zhihu.network.API;
import xyz.ivyxjc.zhihu.network.HttpMethods;
import xyz.ivyxjc.zhihu.utils.CacheUtil;
import xyz.ivyxjc.zhihu.utils.HtmlUtil;
import xyz.ivyxjc.zhihu.utils.ImageLoadUtil;

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

    private boolean isDataCached;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Toolbar mToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
//        setSupportActionBar(mToolbar);
        initView();
        initImmediateData();
        //setView可以放在此处是因为, setView()的过程中不需要用到initLazyData()获得的数据
        setView();
        if(!isDataCached){
            initLazyData();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isDataCached){
            mWebView.loadDataWithBaseURL(null, HtmlUtil.getHtml(mHtmlContent),HtmlUtil.getMimeType(),HtmlUtil.getCoding(),null);
        }
    }

    @Override
    protected void initImmediateData() {
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        mUrl=bundle.getSerializable(Constant.LIST_DETAIL_DETAIL_URL).toString();
        mTitle=bundle.getSerializable(Constant.LIST_DETAIL_TITLE).toString();
        mTitleImage=bundle.getSerializable(Constant.LIST_DETAIL_TITLE_IMAGE).toString();
        mSlug=Integer.parseInt(bundle.getSerializable(Constant.LIST_DETAIL_SLUG).toString());
        mHtmlContent=(String)CacheUtil.load(getApplication(),CacheUtil.calFilename(mSlug));
        if(mHtmlContent==null){
            Log.i(TAG.DETAIL_ACTIVITY,"mHtmlContent null");
        }else {
            Log.i(TAG.DETAIL_ACTIVITY,mHtmlContent);
        }

        if(mHtmlContent!=null && mHtmlContent.length()!=0){
            isDataCached=true;
        }
    }

    @Override
    protected void initLazyData(){
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
                        CacheUtil.save(getApplication(), CacheUtil.calFilename(mSlug),mHtmlContent);
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

    @Override
    protected boolean isNightMode() {
        return false;
    }
}
