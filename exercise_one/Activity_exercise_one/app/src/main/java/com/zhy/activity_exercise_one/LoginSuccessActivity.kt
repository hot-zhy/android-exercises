package com.zhy.activity_exercise_one

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login_success.*

class LoginSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_success)
        name_return.text=intent.getStringExtra("name")
        password_return.text=intent.getStringExtra("password")
        remember_return.text=when(intent.getBooleanExtra("remember",false)){
            false->"否"
            true->"是"
        }
        return_login.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }


}