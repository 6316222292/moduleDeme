package com.android.numberone.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.android.numberone.R;
import com.android.numberone.mode.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main2Activity extends BaseActivity {
    List<Fruit> fruitList=new ArrayList<>();
    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main2);
        initFurits();
        inputText=findViewById(R.id.inputText);
        final RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
//        final FruitAdapter fruitAdapter=new FruitAdapter(fruitList);
//        recyclerView.setAdapter(fruitAdapter);
//        recyclerView.scrollToPosition(fruitList.size()-1);
//        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String content=inputText.getText().toString().trim();
//                if (!content.equals("")){
//                    Fruit fruit=new Fruit(content,Fruit.getTYPE_SENT());
//                    fruitList.add(fruit);
//                    fruitAdapter.notifyItemInserted(fruitList.size()-1);//刷新最后一条数据
//                    recyclerView.scrollToPosition(fruitList.size()-1);//定位到最后一条信息位置
//                    inputText.setText("");
//                }
//            }
//        });
    }

    private void initFurits() {
        for (int i=0;i<20;i++){
            Fruit fruit;
            if (i%2==0){
                fruit=new Fruit("我是大海",Fruit.getTYPE_RECEIVED());
            }else {
                fruit=new Fruit("我是成海",Fruit.getTYPE_SENT());
            }
            fruitList.add(fruit);
        }
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
