package com.zhy.activity_exercise_one

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    private val tag="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login.setOnClickListener {
            val intent=Intent(this,LoginSuccessActivity::class.java)
            intent.apply {
                putExtra("name",name.text.toString())
                putExtra("password",password.text.toString())
                putExtra("remember",remember_account.isChecked)
            }
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart")}
}