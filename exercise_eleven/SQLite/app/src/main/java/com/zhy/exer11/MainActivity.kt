package com.zhy.exer11

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkLite.setOnClickListener {
            val intent=Intent(this,DataActivity::class.java)
            startActivity(intent)
        }

        startBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent)
            Toast.makeText(this,"开启Service，每隔十秒产生一条记录",Toast.LENGTH_SHORT).show()
            startBtn.isEnabled=false
            stopBtn.isEnabled=true
        }

        stopBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
            Toast.makeText(this,"停止了Service不再产生字符串",Toast.LENGTH_SHORT).show()
            startBtn.isEnabled=true
            stopBtn.isEnabled=false
        }

    }
}