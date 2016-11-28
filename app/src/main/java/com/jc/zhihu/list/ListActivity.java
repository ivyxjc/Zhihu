package com.jc.zhihu.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.jc.zhihu.Constant;
import com.jc.zhihu.R;
import com.jc.zhihu.TAG;
import com.jc.zhihu.adapter.RecyclerViewAdapter;
import com.jc.zhihu.adapter.ViewpagerAdapter;
import com.jc.zhihu.model.ListModel;

import java.util.List;

/**
 * Created by ivyxjc on on 2016/11/25.
 * This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
 */

public class ListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView mRecyclerView;
    private List<ListModel> datas;
    private RecyclerViewAdapter mAdapter;
    FragmentManager fm;
    FragmentTransaction mTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar=(Toolbar)findViewById(R.id.list_toolbar);
        setSupportActionBar(toolbar);


        fm=getSupportFragmentManager();
        mTransaction=fm.beginTransaction();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private void replaceFragment(Fragment fragment){
        fm.beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_android) {
            Log.i(TAG.TAG,"android");
            replaceFragment(FragmentList.newSingleton(R.array.develop,R.array.develop_suffix));
        } else if (id == R.id.nav_tech) {
            Log.i(TAG.TAG,"tech");
            replaceFragment(FragmentList.newSingleton(R.array.tech,R.array.tech_suffix));
        } else if (id == R.id.nav_book) {
            Log.i(TAG.TAG,"book");
            replaceFragment(FragmentList.newSingleton(R.array.book,R.array.book_suffix));
        } else if (id == R.id.nav_movie) {
            Log.i(TAG.TAG,"movie");
            replaceFragment(FragmentList.newSingleton(R.array.movie,R.array.movie_suffix));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}