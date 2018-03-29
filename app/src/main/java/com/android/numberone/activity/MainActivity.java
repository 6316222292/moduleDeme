package com.android.numberone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.numberone.R;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*findViewById(R.id.leftBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                replaceFragment(new RightFragment());
            }
        });*/

//        replaceFragment(new AnotherRightFragment());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.right_layout,fragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                toast("添加");
                startActivity(new Intent("com.android.numberone.ACTION_START").addCategory("com.android.numberone.MY_CATEGORY"));
//                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.baidu.com")));
                break;
            case R.id.remove:
                toast("删除");
                break;
        }
        return true;
    }
    public void toast(String messge){
        Toast.makeText(this,messge,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }
}
