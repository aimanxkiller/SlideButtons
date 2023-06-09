package com.example.slidebuttons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecentTransactionAdapter(private val mainList: ArrayList<String>) : RecyclerView.Adapter<RecentTransactionAdapter.ViewHolder>() {

    //Reference for items
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val name:TextView =  itemView.findViewById(R.id.favName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.most_recent_transaction_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=mainList[position]
        holder.name.text = item
    }



}
