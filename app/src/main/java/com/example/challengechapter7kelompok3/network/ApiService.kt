package com.example.challengechapter7kelompok3.network

import com.example.challengechapter7kelompok3.model.ResponseInsUser
import com.example.challengechapter7kelompok3.model.ResponseGetItem
import com.example.challengechapter7kelompok3.model.ResponseLogin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {

    @POST("/login/{p_username}/{p_password}")
    open fun login(@Path("p_username",encoded=true) p_username: String?, @Path("p_password",encoded=true) p_password: String?): Call<ResponseLogin>

    @POST("/users/add/{p_username}/{p_password}/{p_nama}/{p_email}")
    open fun insUsers(@Path("p_username",encoded=true) p_username: String?, @Path("p_password",encoded=true) p_password: String?,@Path("p_nama",encoded=true) p_nama: String?,@Path("p_email",encoded=true) p_email: String?): Call<ResponseInsUser>

    @GET("/list_items")
    open fun getAllItemList() : Call<List<ResponseGetItem>>

    @GET("/items/search/{p_search}")
    open fun getSearchItemList(@Path("p_search",encoded=true) p_search: String?) : Call<List<ResponseGetItem>>

}