package com.zhy.phonetest

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val contactsList=ArrayList<Contact>()
    private lateinit var adapter:ContactsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layoutManager=LinearLayoutManager(this)
        contactsView.layoutManager=layoutManager
        adapter=ContactsAdapter(contactsList)
        contactsView.adapter=adapter


        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),1)
        }else{
            readContacts()
        }
    }

    @SuppressLint("Range", "NotifyDataSetChanged")
    private fun readContacts() {
        contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)?.apply {
            while (moveToNext()){
                val displayName=getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number=getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contactsList.add(Contact(displayName,number))
            }
            adapter.notifyDataSetChanged()
            close()
        }
        for(i in contactsList.indices){
            Log.d("phone","phone=${contactsList[i].phone}")
        }
    }

}