package mrgood.com.mvpdemo.presenter;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mrgood.com.mvpdemo.model.imp.MainModelImp;
import mrgood.com.mvpdemo.viewinterface.MainViewInterface;

/**
 * Created by Administrator on 2017/1/24 0024.
 */

public class MainPresenter extends BasePresenter {
    MainModelImp imp;
    MainViewInterface vi;


    public MainPresenter(MainViewInterface vi) {
        this.imp = new MainModelImp();
        this.vi = vi;
    }

    public void httpsTest(){
        imp.httpsTest().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                  //      Log.d("mylog", "onSubscribe: ");
                        vi.loadingState();
                    }

                    @Override
                    public void onNext(String s) {
                    //    Log.d("mylog", "onNext: ");
                        vi.successState(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                   //     Log.d("mylog", "onError:e "+e.toString());
                        vi.emptyState();
                    }

                    @Override
                    public void onComplete() {
                    //    Log.d("mylog", "onComplete: ");
                    }
                });
    }



}
