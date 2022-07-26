package com.example.challengechapter7kelompok3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.challengechapter7kelompok3.adapter.CheckoutPaymentAdapter
import com.example.challengechapter7kelompok3.databinding.ActivityAddCheckoutPaymentBinding
import com.example.challengechapter7kelompok3.databinding.ActivityLoginBinding


class AddCheckoutPaymentActivity : AppCompatActivity() {

        lateinit var viewPager: ViewPager
        lateinit var viewPagerAdapter: CheckoutPaymentAdapter
        lateinit var imageList: List<Int>
        private lateinit var binding : ActivityAddCheckoutPaymentBinding


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityAddCheckoutPaymentBinding.inflate(layoutInflater)
            setContentView(binding.root)

            viewPager = binding.idViewPager

            val tempUsername = intent.getStringExtra("DATA_USER_USERNAME")
            val tempTotalItem = intent.getStringExtra("DATA_TOTAL_ITEM")
            val tempTotalPayment = intent.getStringExtra("DATA_TOTAL_PAYMENT")

            imageList = ArrayList<Int>()
            imageList = imageList + R.drawable.creditcard
            imageList = imageList + R.drawable.paypal
            imageList = imageList + R.drawable.paypal_paylater

            viewPagerAdapter = CheckoutPaymentAdapter(this@AddCheckoutPaymentActivity, imageList)

            viewPager.adapter = viewPagerAdapter


            binding.ivBack.setOnClickListener {
                var intentToCheckout= Intent(this,CheckoutPaymentActivity::class.java)
                intentToCheckout.putExtra("DATA_USER_USERNAME", tempUsername)
                intentToCheckout.putExtra("DATA_TOTAL_ITEM", tempTotalItem)
                intentToCheckout.putExtra("DATA_TOTAL_PAYMENT", tempTotalPayment)

                startActivity(intentToCheckout)
            }

        }

    }


