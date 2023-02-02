package com.zhy.dialogactivity

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    val updateText=1
    val handler=object :Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            when(msg.what){
                updateText->
                    number.text="你好"
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start.setOnClickListener {
            val intent=Intent(this,DialogActivity::class.java)
            startActivityForResult(intent,1)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1->
                if(resultCode== RESULT_OK){
                    val returnedData=data?.getStringExtra("number")
                    Log.d("return", "return$returnedData")
                    number.text=returnedData
                }
        }
    }
}