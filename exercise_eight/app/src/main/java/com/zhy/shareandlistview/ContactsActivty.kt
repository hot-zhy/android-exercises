package com.zhy.shareandlistview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_contacts_activty.*

class ContactsActivty : AppCompatActivity() {
    private val data = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_activty)
        loadData()
        val adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)
        listView.adapter=adapter
    }

    private fun loadData() {
//        获取偏好配置中的通讯录
        val prefs=getSharedPreferences("contactsList",Context.MODE_PRIVATE)
        for(i in prefs.all){
            i.let {
                data.add(i.value.toString())
            }
        }
        //按照名字字典序升序排列
        data.sortBy {
            it
        }
    }
}