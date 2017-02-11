package mrgood.com.mvpdemo.ui.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mrgood.com.mvpdemo.R;
import mrgood.com.mvpdemo.presenter.TestPresenter;
import mrgood.com.mvpdemo.view.LoadStateLayout;
import mrgood.com.mvpdemo.viewinterface.TestViewInterface;

public class TestActivity
        //extends Activity
        extends LoadStateActivity
        implements TestViewInterface {
//

    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.wb)
    WebView wb;

    private TestPresenter presenter;



    @Override
    protected void init(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new TestPresenter(this);
        presenter.httpsTest();
    }

    @Override
    public int setErrorlayoutRes() {
        return R.layout.loaderror;
    }

    @Override
    public int setEmptylayoutRes() {
        return R.layout.loadempty;
    }

    @Override
    public int setLoadinglayoutRes() {
        return R.layout.loadingstate;
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
