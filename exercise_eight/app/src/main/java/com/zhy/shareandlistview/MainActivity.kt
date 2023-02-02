package com.zhy.shareandlistview

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val contactsList=ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (contactsList.size==0){
           load.setOnClickListener {
               addInPreferences()
           }
        }
        show.setOnClickListener {
            val intent=Intent(this,ContactsActivty::class.java)
            startActivity(intent)
        }
    }

    private fun addInPreferences() {
        //获取通讯录信息
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),1)
        }else{
            readcontacts()
        }
    }

    @SuppressLint("Range", "Recycle")
    private fun readcontacts() {
        val editor=getSharedPreferences("contactsList",Context.MODE_PRIVATE).edit()
        contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)?.apply {
            while (moveToNext()){
                val displayName=getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number=getString(getColumnIndex((ContactsContract.CommonDataKinds.Phone.NUMBER)))
                contactsList.add("$displayName\n$number")
            }
        }
        load.isEnabled=false
        runOnUiThread {
            load.text="已加载"
        }
        //将通讯录添加到偏好设置
        for(i in 0 until contactsList.size){
            editor.putString("contact$i", contactsList[i])
        }
        editor.apply()
        Toast.makeText(this,"加载成功，点击“显示”即可查看通讯录，共有${contactsList.size}个联系人",Toast.LENGTH_SHORT).show()
        Log.d("number","number=${contactsList.size}")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1->{
                if(grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    readcontacts()
                }else{
                    Toast.makeText(this,"你拒绝了授予权限",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}