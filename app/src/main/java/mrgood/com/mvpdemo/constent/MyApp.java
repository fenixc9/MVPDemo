package mrgood.com.mvpdemo.constent;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

import mrgood.com.mvpdemo.util.CerManager;
import mrgood.com.mvpdemo.util.MyRetrofit;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/1/24 0024.
 */

public class MyApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //添加证书
        CerManager.addCerFile(MyRetrofit.builder,"srca.cer");
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

    }

    public static Context getContext(){
        return context;
    }
}
