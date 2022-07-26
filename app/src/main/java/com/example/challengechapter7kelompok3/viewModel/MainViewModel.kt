package com.example.challengechapter7kelompok3.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengechapter7kelompok3.helper.Event
import com.example.challengechapter7kelompok3.model.ResponseItem
import com.example.challengechapter7kelompok3.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    val contentItem : MutableLiveData<List<ResponseItem>> = MutableLiveData()

    val errorMessage : MutableLiveData<Event<String>> = MutableLiveData()

    fun getContentList() {
        ApiClient.instance.getAllItemList().enqueue(object : Callback<List<ResponseItem>> {

            override fun onResponse(
                call: Call<List<ResponseItem>>,
                response: Response<List<ResponseItem>>
            ) {
                if(response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        contentItem.postValue(it.toMutableList())
                    }
                } else errorMessage.postValue(Event("Gagal mengambil data item"))
            }

            override fun onFailure(call: Call<List<ResponseItem>>, t: Throwable) {
                errorMessage.postValue(Event("Gagal mengambil data"))
            }

        })


    }

}