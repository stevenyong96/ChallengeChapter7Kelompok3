package com.example.challengechapter7kelompok3.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengechapter7kelompok3.CartTokpeeActivity
import com.example.challengechapter7kelompok3.R
import com.example.challengechapter7kelompok3.database.CartDatabase
import com.example.challengechapter7kelompok3.databinding.ActivityCartTokpeeBinding
import com.example.challengechapter7kelompok3.databinding.CardItemCartBinding
import com.example.challengechapter7kelompok3.entity.Carts
import com.example.challengechapter7kelompok3.model.DataCart
import com.example.challengechapter7kelompok3.model.DataMain
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.security.AccessController.getContext
import java.text.DecimalFormat
import java.text.NumberFormat

fun concatString(s1: String, s2: String): String {
    return s1 + s2
}

class CartItemAdapter(private val listItem: List<Carts>) : RecyclerView.Adapter<CartItemAdapter.ViewHolderCart>() {


        class ViewHolderCart(private val dataCartBinding : CardItemCartBinding , private val dataActivityCartTokpeeBinding: ActivityCartTokpeeBinding) : RecyclerView.ViewHolder(dataCartBinding.root) {
            @SuppressLint("Range")
            fun bind(cart : Carts , item: Carts) {

                //step 4 implement recyclerview, bind data ke view
                dataCartBinding.tvItemDesc.text = cart.item_name
                val formatter: NumberFormat = DecimalFormat("#,###")
                val formattedNumber: String = formatter.format(cart.item_price)
                val result = concat("Rp ",formattedNumber)
                dataCartBinding.tvItemPrice.text = result
                Glide.with(itemView.context).load(cart.item_image).into(dataCartBinding.ivCover)
                dataCartBinding.clCartItem.setBackgroundColor(Color.parseColor(cart.item_color));
                dataCartBinding.etQty.setText(cart.item_qty.toString())

                dataCartBinding.ivMinus.setOnClickListener {
                    val database = CartDatabase.getInstance(it.context as CartTokpeeActivity)
                    var cur_qty = dataCartBinding.etQty.text.toString()
                    if(cur_qty.toInt() > 0){
                        var tempQty = cur_qty.toInt()-1
                        var tempItemName = dataCartBinding.tvItemDesc.text.toString()

                        GlobalScope.launch {
                            val resUpdQty = database?.cartDao()?.updateQtyItem(tempItemName,tempQty)
                            (dataCartBinding.root.context as CartTokpeeActivity).runOnUiThread{
                                if(resUpdQty != 0.toInt() && resUpdQty != -1.toInt()){
                                    dataCartBinding.etQty.setText(tempQty.toString())
                                    GlobalScope.launch {
                                        val resTotalItem = database?.cartDao()?.getTotalItem()
                                        val resTotalPayment = database?.cartDao()?.getTotalPayment()
                                        (dataCartBinding.root.context as CartTokpeeActivity).runOnUiThread{
                                            var satuan = " Items"
                                            var resultQty = concat(resTotalItem.toString(),satuan)
                                            val formatter: NumberFormat = DecimalFormat("#,###")
                                            val formattedNumber: String = formatter.format(resTotalPayment)
                                            val resultPayment = concat("Rp ", formattedNumber)
                                            dataActivityCartTokpeeBinding.tvQty.setText(resultQty)
                                            dataActivityCartTokpeeBinding.tvItemPrice.setText(resultPayment)
                                        }
                                        (dataActivityCartTokpeeBinding.root.context as CartTokpeeActivity).fetchData()
                                    }
                                }
                            }
                        }
                    }
                }

                dataCartBinding.ivPlus.setOnClickListener {
                    val database = CartDatabase.getInstance(it.context as CartTokpeeActivity)
                    var cur_qty_plus = dataCartBinding.etQty.text.toString()
                    if(cur_qty_plus.toInt() <= 10000000000){
                        var tempQty = cur_qty_plus.toInt()+1
                        var tempItemName = dataCartBinding.tvItemDesc.text.toString()

                        GlobalScope.launch {
                            val resUpdQty = database?.cartDao()?.updateQtyItem(tempItemName,tempQty)
                            (dataCartBinding.root.context as CartTokpeeActivity).runOnUiThread{
                                if(resUpdQty != 0.toInt() && resUpdQty != -1.toInt()){
                                    dataCartBinding.etQty.setText(tempQty.toString())
                                    GlobalScope.launch {
                                        val resTotalItem = database?.cartDao()?.getTotalItem()
                                        val resTotalPayment = database?.cartDao()?.getTotalPayment()
                                        (dataActivityCartTokpeeBinding.root.context as CartTokpeeActivity).runOnUiThread{
                                            var satuan = " Items"
                                            var resultQty = concat(resTotalItem.toString(),satuan)
                                            val formatter: NumberFormat = DecimalFormat("#,###")
                                            val formattedNumber: String = formatter.format(resTotalPayment)
                                            val resultPayment = concat("Rp ", formattedNumber)
                                            dataActivityCartTokpeeBinding.tvQty.setText(resultQty)
                                            dataActivityCartTokpeeBinding.tvItemPrice.setText(resultPayment)
                                        }
                                        (dataActivityCartTokpeeBinding.root.context as CartTokpeeActivity).fetchData()
                                    }
                                }
                            }
                        }
                    }
                }

                dataCartBinding.ivTrash.setOnClickListener {
                    val database = CartDatabase.getInstance(it.context as CartTokpeeActivity)
                    AlertDialog.Builder(dataActivityCartTokpeeBinding.root.context as CartTokpeeActivity)
                        .setPositiveButton("Ya") { p0, p1 ->

                            GlobalScope.launch {

                                val result = database?.cartDao()?.deleteCart(item)

                                (dataActivityCartTokpeeBinding.root.context as CartTokpeeActivity).runOnUiThread {
                                    if (result != 0) {
                                        Toast.makeText(
                                            it.context, "Data ${item.item_name} berhasil dihapus",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        //UPDATE TOTAL QTY & TOTAL PAYMENT
                                        GlobalScope.launch {
                                            val resTotalItem = database?.cartDao()?.getTotalItem()
                                            val resTotalPayment = database?.cartDao()?.getTotalPayment()
                                            (dataActivityCartTokpeeBinding.root.context as CartTokpeeActivity).runOnUiThread{
                                                var satuan = " Items"
                                                var resultQty = concat(resTotalItem.toString(),satuan)
                                                val formatter: NumberFormat = DecimalFormat("#,###")
                                                val formattedNumber: String = formatter.format(resTotalPayment)
                                                val resultPayment = concat("Rp ", formattedNumber)
                                                dataActivityCartTokpeeBinding.tvQty.setText(resultQty)
                                                dataActivityCartTokpeeBinding.tvItemPrice.setText(resultPayment)
                                            }
                                        }
                                    } else {
                                        Toast.makeText(
                                            it.context, "Data ${item.item_name} Gagal dihapus",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                                (dataActivityCartTokpeeBinding.root.context as CartTokpeeActivity).fetchData()
                            }
                        }.setNegativeButton("Tidak") { p0, p1 ->
                            p0.dismiss()
                        }.setMessage("Apakah Anda Yakin ingin menghapus data ${item.item_name}")
                        .setTitle("Konfirmasi Hapus").create()
                        .show()
                }





            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCart {
            val view = CardItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            val viewCart = ActivityCartTokpeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolderCart(view,viewCart)
        }

        override fun onBindViewHolder(holder: ViewHolderCart, position: Int) {
            val item : Carts = listItem[position]
            val onItemStoreClick: (DataMain) -> Unit
            holder.bind(item,item)
        }

        override fun getItemCount(): Int {
            return listItem.size
        }




    }
