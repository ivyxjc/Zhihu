package com.jc.zhihu.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.jc.zhihu.R;

import java.util.List;


/**
 * Created by ivyxjc on on 2016/11/25.
 * This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
 */

public abstract class BaseActivity extends AppCompatActivity
                                    implements NavigationView.OnNavigationItemSelectedListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());


        DrawerLayout drawer=(DrawerLayout)findViewById(getDrawerLayouId());
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(getNavigationViewId());
        navigationView.setNavigationItemSelectedListener(this);

        initView();
        initData();
        setView();
    }


    protected abstract void initView();

    protected abstract void setView();

    protected abstract void initData();

    protected abstract int getLayoutId();

    protected abstract int getDrawerLayouId();

    protected abstract int getNavigationViewId();

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(getDrawerLayouId());
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
