package com.example.challengechapter7kelompok3.model

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: String
)