package com.zhy.phonetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter(val contactsList:List<Contact>):RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val name:TextView=view.findViewById(R.id.name)
        val phone:TextView=view.findViewById(R.id.phone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact=contactsList[position]
        holder.name.text=contact.name
        holder.phone.text=contact.phone
    }

    override fun getItemCount()=contactsList.size
}