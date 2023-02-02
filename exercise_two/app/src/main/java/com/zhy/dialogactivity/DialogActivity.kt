package com.zhy.dialogactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_dialog.*
import kotlinx.android.synthetic.main.activity_main.*

class DialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        save.setOnClickListener {
            val intent=Intent()
            intent.putExtra("number",input_number.text.toString())
            setResult(RESULT_OK,intent)
            finish()
        }
    }
}