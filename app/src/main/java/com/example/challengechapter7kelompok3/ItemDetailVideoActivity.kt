package com.example.challengechapter7kelompok3


import android.content.Intent
import android.net.Uri
import android.os.Binder
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.MediaController
import com.example.challengechapter7kelompok3.databinding.ActivityItemDetailVideoBinding

class ItemDetailVideoActivity: AppCompatActivity() {
    private lateinit var binding : ActivityItemDetailVideoBinding

//    private val URL = "https://raw.githubusercontent.com/stevenyong96/fastapi-project/master/video/adidas_nmd.mp4"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailVideoBinding.inflate(layoutInflater)

        setContentView(binding.root)



        val itemName = intent.getStringExtra("KEY_ITEM_NAME")
        val itemPrice = intent.getStringExtra("KEY_ITEM_PRICE")
        val itemColor = intent.getStringExtra("KEY_ITEM_COLOR")
        var itemImage = intent.getStringExtra("KEY_ITEM_IMAGE")
        val tempUsername = intent.getStringExtra("DATA_USER_USERNAME")

        // Set up the media controller widget and attach it to the video view.
        val controller = MediaController(this)
        controller.setMediaPlayer(binding.videoView)
        binding.videoView.setMediaController(controller)

        binding.ivBack.setOnClickListener {
            var intentToItemDetail = Intent(this,ItemDetailActivity::class.java)
            intentToItemDetail.putExtra("KEY_ITEM_NAME", itemName)
            intentToItemDetail.putExtra("KEY_ITEM_PRICE", itemPrice)
            intentToItemDetail.putExtra("KEY_ITEM_COLOR", itemColor)
            intentToItemDetail.putExtra("KEY_ITEM_IMAGE", itemImage)
            intentToItemDetail.putExtra("DATA_USER_USERNAME",tempUsername)
            startActivity(intentToItemDetail)
        }

    }


    override fun onStart() {
        super.onStart()

        initializePlayer()
    }

    private fun initializePlayer() {

        binding.loading.visibility = View.VISIBLE



        val path = "android.resource://$packageName/${R.raw.adidas_nmd}"
        //        val videoUri = Uri.parse(URL)
        val videoUri = Uri.parse(path)


        binding.videoView.setVideoURI(videoUri)

        binding.videoView.setOnPreparedListener {

            binding.loading.visibility = View.GONE

            binding.videoView.seekTo(1)

            binding.videoView.start()
        }


    }
}