package mrgood.com.mvpdemo.util;

import io.reactivex.Observable;
import mrgood.com.mvpdemo.constent.UrlContent;
import mrgood.com.mvpdemo.model.bean.NewsBean;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/1/24 0024.
 */

public interface RetrofitService {
    @GET(UrlContent.Test12306.OTN)
    Observable<String> SSLTest();

    @GET(UrlContent.showapi.NEWS)
    Observable<NewsBean> getNews(@Query("showapi_appid")String showapi_appid,
                                 @Query("showapi_sign")String showapi_sign);
}
