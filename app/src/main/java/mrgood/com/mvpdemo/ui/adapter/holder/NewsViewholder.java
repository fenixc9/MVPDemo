package mrgood.com.mvpdemo.ui.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mrgood.com.mvpdemo.R;

/**
 * Created by lh on 2017/1/29 0029.
 */

public class NewsViewholder extends RecyclerView.ViewHolder {
    public TextView time;
    public TextView title;
    public TextView subtitle;

    public NewsViewholder(View itemView) {
        super(itemView);
        time = (TextView) itemView.findViewById(R.id.tv_time);
        title = (TextView) itemView.findViewById(R.id.title);
        subtitle = (TextView) itemView.findViewById(R.id.subtitle);

    }
}
