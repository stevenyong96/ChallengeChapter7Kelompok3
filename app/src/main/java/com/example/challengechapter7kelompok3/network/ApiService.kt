package com.example.challengechapter7kelompok3.network

import com.example.challengechapter7kelompok3.model.ResponseLogin
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {

    @POST("/login/{p_username}/{p_password}")
    open fun login(@Path("p_username",encoded=true) p_username: String?, @Path("p_password",encoded=true) p_password: String?): Call<ResponseLogin>

}