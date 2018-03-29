package com.android.numberone.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.numberone.R;
import com.android.numberone.activity.FruitActivity;
import com.android.numberone.activity.NewsContentActivity;
import com.android.numberone.fragment.NewsContentFragment;
import com.android.numberone.fragment.NewsTitleFragment;
import com.android.numberone.mode.Fruit;
import com.android.numberone.mode.News;
import com.bumptech.glide.Glide;

import java.util.List;


/**
 * Created by lenovo on 2018/3/22.
 */

public class FruitAdapter1 extends RecyclerView.Adapter<FruitAdapter1.ViewHolder> {
    private static final String TAG = "zch";
    private List<Fruit> fruitList;
    private Context context;

    public FruitAdapter1(List<Fruit> fruitList) {
        this.fruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context==null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.fruit_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Fruit fruit=fruitList.get(position);
                Intent intent=new Intent(context, FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getContent());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.getType());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit=fruitList.get(position);
        holder.cardName.setText(fruit.getContent());
//        holder.cardImage.setImageResource(fruit.getType());
        Glide.with(context).load(fruit.getType()).into(holder.cardImage);
    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView cardImage;
        TextView cardName;
        public ViewHolder(View itemView) {
            super(itemView);
            cardImage=itemView.findViewById(R.id.cardImage);
            cardName=itemView.findViewById(R.id.cardName);
        }
    }
}
