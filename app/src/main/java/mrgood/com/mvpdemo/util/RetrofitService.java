package mrgood.com.mvpdemo.util;

import io.reactivex.Observable;
import mrgood.com.mvpdemo.content.UrlContent;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/1/24 0024.
 */

public interface RetrofitService {
    @GET(UrlContent.Test12306.OTN)
    Observable<String> SSLTest();
}
