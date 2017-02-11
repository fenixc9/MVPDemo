package mrgood.com.mvpdemo.presenter;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mrgood.com.mvpdemo.model.imp.HttpsTestModelImp;
import mrgood.com.mvpdemo.viewinterface.TestViewInterface;

/**
 * Created by Administrator on 2017/1/24 0024.
 */

public class TestPresenter extends BasePresenter  {
    HttpsTestModelImp imp;
    TestViewInterface vi;


    public TestPresenter(TestViewInterface vi) {
        this.imp = new HttpsTestModelImp();
        this.vi = vi;
    }

    public void httpsTest(){
        imp.httpsTest().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("mylog", "onSubscribe: ");
                        vi.loadingState();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("mylog", "onNext: ");
                        vi.successState(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("mylog", "onError:e "+e.toString());
                        vi.errorState(e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d("mylog", "onComplete: ");
                    }
                });
    }



}
