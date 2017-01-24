package mrgood.com.mvpdemo.content;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by Administrator on 2017/1/24 0024.
 */

public class MyApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
