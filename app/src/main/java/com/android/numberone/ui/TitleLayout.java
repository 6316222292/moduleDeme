package com.android.numberone.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.numberone.R;

public class TitleLayout extends LinearLayout implements View.OnClickListener {

    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.title_view,this);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.meun).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
            case R.id.meun:
                Toast.makeText(getContext(),"事件",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
