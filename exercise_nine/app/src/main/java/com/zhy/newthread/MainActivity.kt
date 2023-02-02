package com.zhy.newthread

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStreamWriter
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var randomStr:StringBuilder
    lateinit var manager:NotificationManager
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //创建通知渠道
        manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel=NotificationChannel("new","Normal",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)

        }

        button.setOnClickListener {
            thread {
                //产生一个字符串
                val dictChars = mutableListOf<Char>().apply {
                    "123456789zxcvbnmasdfghjklqwertyuiop".forEach {
                        this.add(it)
                    }
                }
                randomStr = StringBuilder().apply { (1..((100..300).random())).onEach { append(dictChars.random()) } }
                //休眠10秒
                runOnUiThread {
                    text.text="数据等待中...."
                    button.text="已点击，请等10s"
                    button.isEnabled=false
                }
                Thread.sleep(10000)
                //完成休眠后将字符串存储到文件
                loadStringInFile(randomStr)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun loadStringInFile(randomStr: StringBuilder) {
        try {
            val output=openFileOutput("data",Context.MODE_PRIVATE)
            val writer=BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(randomStr.toString())
            }
            runOnUiThread {
                Toast.makeText(this,"存储成功",Toast.LENGTH_SHORT).show()
                text.text="计算已完成"
                button.text="点击启动新线程"
                button.isEnabled
            }
            //发送一条通知
            sendNotification()
        }catch (e:IOException){
            e.printStackTrace()
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun sendNotification() {
        val intent= Intent(this,SecondActivity::class.java)
        val pi= PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_IMMUTABLE)
        val notification= NotificationCompat.Builder(this,"new")
            .setContentTitle("通知")
            .setContentText("点击查看字符串")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.ic_launcher_foreground))
            .setContentIntent(pi)
            .setAutoCancel(true)
            .build()
        manager.notify(1,notification)
    }
}