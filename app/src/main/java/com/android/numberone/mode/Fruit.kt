package com.android.numberone.mode

/**
 * Created by lenovo on 2018/3/22.
 */
data class Fruit(val content:String,val type:Int){
    companion object {
        @JvmStatic
        val TYPE_RECEIVED:Int =0
        @JvmStatic
        val TYPE_SENT:Int=1
    }
}