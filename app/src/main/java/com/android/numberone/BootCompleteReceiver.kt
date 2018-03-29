package com.android.numberone

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class BootCompleteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.e("tag","开机广播")
        Toast.makeText(context,"我是开机广播",Toast.LENGTH_LONG).show()
//        throw UnsupportedOperationException("Not yet implemented")
    }
}
