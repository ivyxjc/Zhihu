package com.jc.zhihu.list.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jc.zhihu.Constant;
import com.jc.zhihu.list.FragmentList;

/**
 * Created by jc on 11/28/2016.
 */

/**
 * 用于list页面, 即主页面
 */
public abstract class BaseFragment extends Fragment {

    protected View view;

    protected boolean isDataLoaded=false;

//    public static <T extends BaseFragment> T newSingleton(String className, int titleId, int suffixId){
//        Class c;
//        try{
//            c=Class.forName(className);
//        }catch (ClassNotFoundException e){
//            e.printStackTrace();
//            c=BaseFragment.class;
//        }
//        T t;
//        try{
//            t=(T)c.newInstance();
//        }catch (java.lang.InstantiationException e){
//            e.printStackTrace();
//            t=null;
//        }catch (IllegalAccessException e){
//            e.printStackTrace();
//            t=null;
//        }
//        Bundle bundle=new Bundle();
//        bundle.putInt(Constant.LIST_ACTIVITY_NAV_TITLE,titleId);
//        bundle.putInt(Constant.LIST_ACTIVITY_NAV_SUFFIX,suffixId);
//        t.setArguments(bundle);
//        return t;
//    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initImmediateData();
        initLazyData();
        initAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(getLayoutId(),container,false);
        }
        initView();
        if(isDataLoaded){
            setView();
        }
    return view;
}

    /**
     * 数据完全获得后调用, isDataLoaded一旦生成, setView()和initAdapter()方法即可调用,
     */
    protected void dataLoaded(){
        isDataLoaded=true;
    }

    protected abstract void initView();

    /**
     * 在onCreate或其他发生在OnCreateView()之前 即可获得的数据写在该方法中, 该方法内部一般不会调用setView()和initAdapter()
     * 如果initLazyData()没有具体的内容, 则在该方法末尾调用DataLoaded().
     * Adapter所需的数据具体内容可以不在该方法中获取, 但是Adapter所需的相应的变量必须在该方法中
     */
    protected abstract void initImmediateData();

    /**
     * 通过异步方式或其它延迟等方式, 获取到的数据在此方法中. 虽然该方法也会在onCreate()中执行, 但是可能该方法获取到数据时
     * onCreateView()方法已经执行完毕.
     * setView()和initAdapte()可能会在该方法中调用.但是其使用到的变量都应初始化
     */

    protected abstract void initLazyData();


    protected abstract void setView();

    protected abstract int getLayoutId();

    protected abstract void initAdapter();



}
