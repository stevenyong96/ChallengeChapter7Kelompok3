package com.example.challengechapter7kelompok3

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.challengechapter7kelompok3.adapter.CheckoutPaymentAdapter
import com.example.challengechapter7kelompok3.database.CartDatabase
import com.example.challengechapter7kelompok3.databinding.ActivityCheckoutPaymentBinding
import com.example.challengechapter7kelompok3.entity.Users
import com.example.challengechapter7kelompok3.viewModel.CartTokpeeViewModel
import com.example.challengechapter7kelompok3.viewModel.CheckoutPaymentViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.NumberFormat

class CheckoutPaymentActivity : AppCompatActivity() {

    lateinit var binding: ActivityCheckoutPaymentBinding
    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: CheckoutPaymentAdapter
    lateinit var imageList: List<Int>
    private lateinit var viewModel: CheckoutPaymentViewModel
    private lateinit var btnBack : Button
    var database : CartDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCheckoutPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupComponent()

        val tempUsername = intent.getStringExtra("DATA_USER_USERNAME")
        val tempTotalItem = intent.getStringExtra("DATA_TOTAL_ITEM")
        val tempTotalPayment = intent.getStringExtra("DATA_TOTAL_PAYMENT")



        val intentToHome = Intent(this,MainActivity::class.java)
        database = CartDatabase.getInstance(this)

        setupComponent()

        binding.tvTotalPayment.setText(tempTotalPayment)
        binding.tvCustomer.setText(tempUsername)


        binding.ivBackBtn.setOnClickListener {
            val intentToHome = Intent(this,MainActivity::class.java)
            startActivity(intentToHome)
        }

        binding.tvAdd.setOnClickListener {
            val intentToAddCheckout = Intent(this,AddCheckoutPaymentActivity::class.java)
            intentToAddCheckout.putExtra("DATA_USER_USERNAME", tempUsername)
            intentToAddCheckout.putExtra("DATA_TOTAL_ITEM", tempTotalItem)
            intentToAddCheckout.putExtra("DATA_TOTAL_PAYMENT", tempTotalPayment)
            startActivity(intentToAddCheckout)
        }


        binding.btnCheckout.setOnClickListener {
            AlertDialog.Builder(this).setPositiveButton("Ya") { p0, p1 ->

                    GlobalScope.launch {

                        val result = database?.cartDao()?.deleteAllCart()

                        runOnUiThread {
                            if (result != 0) {
                                Toast.makeText(
                                    this@CheckoutPaymentActivity,
                                    "Payment sebesar ${tempTotalPayment} berhasil",
                                    Toast.LENGTH_SHORT
                                ).show()
                                intentToHome.putExtra("DATA_USER_USERNAME", tempUsername)
                                startActivity(intentToHome)
                            } else {
                                Toast.makeText(
                                    this@CheckoutPaymentActivity,
                                    "FAILED ,Payment sebesar ${tempTotalPayment} gagal",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }.setNegativeButton("Tidak") { p0, p1 ->
                    p0.dismiss()
                }.setMessage("Apakah Anda Yakin ingin membayar ${tempTotalItem} ,dengan total pembelanjaan sebesar ${tempTotalPayment}")
                .setTitle("Confirmation Payment").create()
                .show()
        }




        }


    private fun setupComponent() {
        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(CheckoutPaymentViewModel::class.java)
    }
}