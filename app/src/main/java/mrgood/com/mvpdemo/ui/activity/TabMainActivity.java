package mrgood.com.mvpdemo.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import mrgood.com.mvpdemo.R;
import mrgood.com.mvpdemo.ui.adapter.ViewpagerAdapter;
import mrgood.com.mvpdemo.util.ToastUtil;

/**
 * Created by lh on 2017/1/28 0028.
 */

public class TabMainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        Toolbar.OnMenuItemClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tab)
    TabLayout tab;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.activity_appbar)
    DrawerLayout activityAppbar;
    @Bind(R.id.nav)
    NavigationView nav;

    @Override
    protected void init(Bundle savedInstanceState) {
        setContentView(R.layout.activity_tabmain);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle barDrawerToggle = new ActionBarDrawerToggle(this, activityAppbar, toolbar, R.string.open, R.string.close);
        barDrawerToggle.syncState();
        toolbar.setOnMenuItemClickListener(this);
        nav.setNavigationItemSelectedListener(this);
        viewpager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager()));
        viewpager.setCurrentItem(0);
        tab.setupWithViewPager(viewpager);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        activityAppbar.closeDrawers();
        return false;
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        ToastUtil.showToast(TabMainActivity.this, item.getTitle().toString());
        return false;
    }
}
