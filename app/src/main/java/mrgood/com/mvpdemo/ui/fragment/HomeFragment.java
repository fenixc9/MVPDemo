package mrgood.com.mvpdemo.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mrgood.com.mvpdemo.R;
import mrgood.com.mvpdemo.model.bean.Contentlist;
import mrgood.com.mvpdemo.model.bean.NewsBean;
import mrgood.com.mvpdemo.presenter.HomePresenter;
import mrgood.com.mvpdemo.ui.adapter.NewsAdapter;
import mrgood.com.mvpdemo.view.LoadStateLayout;
import mrgood.com.mvpdemo.viewinterface.HomeViewInterface;

/**
 * Created by lh on 2017/1/28 0028.
 */

public class HomeFragment extends BaseFragment implements HomeViewInterface, RecyclerView.OnItemTouchListener,  NewsAdapter.OnItemClickListener {
    @Bind(R.id.rv)
    RecyclerView rv;
    @Bind(R.id.lsl)
    LoadStateLayout lsl;
    private HomePresenter presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, rootView);
        initView();
        initData();
        return rootView;
    }

    private void initView() {
        lsl.setEmptyView(R.layout.loadempty);
        lsl.setErrorView(R.layout.loaderror);
        lsl.setLoadingView(R.layout.loadingstate);
    }

    private void initData() {
        presenter = new HomePresenter(this);
        presenter.parseNewsData();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void addmore() {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void errorState(Throwable e) {
        lsl.setState(LoadStateLayout.STATE_ERROR);
    }

    @Override
    public void emptyState() {
        lsl.setState(LoadStateLayout.STATE_EMPTY);
    }

    @Override
    public void loadingState() {
        lsl.setState(LoadStateLayout.STATE_LOADING);

    }

    @Override
    public void successState(Object... object) {
        lsl.setState(LoadStateLayout.STATE_SUCCESS);
        NewsBean pageBean = (NewsBean) object[0];
        Log.d("mylog", "successState: bena"+ pageBean.toString());
        List<Contentlist> contentlist = pageBean.getShowapi_res_body().getPagebean().getContentlist();
        NewsAdapter adapter = new NewsAdapter(getActivity(),contentlist);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
        rv.addOnItemTouchListener(this);
        adapter.setItemClickListener(this);
    }

    @Override
    public void toast(String toast) {

    }

    @Override
    public void next() {

    }

    @Override
    public void back() {

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }



    @Override
    public void ItemClick(View view, int positon) {
        Log.d("mylog", "onItemClick: position"+positon);
    }
}
