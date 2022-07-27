package com.example.challengechapter7kelompok3.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengechapter7kelompok3.helper.Event
import com.example.challengechapter7kelompok3.model.ResponseGetItem
import com.example.challengechapter7kelompok3.model.ResponseLogin
import com.example.challengechapter7kelompok3.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    val contentItem : MutableLiveData<List<ResponseGetItem>> = MutableLiveData()

    val errorMessage : MutableLiveData<Event<String>> = MutableLiveData()

    fun getContentList() {
        ApiClient.instance.getAllItemList().enqueue(object : Callback<List<ResponseGetItem>> {

            override fun onResponse(
                call: Call<List<ResponseGetItem>>,
                response: Response<List<ResponseGetItem>>
            ) {
                if(response.isSuccessful) {
                        val body = response.body()
                        body?.let {
                            contentItem.postValue(it.toMutableList())
                        }
                } else errorMessage.postValue(Event("Gagal mengambil data item"))
            }

            override fun onFailure(call: Call<List<ResponseGetItem>>, t: Throwable) {
                errorMessage.postValue(Event("Gagal mengambil data list item"))
            }

        })


    }


    fun getSearchContentList(p_search : String) {
        ApiClient.instance.getSearchItemList(p_search).enqueue(object : Callback<List<ResponseGetItem>> {

            override fun onResponse(
                call: Call<List<ResponseGetItem>>,
                response: Response<List<ResponseGetItem>>
            ) {
                if(response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        contentItem.postValue(it.toMutableList())
                    }
                } else errorMessage.postValue(Event("Gagal mengambil data item"))
            }

            override fun onFailure(call: Call<List<ResponseGetItem>>, t: Throwable) {
                errorMessage.postValue(Event("Gagal mengambil data list item"))
            }

        })


    }

}