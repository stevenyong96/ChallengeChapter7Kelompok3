package com.example.challengechapter7kelompok3.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.challengechapter7kelompok3.ItemDetailActivity
import com.example.challengechapter7kelompok3.R

class ItemDetailPage3Fragment : Fragment() , ItemDetailActivity.OnSendDataToFragment{
    lateinit var ivItem : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        (activity as ItemDetailActivity).listener = this

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_detail_page3, container, false)
        ivItem = view.findViewById(R.id.iv_image)
        val bundle = arguments
        val message = bundle!!.getString("message")
        Glide.with(this).load(message).into(ivItem)
        return view
    }

    override fun onDataSend(input: String) {
        Glide.with(this).load(input).into(ivItem)
    }
}
