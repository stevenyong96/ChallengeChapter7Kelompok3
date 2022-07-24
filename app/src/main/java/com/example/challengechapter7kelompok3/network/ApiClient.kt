package com.example.challengechapter7kelompok3.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {

    //    pastiin base url di akhiri tanda / (slash) "
    private const val BASE_URL = "https://fastapi-binar.herokuapp.com/"

    val instance : ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(ApiService::class.java)
    }

    private val inteceptor : HttpLoggingInterceptor
        get() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }


    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(inteceptor)
        .build()

}