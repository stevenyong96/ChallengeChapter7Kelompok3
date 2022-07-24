package com.example.challengechapter7kelompok3.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.challengechapter7kelompok3.R

class DataMainAdapter(private val itemList : List<DataMain>) {
//    class itemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
//        val itemImageView: ImageView = itemView.findViewById(R.id.cover)
//        val itemDescText : TextView = itemView.findViewById(R.id.title)
//        val itemPrice: TextView = itemView.findViewById(R.id.price)
//    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder
//    {
//        val from = LayoutInflater.from(parent.context)
//        val binding = CardCellBinding.inflate(from, parent, false)
//        return CardViewHolder(binding, clickListener)
//    }
//
//    override fun onBindViewHolder(holder: CardViewHolder, position: Int)
//    {
//        val item = itemList[position]
//        holder.itemImageView.setImageResource(item.itemImage)
//        holder.itemDescText = item.itemDesc
//    }
//
//    override fun getItemCount(): Int = itemList.size

}