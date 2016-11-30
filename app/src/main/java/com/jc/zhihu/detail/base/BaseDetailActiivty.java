package com.jc.zhihu.detail.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;

import com.jc.zhihu.Constant;
import com.jc.zhihu.R;
import com.jc.zhihu.utils.NightModeUtil;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

//import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by jc on 11/26/2016.
 */

public abstract class BaseDetailActiivty extends SwipeBackActivity {

    private SwipeBackLayout mSwipeBackLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(NightModeUtil.isNigthMode()){
            setTheme(Constant.NIGHT_STYLE);
        }else{
            setTheme(Constant.DAY_STYLE);
        }
        setContentView(getLayoutId());

        mSwipeBackLayout=getSwipeBackLayout();

    }

    protected abstract boolean isNightMode();

    protected abstract void initView();

    protected abstract void setView();

    protected abstract void initLazyData();

    protected abstract void initImmediateData();

    protected abstract int getLayoutId();



}
