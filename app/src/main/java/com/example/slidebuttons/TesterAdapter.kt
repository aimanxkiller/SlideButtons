package com.example.slidebuttons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout

class TesterAdapter(private val context: FavLayoutActivity, private val list: List<People>) :RecyclerView.Adapter<TesterAdapter.ViewHolder>() {


    //Reference for items
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val name:TextView = itemView.findViewById(R.id.tv_name)
        val acc:TextView = itemView.findViewById(R.id.tv_account_no)
        val bankName:TextView = itemView.findViewById(R.id.tv_bank_name)

        val btn:ImageView = itemView.findViewById(R.id.btn_3dots)
        val h_btn1:ImageView = itemView.findViewById(R.id.hdn_btn_1)
        val h_btn2:ImageView = itemView.findViewById(R.id.hdn_btn_2)
        val h_btn3:ImageView = itemView.findViewById(R.id.hdn_btn_3)
        val swiper:SwipeLayout = itemView.findViewById(R.id.swiper_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.name.text = data.name
        holder.acc.text = data.age.toString()
        holder.bankName.text = data.id

        holder.btn.setOnClickListener {
            if (holder.swiper.openStatus == SwipeLayout.Status.Open){
                holder.swiper.close()
            }else if(holder.swiper.openStatus == SwipeLayout.Status.Close){
                holder.swiper.open()
            }
        }

        holder.h_btn1.setOnClickListener {
            Toast.makeText(context, "Button 1 Pressed at $position", Toast.LENGTH_SHORT).show()
        }
        holder.h_btn2.setOnClickListener {
            Toast.makeText(context,"Button 2 Pressed at $position", Toast.LENGTH_SHORT).show()
        }
        holder.h_btn3.setOnClickListener {
            Toast.makeText(context,"Button 3 Pressed at $position", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}