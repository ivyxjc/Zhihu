package xyz.ivyxjc.zhihu.detail.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import xyz.ivyxjc.zhihu.Constant;
import xyz.ivyxjc.zhihu.utils.NightModeUtil;


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
