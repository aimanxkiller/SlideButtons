package com.example.slidebuttons.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SimpleSwipeListener
import com.daimajia.swipe.SwipeLayout
import com.example.slidebuttons.ListClickListener
import com.example.slidebuttons.People
import com.example.slidebuttons.R
import com.example.slidebuttons.activity.FavLayoutActivity

class TesterAdapter(private val context: FavLayoutActivity, private var list: List<People>) :RecyclerView.Adapter<TesterAdapter.ViewHolder>() {

    private var listener: ListClickListener? = null
    fun setItemClickListener(listener: ListClickListener) {
        this.listener = listener
    }

    //Reference for items
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val name:TextView = itemView.findViewById(R.id.tv_name)
        val acc:TextView = itemView.findViewById(R.id.tv_account_no)
        val bankName:TextView = itemView.findViewById(R.id.tv_bank_name)
        val divider:ImageView = itemView.findViewById(R.id.item_list_divider)
        val view:RelativeLayout = itemView.findViewById(R.id.surface_view)

        val btn:ImageView = itemView.findViewById(R.id.btn_3dots)
        val h_btn1:ImageView = itemView.findViewById(R.id.hdn_btn_1)
        val h_btn2:ImageView = itemView.findViewById(R.id.hdn_btn_2)
        val h_btn3:ImageView = itemView.findViewById(R.id.hdn_btn_3)
        val swiper:SwipeLayout = itemView.findViewById(R.id.swiper_view)
        val pin:View = itemView.findViewById(R.id.icon_pin)
    }

    fun setData(peopleList: List<People>) {
        this.list = peopleList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return ViewHolder(view)
    }

    private var currentlyOpenSwipeLayout: SwipeLayout? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.name.text = data.name
        holder.acc.text = data.age.toString()
        holder.bankName.text = data.id

        if (list[position].pin){
            holder.pin.visibility = View.VISIBLE
        }else{
            holder.pin.visibility = View.GONE
        }

        //Start
        holder.btn.setOnClickListener {
            if (currentlyOpenSwipeLayout != null && currentlyOpenSwipeLayout != holder.swiper) {
                currentlyOpenSwipeLayout?.close()
            }
            if (holder.swiper.openStatus == SwipeLayout.Status.Open){
                holder.swiper.close()
            }else if(holder.swiper.openStatus == SwipeLayout.Status.Close){
                holder.swiper.open()
            }
            currentlyOpenSwipeLayout = holder.swiper
        }
        // Close the currently open SwipeLayout when user starts dragging another one
        holder.swiper.addSwipeListener(object : SimpleSwipeListener() {
            override fun onStartOpen(layout: SwipeLayout) {
                if (currentlyOpenSwipeLayout != null && currentlyOpenSwipeLayout != layout) {
                    currentlyOpenSwipeLayout?.close()
                }
            }
        })
        // Set the currently open SwipeLayout when it's opened or closed
        holder.swiper.addSwipeListener(object : SimpleSwipeListener() {
            override fun onOpen(layout: SwipeLayout) {
                currentlyOpenSwipeLayout = layout
            }
        })
        //End

        holder.h_btn1.setOnClickListener {
            listener?.onItemPin(data.id)
        }
        holder.h_btn2.setOnClickListener {
            Toast.makeText(context,"Button 2 Pressed at $position", Toast.LENGTH_SHORT).show()
        }
        holder.h_btn3.setOnClickListener {
            Toast.makeText(context,"Button 3 Pressed at $position", Toast.LENGTH_SHORT).show()
        }

        if (position == list.size-1){
            holder.divider.visibility = View.GONE
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }


}