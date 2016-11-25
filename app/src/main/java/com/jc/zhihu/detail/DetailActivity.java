package com.jc.zhihu.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.jc.zhihu.R;
import com.jc.zhihu.base.BaseActivity;
import com.jc.zhihu.model.DetailModel;

import java.util.List;

/**
 * Created by jc on 11/24/2016.
 */

public class DetailActivity extends BaseActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    protected void notifyDatasetChanged() {
        return;
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
