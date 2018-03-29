package com.android.numberone.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.numberone.R;
import com.android.numberone.activity.MainActivity;
import com.android.numberone.mode.News;

import java.util.List;

/**
 * Created by lenovo on 2018/3/23.
 */

public class NewsContentFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.news_content_frag,container,false);
        return view;
    }
    public void refresh(String newsTitle,String newsContent){
        View viewLayout=view.findViewById(R.id.visibility_layout);
        viewLayout.setVisibility(View.VISIBLE);
        TextView newTitle=view.findViewById(R.id.news_titile);
        TextView newContent=view.findViewById(R.id.new_content);
        newTitle.setText(newsTitle);
        newContent.setText(newsContent);

    }
}
