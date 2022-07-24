package com.example.challengechapter7kelompok3

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengechapter7kelompok3.adapter.CartItemAdapter
import com.example.challengechapter7kelompok3.database.CartDatabase
import com.example.challengechapter7kelompok3.databinding.ActivityCartTokpeeBinding
import com.example.challengechapter7kelompok3.model.DataCart
import com.example.challengechapter7kelompok3.viewModel.CartTokpeeViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.NumberFormat


class CartTokpeeActivity : AppCompatActivity(){
    private lateinit var bindingCart : ActivityCartTokpeeBinding
    private lateinit var viewModel: CartTokpeeViewModel
    var dataBase : CartDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingCart = ActivityCartTokpeeBinding.inflate(layoutInflater)
        setContentView(bindingCart.root)
        dataBase = CartDatabase.getInstance(this)
        val tempUsername = intent.getStringExtra("DATA_USER_USERNAME")

        setupComponent()

//        bindingCart.rvListCart.adapter = CartItemAdapter(getDummyData()) {
//            Toast.makeText(this, "Item di click : ${it.itemDesc}", Toast.LENGTH_SHORT).show()
//        }

        bindingCart.rvListCart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        fetchData()

        bindingCart.ivBackBtn.setOnClickListener {
            val intentToHome = Intent(this, MainActivity::class.java)
            startActivity(intentToHome)
        }

        bindingCart.srlStudent.setOnRefreshListener {
            fetchData()
        }

        bindingCart.buttonCheckout.setOnClickListener {
            var tempTotalItem = bindingCart.tvQty.text.toString()
            var tempTotalPayment = bindingCart.tvItemPrice.text.toString()
            var intentToCheckout = Intent(this,CheckoutPaymentActivity::class.java)
            intentToCheckout.putExtra("DATA_USER_USERNAME",tempUsername)
            intentToCheckout.putExtra("DATA_TOTAL_ITEM", tempTotalItem)
            intentToCheckout.putExtra("DATA_TOTAL_PAYMENT", tempTotalPayment)
            startActivity(intentToCheckout)
        }

    }

//    private fun showSkeleton() {
//        bindingCart.rvListCart.visibility = View.GONE
//        bindingCart.skeleton.visibility = View.VISIBLE
//        bindingCart.skeleton.showSkeleton()
//
//    }

//    private fun hideSkeleton() {
//        bindingCart.rvListCart.visibility = View.VISIBLE
//        bindingCart.skeleton.visibility = View.GONE
//        bindingCart.skeleton.showOriginal()
//    }


    private fun getDummyData() : ArrayList<DataCart> {
        return arrayListOf(
            DataCart("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_backpack.png", "Adidas Raptor", 750000,"#FF233D"),
            DataCart("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_nmd.png", "Adidas NMD1", 2850000,"#F5CA7B"),
            DataCart("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/nike_jacket.png", "Nike Jacket", 300000,"#EEEE9B"),
            DataCart("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_backpack.png", "Backpack", 950000,"#98fb98"),
        )
    }

    fun fetchData(){
//        if(!bindingCart.srlStudent.isRefreshing) showSkeleton()

//        val handler = Handler()
//        handler.postDelayed({
//
//            binding.cpiLoading.hide()
//

//
//            hideSkeleton()

            GlobalScope.launch {
                val listCart = dataBase?.cartDao()?.getAllCart()

                runOnUiThread{
                    listCart?.let {
                        val adapter = CartItemAdapter(it)
                        bindingCart.rvListCart.adapter = adapter
                    }
                }
            }

            bindingCart.srlStudent.isRefreshing = false
            getTotalItem()

//        }, 2000)

    }

    fun getTotalItem(){
        GlobalScope.launch {
            val resTotalItem = dataBase?.cartDao()?.getTotalItem()
            val resTotalPayment = dataBase?.cartDao()?.getTotalPayment()
            runOnUiThread{
                var satuan = " Items"
                var resultQty = concat(resTotalItem.toString(),satuan)
                val formatter: NumberFormat = DecimalFormat("#,###")
                val formattedNumber: String = formatter.format(resTotalPayment)
                val resultPayment = concat("Rp ", formattedNumber)
                bindingCart.tvQty.setText(resultQty)
                bindingCart.tvItemPrice.setText(resultPayment)
            }
        }
    }

    fun concat(s1: String, s2: String): String {
        return s1 + s2
    }

    private fun setupComponent() {
        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(CartTokpeeViewModel::class.java)
    }
}