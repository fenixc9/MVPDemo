package mrgood.com.mvpdemo.presenter;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mrgood.com.mvpdemo.model.bean.NewsBean;
import mrgood.com.mvpdemo.model.imp.NewsModelImp;
import mrgood.com.mvpdemo.viewinterface.HomeViewInterface;

/**
 * Created by lh on 2017/1/29 0029.
 */

public class HomePresenter extends BasePresenter {
    HomeViewInterface hi;
    NewsModelImp newImp;


    public HomePresenter(HomeViewInterface hi) {
        newImp = new NewsModelImp();
        this.hi = hi;
    }


    public void parseNewsData(){
        newImp.getNews().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("mylog", "onSubscribe: ");
                        hi.loadingState();
                    }

                    @Override
                    public void onNext(NewsBean s) {
                        hi.successState(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("mylog", "onError: e"+e.toString());
                        hi.errorState(e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d("mylog", "onComplete: ");
                    }
                });
    }
}
