package xyz.ivyxjc.zhihu.list;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;

import java.util.List;

import xyz.ivyxjc.zhihu.Constant;
import xyz.ivyxjc.zhihu.R;
import xyz.ivyxjc.zhihu.TAG;
import xyz.ivyxjc.zhihu.adapter.RecyclerViewAdapter;
import xyz.ivyxjc.zhihu.model.ListModel;
import xyz.ivyxjc.zhihu.utils.NightModeUtil;

//import xyz.ivyxjc.zhihu.TransitionActivity;

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
        if(NightModeUtil.isNigthMode()){
            setTheme(Constant.NIGHT_STYLE);
        }else{
            setTheme(Constant.DAY_STYLE);
        }
        setContentView(R.layout.activity_list);
        Toolbar toolbar=(Toolbar)findViewById(R.id.list_toolbar);
        setSupportActionBar(toolbar);


        fm=getSupportFragmentManager();
        mTransaction=fm.beginTransaction();
        replaceFragment(FragmentTab.newSingleton(R.array.develop,R.array.develop_suffix));

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SwitchCompat item=null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            item = (SwitchCompat) navigationView.getMenu().findItem(R.id.night_mode_swithcbtn).getActionView();
        }

        item.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener(){
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    setTheme(Constant.NIGHT_STYLE);
                    NightModeUtil.setTheme(Constant.NIGHT_STYLE);
                    Intent intent=getIntent();
                    Log.i("aabbaa","ischecked");
//                    recreate();
                }else
                    setTheme(Constant.DAY_STYLE);
                    NightModeUtil.setTheme(Constant.DAY_STYLE);
                    Log.i("aabbaa","notchecked");
//                    recreate();
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.getDefaultNightMode());
                }
//                TransitionActivity.startIntent();
            });
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
            replaceFragment(FragmentTab.newSingleton(R.array.develop,R.array.develop_suffix));
        } else if (id == R.id.nav_tech) {
            Log.i(TAG.TAG,"tech");
            replaceFragment(FragmentTab.newSingleton(R.array.tech,R.array.tech_suffix));
        } else if (id == R.id.nav_book) {
            Log.i(TAG.TAG,"book");
            replaceFragment(FragmentTab.newSingleton(R.array.book,R.array.book_suffix));
        } else if (id == R.id.nav_movie) {
            Log.i(TAG.TAG,"movie");
            replaceFragment(FragmentTab.newSingleton(R.array.movie,R.array.movie_suffix));
        }



        android.support.v4.widget.DrawerLayout drawer = (android.support.v4.widget.DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}