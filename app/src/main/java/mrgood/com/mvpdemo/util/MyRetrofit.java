package mrgood.com.mvpdemo.util;

import android.content.Context;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import mrgood.com.mvpdemo.BuildConfig;
import mrgood.com.mvpdemo.constent.Config;
import mrgood.com.mvpdemo.constent.MyApp;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by lh on 2017/1/24 0024.
 *  网络处理类
 */

public class MyRetrofit {
    private volatile static MyRetrofit myRetrofit;
    public static OkHttpClient.Builder builder = new OkHttpClient.Builder();
    public static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    private static final long CACHE_SIZE =  1024 * 1024 * 100;

    public static MyRetrofit getSingleton() {
        if (myRetrofit == null) {
            synchronized (MyRetrofit.class) {
                if (myRetrofit == null) {
                    myRetrofit = new MyRetrofit();
                }
            }
        }
        return myRetrofit;
    }

    private MyRetrofit() {}

    private static void initOkHttp() {
        File cacheFile = new File(MyApp.getContext().getCacheDir(),
                "HttpCache"); // 指定缓存路径
        Cache cache = new Cache(cacheFile, CACHE_SIZE);
        builder.cache(cache);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //请求拦截
                Request request = chain.request();
                if (!NetCheckUtil.isConnected(MyApp.getContext())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE).build();
                    Log.d("mylog", "intercept: no network");
                }
                //响应拦截
                Response originalResponse = chain.proceed(request);
                if (NetCheckUtil.isConnected(MyApp.getContext())) {
                    String cacheControl = request.cacheControl().toString();
                    return originalResponse.newBuilder()
                            .header("Cache-Control", cacheControl)
                            .removeHeader("Pragma").build();
                } else {
                    return originalResponse.newBuilder().header("Cache-Control",
                            "public, only-if-cached," + CACHE_STALE_SEC)
                            .removeHeader("Pragma").build();
                }
            }
        };

        //应用拦截
        builder.addInterceptor(cacheInterceptor);
        //网络拦截
        builder.addNetworkInterceptor(cacheInterceptor);


        //自定义日志策略release版不拦截
        if (BuildConfig.DEBUG) {
            Interceptor LoggingInterceptor = new Interceptor() {

                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Log.i("RETROFIT", "requestheaders:" + request.headers());
                    Log.i("RETROFIT", "request:" + request.method() + "-" + request.url().toString());
                    Log.i("RETROFIT", "-----------------------------------------------------------------------");
                    Response response = chain.proceed(chain.request());
                    Log.i("RETROFIT", "responseCode:" + response.code() + "");
                    Log.i("RETROFIT", "response:" + response.body().string());
                    return chain.proceed(request);
                }
            };
            builder.addInterceptor(LoggingInterceptor);
        }

    }

    /**
     * 网络请求
     * @param url
     * @return call、observable或flowable对象
     */
    public RetrofitService getHttpService(String url) {
        initOkHttp();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();
        return retrofit.create(RetrofitService.class);
    }


}
