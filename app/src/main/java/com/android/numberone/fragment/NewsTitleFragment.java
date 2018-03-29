package com.android.numberone.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.numberone.adapter.FruitAdapter;
import com.android.numberone.R;
import com.android.numberone.mode.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lenovo on 2018/3/23.
 */

public class NewsTitleFragment extends Fragment {
    public boolean isTwoPane;
    private List<News> news=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_titile_frag, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(getNews(), this);
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout)!=null){
            isTwoPane=true;
        }else {
            isTwoPane=false;
        }
    }

    public List<News> getNews() {
        List<News> newsList=new ArrayList<>();
        for (int i=0;i<=50;i++){
            News news=new News("我是标题",getRandomLengName("我是新闻内容"));
            newsList.add(news);
        }
        news.addAll(newsList);
        return newsList;
    }
    public String getRandomLengName(String name) {
        Random random=new Random();
        int length=random.nextInt(20)+1;
        StringBuffer buffer=new StringBuffer();
        for (int i=0;i<length;i++){
            buffer.append(name);
        }
        return buffer.toString();
    }
}

