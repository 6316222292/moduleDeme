package com.android.numberone.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.numberone.R
import com.android.numberone.fragment.NewsContentFragment

class NewsContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_content)
        val newsTitle=intent.getStringExtra("news_title")
        val newsContent=intent.getStringExtra("news_content")
        val fragment= supportFragmentManager.findFragmentById(R.id.news_content_fragment) as NewsContentFragment
        fragment.refresh(newsTitle,newsContent)
    }
    companion object {
        @JvmStatic
        fun actionStart(context:Context,newsTitle:String,newsContent:String){
            val intent=Intent(context,NewsContentActivity::class.java)
            intent.putExtra("news_title",newsTitle)
            intent.putExtra("news_content",newsContent)
            context.startActivity(intent)
        }
    }
}
