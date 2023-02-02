package com.zhy.exer11

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHandler(context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {

    private val createStringTable = "create table Hello (id integer primary key autoincrement,string text)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createStringTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}