package com.android.numberone.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.numberone.R;
import com.android.numberone.activity.NewsContentActivity;
import com.android.numberone.fragment.NewsContentFragment;
import com.android.numberone.fragment.NewsTitleFragment;
import com.android.numberone.mode.News;

import java.util.List;


/**
 * Created by lenovo on 2018/3/22.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private static final String TAG = "zch";
    private List<News> fruitList;
    private NewsTitleFragment titleFragment;


    public FruitAdapter(List<News> fruitList, NewsTitleFragment titleFragment) {
        this.fruitList = fruitList;
        this.titleFragment=titleFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context=parent.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.news_title_frag,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.sendBroadcast(new Intent("com.numberone.receiver"));
                News news =fruitList.get(holder.getAdapterPosition());
                if (titleFragment.isTwoPane){
                    NewsContentFragment newsContentFragment=(NewsContentFragment)titleFragment.getFragmentManager().findFragmentById(R.id.news_content_fragment);
                    newsContentFragment.refresh(news.getTitle(),news.getContent());
                }else {
                    NewsContentActivity.actionStart(titleFragment.getActivity(),news.getTitle(),news.getContent());
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News msg=fruitList.get(position);
        holder.newsTitle.setText(msg.getTitle());
    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView newsTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            newsTitle=itemView.findViewById(R.id.news_title);
        }
    }
}
