package com.android.numberone.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.android.numberone.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_fruit.*

class FruitActivity : AppCompatActivity() {
    companion object {
        @JvmField
        val FRUIT_NAME="fruit_name"
        @JvmField
        val FRUIT_IMAGE_ID="fruit_image_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit)
        val fruitName=intent.getStringExtra(FRUIT_NAME)
        val frutiImageId=intent.getIntExtra(FRUIT_IMAGE_ID,0)
        setSupportActionBar(toolbar)
        val actionBar=supportActionBar
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        collapsingToolBar.title=fruitName
        Glide.with(this).load(frutiImageId).into(fruitImage)
        var fruitContent=generateFruitContent(fruitName)
        fruitContentText.text=fruitContent
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home->{
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun generateFruitContent(fruitName: String?):String {
        val fruitContent=StringBuffer()
        var i=0
        while (i<50){
            fruitContent.append(fruitName)
            i++
        }
        return fruitContent.toString()
    }
}
