package com.zhy.exer11

import android.app.Service
import android.content.ContentValues
import android.content.Intent
import android.os.*
import android.util.Log
import android.widget.Toast
import kotlin.concurrent.thread
import kotlin.system.exitProcess

class MyService : Service() {

    @Volatile
    private var running = false
    lateinit var child:Thread

    override fun onCreate() {
        super.onCreate()
        // 数据库建表
        MyDBHandler(this, "MyDatabase.db", 3).writableDatabase
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        running = true
        var counter=1
        child=thread {
            while (running) {
                Thread.sleep(10000)
                val string="hello——$counter"
                counter++
                MyDBHandler(this, "MyDatabase.db", 3).writableDatabase.use { db ->
                    ContentValues().apply {
                        put("string", string)
                        db.insert("Hello", null, this)
                    }
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        running = false
        child.join()
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

}