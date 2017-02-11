package mrgood.com.mvpdemo.ui.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mrgood.com.mvpdemo.R;
import mrgood.com.mvpdemo.view.LoadStateLayout;

/**
 * Created by lh on 2017/1/24 0024.
 * 加载界面处理
 */

public abstract class LoadStateActivity extends BaseActivity {


    private ViewGroup loadstateview;
    public LoadStateLayout lsl;


    @Override
    public void setContentView(int layoutResID) {
        loadstateview = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.loadstate_layout,null);
        View child = LayoutInflater.from(this).inflate(layoutResID,null);
        loadstateview.addView(child);
        super.setContentView(loadstateview);
        lsl = (LoadStateLayout) loadstateview.findViewById(R.id.loadstate);
        lsl.setErrorView(setErrorlayoutRes());
        lsl.setEmptyView(setEmptylayoutRes());
        lsl.setLoadingView(setLoadinglayoutRes());
    }



    public abstract int setErrorlayoutRes();

    public abstract int setEmptylayoutRes();

    public abstract int setLoadinglayoutRes();

}
