package com.example.notificationtest

import android.annotation.SuppressLint
import android.app.Notification
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.NotificationManager
import android.app.NotificationChannel
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*
import android.app.PendingIntent
import android.content.Intent
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    var counter=1
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel=NotificationChannel("new","new",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        sendNotice.setOnClickListener {
            val intent=Intent(this,NotificationActivity::class.java)
            val pi=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_IMMUTABLE)
            val notification=NotificationCompat.Builder(this,"new")
                .setContentTitle("notification计数器")
                .setContentText("hello $counter")
                .setSmallIcon(R.drawable.large_icon)
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.large_icon))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build()
            manager.notify(counter,notification)
            counter++
        }
    }

}
