package com.example.challengechapter7kelompok3

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.challengechapter7kelompok3.database.CartDatabase
import com.example.challengechapter7kelompok3.database.UserDatabase
import com.example.challengechapter7kelompok3.databinding.ActivityItemDetailBinding
import com.example.challengechapter7kelompok3.entity.Carts
import com.example.challengechapter7kelompok3.entity.Users
import com.example.challengechapter7kelompok3.viewModel.ItemDetailViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.text.DecimalFormat
import java.text.NumberFormat

fun concat(s1: String, s2: String): String {
    return s1 + s2
}

class ItemDetailActivity : AppCompatActivity(){
    private lateinit var viewModel: ItemDetailViewModel
    var dataBase : CartDatabase? = null

    //soundpool
    private lateinit var binding : ActivityItemDetailBinding
    private val MAX_STREAMS = 1
    private lateinit var soundPool: SoundPool
    private var loaded = false
    private var soundId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataBase = CartDatabase.getInstance(this)

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME) // untuk merubah volume yang di gunakan pada soundpool, bisa alarm, ringtone atau media
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION) // merubah cara suara berinteraksi dan mengelompokkan
            .build()

        soundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .build()

        soundPool.setOnLoadCompleteListener { soundPool, i, i2 ->
            loaded = true // listen event saat suara sudah di load
        }

        soundId = soundPool.load(this, R.raw.sound2, 1)

        setupComponent()

        val itemName = intent.getStringExtra("KEY_ITEM_NAME")
        val itemPrice = intent.getStringExtra("KEY_ITEM_PRICE")
        val itemColor = intent.getStringExtra("KEY_ITEM_COLOR")
        val itemImage = intent.getStringExtra("KEY_ITEM_IMAGE")
        val tempUsername = intent.getStringExtra("DATA_USER_USERNAME")

        binding.tvItemName.setText(itemName)
        val formatter: NumberFormat = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(itemPrice?.toInt())
        val result = concat("Rp ", formattedNumber)

        binding.tvItemPrice.setText(result)
        binding.clParent.setBackgroundColor(Color.parseColor(itemColor))
        binding.clHdr.setBackgroundColor(Color.parseColor(itemColor))
        binding.buttonAddToCart.setBackgroundColor(Color.parseColor(itemColor))
        Glide.with(this).load(itemImage).into(binding.ivImage)

        binding.ivItemDetailBack.setOnClickListener {
            var intentToHome = Intent(this,MainActivity::class.java)
            intentToHome.putExtra("DATA_USER_USERNAME",tempUsername)
            startActivity(intentToHome)
        }

        binding.ivCartDetail.setOnClickListener {
            var intentToCart = Intent(this,CartTokpeeActivity::class.java)
            intentToCart.putExtra("DATA_USER_USERNAME",tempUsername)
            startActivity(intentToCart)
        }

        binding.buttonAddToCart.setOnClickListener {
            GlobalScope.async {
                val result = dataBase?.cartDao()?.checkCart(itemName)
                runOnUiThread {
                    if(result == 0.toLong()) {
                        insItemDetail(itemName,itemPrice,itemColor,itemImage)
                    }
                    else{
                        Toast.makeText(
                            this@ItemDetailActivity,
                            "Item ${itemName} sudah ada dalam cart sebelumnya",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            val serviceSystemManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
            val actualVolume = serviceSystemManager.getStreamVolume(AudioManager.STREAM_MUSIC).toFloat()
            val maxVolume = serviceSystemManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC).toFloat()
            val volume = actualVolume / maxVolume

            if(loaded)
                soundPool.play(soundId, volume, volume, 1,0, 1f)
            else
                Toast.makeText(this, "Soundpool belum di load", Toast.LENGTH_SHORT).show()

        }
    }

    fun insItemDetail(param_item_name:String ?= "",param_item_price: String ?="0",paramItemColor: String?="" , param_item_image: String ? =""){
        val cartEntity = Carts(param_item_name,"",param_item_price?.toInt(),paramItemColor,param_item_image,1)
        GlobalScope.async {
            val result = dataBase?.cartDao()?.insertCart(cartEntity)
            runOnUiThread {
                if(result != 0.toLong()) {
                    Toast.makeText(
                        this@ItemDetailActivity,
                        "Sukses menambahkan ${param_item_name}",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@ItemDetailActivity,
                        "Gagal menambahkan ${param_item_name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setupComponent() {
        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(ItemDetailViewModel::class.java)
    }

}