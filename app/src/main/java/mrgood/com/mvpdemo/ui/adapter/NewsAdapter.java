package mrgood.com.mvpdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mrgood.com.mvpdemo.R;
import mrgood.com.mvpdemo.model.bean.Contentlist;
import mrgood.com.mvpdemo.ui.adapter.holder.NewsViewholder;

/**
 * Created by lh on 2017/2/10 0010.
 */

public class NewsAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Contentlist>list;
    public NewsAdapter(Context context,List<Contentlist> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public NewsViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news,null);
        return new NewsViewholder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(itemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    itemClickListener.ItemClick(holder.itemView,position); // 2
                }
            });
        }
        NewsViewholder myholder = (NewsViewholder)holder;
        myholder.title.setText(list.get(position).getTitle()!=null?list.get(position).getTitle():"空数据");
        myholder.time.setText(list.get(position).getPubDate()!=null?list.get(position).getPubDate():"空数据");
        myholder.subtitle.setText(list.get(position).getDesc()!=null?list.get(position).getDesc():"空数据");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private OnItemClickListener itemClickListener;
    public interface OnItemClickListener {
        void ItemClick(View view,int positon);
    }

    public OnItemClickListener getItemClickListener(){
        return itemClickListener;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
