package com.zhy.download

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.AsyncTask
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import java.io.BufferedOutputStream
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

class myTask(context: Context, button: Button, progressBar: ProgressBar, textView: TextView): AsyncTask<Unit, Int, String>(){

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context
    @SuppressLint("StaticFieldLeak")
    lateinit var button: Button
    @SuppressLint("StaticFieldLeak")
    lateinit var progressBar: ProgressBar
    @SuppressLint("StaticFieldLeak")
    lateinit var textView: TextView
    var uri="http://115.29.231.93:8080/compare/mongodb.tgz"
    var downloadId:Long=0

    init {
        this.context = context
        this.button = button
        this.progressBar = progressBar
        this.textView = textView
    }

    override fun doInBackground(vararg p0: Unit?): String {
        for(i in 1..100){
            Thread.sleep(100)
            publishProgress(i)
        }
        var request=DownloadManager.Request(Uri.parse(uri))
            .setTitle("下载文件")
            .setDescription("文件较大，请稍等")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            .setAllowedOverMetered(true)
        var dm=context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadId=dm.enqueue(request)
        var br=object :BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                var id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (id == downloadId) {
                    Toast.makeText(context, "下载成功!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        context.registerReceiver(br,IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
        return "下载完成！"
    }


    @SuppressLint("SetTextI18n")
    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        val progress =values[0]
        textView.text = "$progress% 下载中..."
        progressBar.progress = progress!! // !! promise never null value
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        textView.text = result
        button.isEnabled = true
    }

    override fun onPreExecute() {
        super.onPreExecute()
        textView.text = "Preparing"
        button.isEnabled = false
        Thread.sleep(1000)
    }
}