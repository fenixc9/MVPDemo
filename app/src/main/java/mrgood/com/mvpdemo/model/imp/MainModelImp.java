package mrgood.com.mvpdemo.model.imp;

import io.reactivex.Observable;
import mrgood.com.mvpdemo.constent.UrlContent;
import mrgood.com.mvpdemo.model.MainModel;
import mrgood.com.mvpdemo.util.MyRetrofit;

/**
 * Created by Administrator on 2017/1/24 0024.
 */

public class MainModelImp implements MainModel {
    private final MyRetrofit myRetrofit;


    public MainModelImp() {
        myRetrofit = MyRetrofit.getSingleton();
    }
    @Override
    public Observable<String> httpsTest(){
        return myRetrofit.
                getHttpService(UrlContent.Test12306.HOST)
                .SSLTest();
    }
}
