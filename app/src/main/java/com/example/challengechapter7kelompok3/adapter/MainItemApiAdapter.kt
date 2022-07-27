package com.example.challengechapter7kelompok3.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengechapter7kelompok3.databinding.CardItemLinearBinding
import com.example.challengechapter7kelompok3.databinding.FragmentMainContentBinding
import com.example.challengechapter7kelompok3.model.DataMain
import com.example.challengechapter7kelompok3.model.ResponseGetItem
import java.text.DecimalFormat
import java.text.NumberFormat

class MainItemApiAdapter(val onItemStoreClick: (ResponseGetItem) -> Unit ) : RecyclerView.Adapter<MainItemApiAdapter.ViewHolder>() {

    private val contentList =  mutableListOf<ResponseGetItem>()

    fun addContentList(newList: MutableList<ResponseGetItem>) {
        contentList.clear()
        contentList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = CardItemLinearBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item : ResponseGetItem = contentList[position]
        holder.bind(contentList[position])
        holder.itemView.setOnClickListener {
            onItemStoreClick(item)
        }
        holder.bind(item)
    }

    override fun getItemCount(): Int = contentList.size

    inner class ViewHolder(private val holder: CardItemLinearBinding) : RecyclerView.ViewHolder(holder.root) {

        fun bind(item: ResponseGetItem) {

            holder.tvTitle.text = item.item_name
            val formatter: NumberFormat = DecimalFormat("#,###")
            val formattedNumber: String = formatter.format(item.item_price)
            val result = concat("Rp ",formattedNumber)
            holder.tvPrice.text = result
            Glide.with(itemView.context).load(item.item_images1).into(holder.ivCover)
            holder.cardItem.setCardBackgroundColor(Color.parseColor(item.item_color));
        }

    }

}