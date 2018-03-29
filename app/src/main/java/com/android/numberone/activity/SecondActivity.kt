package com.android.numberone.activity

import android.app.Activity
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.support.v4.app.NotificationCompat
import android.support.v4.content.FileProvider
import android.support.v4.view.GravityCompat
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.android.numberone.R
import com.android.numberone.adapter.FruitAdapter1
import com.android.numberone.mode.Fruit
import kotlinx.android.synthetic.main.switch_item.*
import java.io.File
import kotlin.math.log

/**
 * Created by lenovo on 2018/3/21.
 */

class SecondActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!.id){

            R.id.fab->snackbar(v,"我是悬浮按钮")
        }
    }

    private fun openCamer() {
        //创建file对象，用于存储拍照后的照片
        val outputImage =File(externalCacheDir,"output_image.jpg")
        if (outputImage.exists()){
            outputImage.delete()
        }
        outputImage.createNewFile()
        if (Build.VERSION.SDK_INT>=24){
            imageUri=FileProvider.getUriForFile(this,"com.examle",outputImage)
        }else{
            imageUri=Uri.fromFile(outputImage)
        }
        startActivityForResult(Intent("android.media.action.IMAGE_CAPTURE").putExtra(MediaStore.EXTRA_OUTPUT,imageUri),TAKE_PHOTO)
    }

    val fruits= arrayListOf<Fruit>(
            Fruit("apple",R.mipmap.a1)
            ,Fruit("apple",R.mipmap.a2)
            ,Fruit("apple",R.mipmap.a3)
            ,Fruit("apple",R.mipmap.a4)
            ,Fruit("apple",R.mipmap.a5)
            ,Fruit("apple",R.mipmap.a6)
            ,Fruit("apple",R.mipmap.a7)
            ,Fruit("apple",R.mipmap.a8)
            ,Fruit("apple",R.mipmap.a1)
            ,Fruit("apple",R.mipmap.a2)
            ,Fruit("apple",R.mipmap.a3)
            ,Fruit("apple",R.mipmap.a4)
            ,Fruit("apple",R.mipmap.a5)
            ,Fruit("apple",R.mipmap.a6)
            ,Fruit("apple",R.mipmap.a7)
            ,Fruit("apple",R.mipmap.a8)
            ,Fruit("apple",R.mipmap.a2)
            ,Fruit("apple",R.mipmap.a3)
            ,Fruit("apple",R.mipmap.a4)
            ,Fruit("apple",R.mipmap.a5)
            ,Fruit("apple",R.mipmap.a6)
            ,Fruit("apple",R.mipmap.a7)
            ,Fruit("apple",R.mipmap.a8)
            ,Fruit("apple",R.mipmap.a1)
            ,Fruit("apple",R.mipmap.a2)
            ,Fruit("apple",R.mipmap.a3)
            ,Fruit("apple",R.mipmap.a4)
            ,Fruit("apple",R.mipmap.a5)
            ,Fruit("apple",R.mipmap.a6)
            ,Fruit("apple",R.mipmap.a7)
            ,Fruit("apple",R.mipmap.a8)
            )
    private val TAG = "SecondActivity"
    val TAKE_PHOTO:Int=1
    private var imageUri: Uri? = null
    lateinit var notificationManager:NotificationManager
    lateinit var pi:PendingIntent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.switch_item)
        setSupportActionBar(bar)
        val actionBar=supportActionBar
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher)
            actionBar.setDisplayShowTitleEnabled(false)
        }
        notificationManager= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        pi= PendingIntent.getActivity(this@SecondActivity,0,Intent(this@SecondActivity,MainActivity::class.java),0)
        nav_view.setCheckedItem(R.id.nav_call)
        nav_view.setNavigationItemSelectedListener {
            toast(it.title.toString())
            drawerLayout.closeDrawers()
            true
        }
        val adapter1=FruitAdapter1(fruits)
        val layoutManager=GridLayoutManager(this,2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter=adapter1
        fab.setOnClickListener(this)
        swipeRefresh.setColorSchemeResources(R.color.colorAccent)
        swipeRefresh.setOnRefreshListener {
            refreshFruits()
            Log.e(TAG,"refreshFruits")
        }
    }

    private fun refreshFruits() {
        Thread(Runnable {
            Thread.sleep(2000)
            runOnUiThread({
                swipeRefresh.isRefreshing=false
            })
        }).start()

    }

    private fun sendNotification() {
        val notification:Notification =NotificationCompat.Builder(this)
                .setContentTitle("测试标题")
                .setContentText("测试内容")
                .setContentIntent(pi)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.mipmap.ic_launcher))
                .build()
        notificationManager.notify(1,notification)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar,menu)
        return true
    }

    fun toast(content:String){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show()
    }
    fun snackbar(view:View,content: String){
        Snackbar.make(view,content,Snackbar.LENGTH_LONG)
                .setAction("确定", {
                    toast("我是按钮回馈")
                }).show()
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.backup->sendNotification()
            R.id.delete->openCamer()
            R.id.setting->toast("setting")
            android.R.id.home->drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            TAKE_PHOTO->{
                if ( resultCode== Activity.RESULT_OK){
                   val bitmap=BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
                    picture.setImageBitmap(bitmap)
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
        /*val intent = Intent(this, NewsContentActivity::class.java)
        intent.putExtra("newsTitle", "sdfsd")
        startActivity(intent)*/
    }
}
