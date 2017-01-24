package mrgood.com.mvpdemo.model;


import io.reactivex.Observable;
import mrgood.com.mvpdemo.util.RetrofitService;

/**
 * Created by Administrator on 2017/1/24 0024.
 */

public interface MainModel {
    Observable<String> httpsTest();

}
