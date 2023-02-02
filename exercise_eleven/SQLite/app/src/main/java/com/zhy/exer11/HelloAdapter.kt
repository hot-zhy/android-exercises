package com.zhy.exer11

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HelloAdapter(val stringList:List<String>): RecyclerView.Adapter<HelloAdapter.ViewHolder>() {
    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val string:TextView=view.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val string=stringList[position]
        holder.string.text=string
    }


    override fun getItemCount()=stringList.size
}