package mrgood.com.mvpdemo.ui.activity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mrgood.com.mvpdemo.R;
import mrgood.com.mvpdemo.presenter.MainPresenter;
import mrgood.com.mvpdemo.view.LoadStateLayout;
import mrgood.com.mvpdemo.viewinterface.MainViewInterface;

public class MainActivity
        //extends Activity
        extends BaseActivity
        implements MainViewInterface {


    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.wb)
    WebView wb;
    @Bind(R.id.lsl)
    LoadStateLayout lsl;
    private MainPresenter presenter;



    @Override
    protected void init(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this);
        initView();
        presenter.httpsTest();
    }

    private void initView() {
        lsl.setEmptyView(R.layout.loadempty);
        lsl.setErrorView(R.layout.loaderror);
        lsl.setLoadingView(R.layout.loadingstate);
        wb.getSettings().setDefaultTextEncodingName("UTF-8");
        wb.setWebViewClient(new WebViewClient());
        wb.setWebChromeClient(new WebChromeClient());
    }


    @Override
    public void updatewebview() {

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
        wb.loadData(object[0].toString(), "text/html; charset=UTF-8", null);
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


    @OnClick(R.id.btn)
    public void onClick() {
        presenter.httpsTest();
    }


}
