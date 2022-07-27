package com.example.challengechapter7kelompok3.model

import com.google.gson.annotations.SerializedName

data class ResponseGetItem(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: String,
    @SerializedName("item_code")
    val item_code: String,
    @SerializedName("item_name")
    val item_name: String,
    @SerializedName("item_desc")
    val item_desc: String,
    @SerializedName("item_price")
    val item_price: Number,
    @SerializedName("item_stock")
    val item_stock: Number,
    @SerializedName("item_color")
    val item_color: String,
    @SerializedName("item_images1")
    val item_images1: String,
    @SerializedName("item_images2")
    val item_images2: String,
    @SerializedName("item_images3")
    val item_images3: String
    )