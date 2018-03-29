package com.android.numberone.task

import android.os.AsyncTask
import android.os.Environment
import com.android.numberone.DownloadListener
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.InputStream
import java.io.RandomAccessFile

/**
 * Created by lenovo on 2018/3/27.
 */
class DownloasTask (val listener:DownloadListener) : AsyncTask<String, Int, Int>() {

    companion object {
        val TYPE_SUCCESS:Int=0
        val TYPE_FAILED:Int=1
        val TYPE_PAUSED=2
        val TYPE_CANCELED=3
    }
    var isCanceled:Boolean=false
    var isPaused:Boolean=false
    var lastProgress:Int=0




    override fun doInBackground(vararg params: String?): Int {
        var istream: InputStream? =null
        var savedFile:RandomAccessFile? =null
        var file:File? =null
        try {
            var downloadedLength:Long=0//记录已下载的文件长度
            val downloadUrl=params[0]//下载地址
            val fileName=downloadUrl!!.substring(downloadUrl!!.lastIndexOf("/"))
            val directory=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path
            file= File(directory+fileName)
            if (file.exists()){
                downloadedLength=file.length()
            }
            var contentLength:Long=getContentLength(downloadUrl)
            if (contentLength== 0L){
                return TYPE_FAILED
            }else if (contentLength==downloadedLength){
                return TYPE_SUCCESS
            }
            val client=OkHttpClient()
            val request= Request.Builder()
                    .addHeader("RANGE", "bytes=$downloadedLength-")
                    .url(downloadUrl)
                    .build()
            val response=client.newCall(request).execute()
            if (response!=null){
                istream=response.body().byteStream()
                savedFile= RandomAccessFile(file,"rw")
                savedFile.seek(downloadedLength)
                val b = ByteArray(1024)
                var total=0
                var len=istream.read(b)
                while (len!=1){
                    when {
                        isCanceled -> return TYPE_CANCELED
                        isPaused -> return TYPE_PAUSED
                        else -> total+=len
                    }
                    savedFile.write(b,0,len)
                    var progress=((total+downloadedLength)*100/contentLength ) as Int
                    publishProgress(progress)
                }
                response.body().close()
                return TYPE_SUCCESS
            }
        }catch (e:Exception){

        }finally {
            try {
                if (istream!=null){
                    istream.close()
                }
                if (savedFile!=null){
                    savedFile.close()
                }
                if (isCanceled&&file!=null){
                    file.delete()
                }
            }catch (e:Exception){

            }
        }
        return TYPE_FAILED
    }

    private fun getContentLength(downloadUrl: String): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProgressUpdate(vararg values: Int?) {
        var progress=values[0]
        if (progress!! >lastProgress){
            listener.onProgress(progress)
            lastProgress=progress
        }

    }

    override fun onPostExecute(result: Int?) {
        when(result){
            TYPE_SUCCESS->listener.onSuccess()
            TYPE_FAILED->listener.onFailed()
            TYPE_PAUSED->listener.onPaused()
            TYPE_CANCELED->listener.onCanceled()
        }
    }
    fun pauseDownload(){
        isPaused=true
    }
    fun cancelDownload(){
        isCanceled=true
    }
}