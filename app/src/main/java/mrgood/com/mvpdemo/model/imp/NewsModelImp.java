package mrgood.com.mvpdemo.model.imp;

import io.reactivex.Observable;
import mrgood.com.mvpdemo.constent.UrlContent;
import mrgood.com.mvpdemo.model.bean.NewsBean;
import mrgood.com.mvpdemo.util.MyRetrofit;

/**
 * Created by lh on 2017/1/29 0029.
 */

public class NewsModelImp  {

    public static Observable<NewsBean> getNews() {
        return MyRetrofit.getSingleton()
                .getHttpService(UrlContent.showapi.HOST)
                .getNews(UrlContent.showapi.appid,UrlContent.showapi.sign);
    }
}
