package mrgood.com.mvpdemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by lh on 2017/1/29 0029.
 */

public class PullToRefreshRecyclerView extends RecyclerView {


    public final static int TYPE_NORMAL = 0;
    public final static int TYPE_HEADER = 1;//头部--支持头部增加一个headerView
    public final static int TYPE_FOOTER = 2;//底部--往往是loading_more

    public PullToRefreshRecyclerView(Context context) {
        super(context);
        init();
    }

    public PullToRefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullToRefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

    }

}
