package com.zhy.exer11

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_data.*
import java.util.*
import kotlin.collections.ArrayList

class DataActivity : AppCompatActivity() {
    private val stringList = ArrayList<String>()
    lateinit var adapter:HelloAdapter
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        readDataFromSQLite()
        val layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        adapter=HelloAdapter(stringList)
        adapter.notifyItemChanged(stringList.size-1)
        recyclerView.adapter=adapter
    }

    private fun readDataFromSQLite() {
        MyDBHandler(this, "MyDatabase.db", 3).readableDatabase.use { db ->
            db.query("Hello", arrayOf("string"), null, null, null, null, null, null).use {
                if (it.moveToFirst()) {
                    do {
                        stringList+=it.getString(it.getColumnIndex("string"))
                    } while (it.moveToNext())
                }
            }
        }
    }
}