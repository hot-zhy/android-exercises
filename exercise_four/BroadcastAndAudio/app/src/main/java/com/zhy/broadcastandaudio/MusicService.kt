package com.zhy.broadcastandaudio

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MusicService:Service() {
    private val mediaPlayer= MediaPlayer()
    override fun onCreate() {
        super.onCreate()
        initMediaPlayer()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "service启动", Toast.LENGTH_SHORT).show()
        if(!mediaPlayer.isPlaying){
            mediaPlayer.start()
        }else{
            mediaPlayer.reset()
            initMediaPlayer()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun initMediaPlayer() {
        val assetManager=assets
        val fd=assetManager.openFd("music.mp3")
        mediaPlayer.setDataSource(fd.fileDescriptor,fd.startOffset,fd.length)
        mediaPlayer.prepare()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}