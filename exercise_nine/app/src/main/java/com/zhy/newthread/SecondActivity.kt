package com.zhy.newthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        //读取字符串
        string.text=load()
    }

    private fun load():String {
        val content=StringBuilder()
        try {
            val input=openFileInput("data")
            val reader=BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        }catch (e:IOException){
            e.printStackTrace()
        }
        return content.toString()
    }
}