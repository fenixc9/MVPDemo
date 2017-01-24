package mrgood.com.mvpdemo.util;

import android.content.Context;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import mrgood.com.mvpdemo.BuildConfig;
import mrgood.com.mvpdemo.content.Config;
import mrgood.com.mvpdemo.content.MyApp;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by lh on 2017/1/24 0024.
 */

public class MyRetrofit {
    private volatile static MyRetrofit myRetrofit;
    private Retrofit retrofit;
    private RetrofitService retrofitService;
    private OkHttpClient.Builder builder;


    public static MyRetrofit getSingleton() {
        if (myRetrofit == null) {
            synchronized (MyRetrofit.class) {
                if (myRetrofit == null) {
                    myRetrofit = new MyRetrofit(MyApp.getContext());
                }
            }
        }
        return myRetrofit;
    }

    /**
     * 构造方法中初始化okhttp
     */
    private MyRetrofit(Context Context){
        builder = new OkHttpClient.Builder();
        //缓存拦截器默认无缓存
        builder.addInterceptor(cacheIntercepter);
        //日志拦截器release下不添加日志
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(LoggingInterceptor);
        }
        //配置config开启https
        if (Config.SSL){
            setCertificates(Context);
        }
    };

    public RetrofitService getHttpService(String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();
        return retrofit.create(RetrofitService.class);
    }

    /**
     * 自定义缓存策略
     */
    private Interceptor cacheIntercepter = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request newrequest = request.newBuilder()
                    .addHeader("Cache-Control", "no-cache")
                    .method(request.method(), request.body())
                    .build();

            return chain.proceed(newrequest);
        }
    };

    //自定义日志策略
    private Interceptor LoggingInterceptor = new Interceptor() {

        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {

            Request request = chain.request();
            Log.i("RETROFIT", "request:" + request.method() + " " + request.url().toString());

            okhttp3.Response response = chain.proceed(chain.request());

            Log.i("RETROFIT", "responseCode:" + response.code() + "");
            Log.i("RETROFIT", "response:" + response.body().string());

            return chain.proceed(request);
        }
    };


    /**
     * https证书存放在asset中
     * @param context
     */
    private void setCertificates(Context context) {

        final InputStream inputStream;
        try {
            inputStream = context.getAssets().open("srca.cer"); // 得到证书的输入流
            try {

                X509TrustManager trustManager = trustManagerForCertificates(inputStream);//以流的方式读入证书
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, new TrustManager[]{trustManager}, null);
                SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
                builder.sslSocketFactory(sslSocketFactory, trustManager);
            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 导入证书
     *
     * @param in
     * @return
     * @throws GeneralSecurityException
     */
    private X509TrustManager trustManagerForCertificates(InputStream in)
            throws GeneralSecurityException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Collection<? extends java.security.cert.Certificate> certificates = certificateFactory.generateCertificates(in);
        if (certificates.isEmpty()) {
            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
        }

        // Put the certificates a key store.
        char[] password = "password".toCharArray(); // Any password will work.
        KeyStore keyStore = newEmptyKeyStore(password);
        int index = 0;
        for (java.security.cert.Certificate certificate : certificates) {
            String certificateAlias = Integer.toString(index++);
            keyStore.setCertificateEntry(certificateAlias, certificate);
        }

        // Use it to build an X509 trust manager.
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
                KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:"
                    + Arrays.toString(trustManagers));
        }
        return (X509TrustManager) trustManagers[0];
    }

    /**
     * 生成空key
     *
     * @param password
     * @return
     * @throws GeneralSecurityException
     */
    private KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType()); // 这里添加自定义的密码，默认
            InputStream in = null; // By convention, 'null' creates an empty key store.
            keyStore.load(in, password);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
