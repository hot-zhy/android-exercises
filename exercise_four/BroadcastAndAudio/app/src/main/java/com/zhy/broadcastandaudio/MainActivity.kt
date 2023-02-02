package com.zhy.broadcastandaudio

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var playMusic=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        music.setOnClickListener {
//            发送一条广播
            val intent=Intent("com.zhy.broadcastandaudio.MY_BROADCAST")
            intent.setPackage(packageName)
            intent.putExtra("play",playMusic)
            playMusic=!playMusic
            sendBroadcast(intent)
            if(playMusic==true){
                music.text="开始播放"
            }else{
                music.text="停止播放"
            }

        }
    }


}