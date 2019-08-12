package com.sohail.kotlinsample2.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sohail.kotlinsample2.R
import kotlinx.android.synthetic.main.item.view.*

class CustomAdapter(val context: Context,val list: List<String>) : RecyclerView.Adapter<CustomAdapter.Viewholder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomAdapter.Viewholder {

        val itemView=LayoutInflater.from(p0.context).inflate(R.layout.item,p0,false)
        return Viewholder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CustomAdapter.Viewholder, i: Int) {
        holder.Img?.setImageResource(R.drawable.img)
        holder.txt?.setText(list.get(i))
    }

    class Viewholder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val Img=itemView.cardImg
        val txt=itemView.cardText
    }
}
