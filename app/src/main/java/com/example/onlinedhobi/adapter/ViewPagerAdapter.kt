package com.example.onlinedhobi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinedhobi.R

class ViewPagerAdapter(private var title:List<String>,private var description:List<String>,private var img:List<Int>):RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {

   inner class Pager2ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       val itemTitle: TextView = itemView.findViewById(R.id.viewtitle)
       val itemDes: TextView = itemView.findViewById(R.id.viewdes)
       val itemImg: ImageView = itemView.findViewById(R.id.viewimg)

   }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item,parent,false))
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {
        holder.itemTitle.text = title[position]
        holder.itemDes.text = description[position]
        holder.itemImg.setImageResource(img[position])
    }

    override fun getItemCount(): Int {
        return title.size
    }
}