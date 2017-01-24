package mrgood.com.mvpdemo.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import mrgood.com.mvpdemo.util.SystemBarTintManager;

/**
 * Created by Administrator on 2017/1/24 0024.
 */

public abstract class BaseActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("mylog", "onCreate:BaseActivity ");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init(savedInstanceState);
        changeStatusBar();
    }

    protected abstract void init(Bundle savedInstanceState);


    @TargetApi(19)
    public void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winLayoutParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winLayoutParams.flags |= bits;
        } else {
            winLayoutParams.flags &= ~bits;
        }
        win.setAttributes(winLayoutParams);
    }

    public void changeStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(this, true);
        }
        SystemBarTintManager barTintManager = new SystemBarTintManager(this);
        barTintManager.setStatusBarTintEnabled(true);
        barTintManager.setNavigationBarTintColor(Color.TRANSPARENT);
        this.getWindow().getDecorView().setFitsSystemWindows(true);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
