package com.zhy.broadcastandaudio

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.widget.Toast

class MyBroadcastReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val play=intent?.getBooleanExtra("play",true)
        Toast.makeText(context, "received in MyBroadcastReceiver${play.toString()}", Toast.LENGTH_SHORT).show()
//        收到第一条广播就开启service，收到第二条就关闭service，开启service即播放音乐
        val serviceIntent=Intent(context,MusicService::class.java)
        when(play){
            true-> context?.startService(serviceIntent)
            false-> context?.stopService(serviceIntent)
        }

    }
}