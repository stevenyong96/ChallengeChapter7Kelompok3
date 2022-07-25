package com.example.challengechapter7kelompok3

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.challengechapter7kelompok3.adapter.CheckoutPaymentAdapter


class AddCheckoutPaymentActivity : AppCompatActivity() {

        lateinit var viewPager: ViewPager
        lateinit var viewPagerAdapter: CheckoutPaymentAdapter
        lateinit var imageList: List<Int>


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            supportActionBar?.hide()

            viewPager = findViewById(R.id.idViewPager)

            imageList = ArrayList<Int>()
            imageList = imageList + R.drawable.creditcard
            imageList = imageList + R.drawable.paypal
            imageList = imageList + R.drawable.paypal_paylater

            viewPagerAdapter = CheckoutPaymentAdapter(this@AddCheckoutPaymentActivity, imageList)

            viewPager.adapter = viewPagerAdapter

        }

    }


