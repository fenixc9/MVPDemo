package mrgood.com.mvpdemo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import mrgood.com.mvpdemo.ui.fragment.HomeFragment;
import mrgood.com.mvpdemo.ui.fragment.GameFragment;

public class ViewpagerAdapter extends FragmentStatePagerAdapter {
    String[] titles = new String[]{"首页","娱乐"};
    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return new HomeFragment();
        }else{
            return new GameFragment();
        }
    }


    public ViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];
    }

}