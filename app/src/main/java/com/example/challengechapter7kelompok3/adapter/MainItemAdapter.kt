package com.example.challengechapter7kelompok3.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengechapter7kelompok3.databinding.CardItemLinearBinding
import com.example.challengechapter7kelompok3.model.DataMain
import com.example.challengechapter7kelompok3.model.ResponseGetItem
import java.text.DecimalFormat
import java.text.NumberFormat

fun concat(s1: String, s2: String): String {
    return s1 + s2
}

//class MainItemAdapter( val onItemStoreClick: (ResponseItem) -> Unit) : RecyclerView.Adapter<MainItemAdapter.ViewHolder>() {

class MainItemAdapter(private val listItem: ArrayList<DataMain>, val onItemStoreClick: (DataMain) -> Unit) : RecyclerView.Adapter<MainItemAdapter.ViewHolder>() {
        private val contentList =  mutableListOf<ResponseGetItem>()

        open fun addContentList(newList: MutableList<ResponseGetItem>) {
            contentList.clear()
            contentList.addAll(newList)
            notifyDataSetChanged()
        }

        class ViewHolder(private val dataMainBinding : CardItemLinearBinding) : RecyclerView.ViewHolder(dataMainBinding.root) {
            fun bind(dataMain : DataMain) {
                //step 4 implement recyclerview, bind data ke view
                dataMainBinding.tvTitle.text = dataMain.itemDesc
                val formatter: NumberFormat = DecimalFormat("#,###")
                val formattedNumber: String = formatter.format(dataMain.itemPrice)
                val result = concat("Rp ",formattedNumber)
                dataMainBinding.tvPrice.text = result
                Glide.with(itemView.context).load(dataMain.itemImage).into(dataMainBinding.ivCover)
                dataMainBinding.cardItem.setCardBackgroundColor(Color.parseColor(dataMain.itemColor));
            }

//            fun bind(item: ResponseItem) {
//
//                dataMainBinding.tvTitle.text = item.item_name
//                val formatter: NumberFormat = DecimalFormat("#,###")
//                val formattedNumber: String = formatter.format(item.item_price)
//                val result = concat("Rp ",formattedNumber)
//                dataMainBinding.tvPrice.text = result
//                Glide.with(itemView.context).load(item.item_images1).into(dataMainBinding.ivCover)
//                dataMainBinding.cardItem.setCardBackgroundColor(Color.parseColor(item.item_color))
//            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = CardItemLinearBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            val item : ResponseItem = contentList[position]

            val item : DataMain = listItem[position]

//            val descString = item.itemDesc
//            val priceInt = item.itemPrice
//            val formatter: NumberFormat = DecimalFormat("#,###")
//            val formattedNumber: String = formatter.format(item.itemPrice)
//            val result = concat("Rp ",formattedNumber)
//            val priceString = result
//            val imageString = item.itemImage
//            val colorString = item.itemColor
//
//            val toPass = Bundle()
//            toPass.putString("itemDesc", descString)
//            toPass.putInt("itemPrice", priceInt)
//            toPass.putString("itemPriceString", priceString)
//            toPass.putString("itemImage", imageString)
//            toPass.putString("itemColor", colorString)

            holder.itemView.setOnClickListener {
                onItemStoreClick(item)
            }
            holder.bind(item)
        }

        override fun getItemCount(): Int {
            return listItem.size
//              return contentList.size
        }
    }
