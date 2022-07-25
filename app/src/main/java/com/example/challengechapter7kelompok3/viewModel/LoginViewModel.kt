package com.example.challengechapter7kelompok3.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengechapter7kelompok3.helper.Event
import com.example.challengechapter7kelompok3.model.ResponseLogin
import com.example.challengechapter7kelompok3.network.ApiClient
import com.example.challengechapter7kelompok3.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    val responseData : MutableLiveData<Event<String>> = MutableLiveData()
    val responseDataError : MutableLiveData<Event<String>> = MutableLiveData()

    fun doLogin(p_username: String,p_password : String) {

        ApiClient.instance.login(p_username,p_password).enqueue(object : Callback<ResponseLogin> {
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    if (response.isSuccessful) {
                        var dataTemp = response.body()
                        if (dataTemp?.status == "SUCCESS") {
                            responseData.postValue(Event("sukses Login"))
                        } else {
                            responseDataError.postValue(Event(dataTemp?.data.toString()))
                        }
                    }
                    else
                        responseDataError.postValue(Event("Failed To Login "))
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    responseDataError.postValue(Event("Something Went Wrong With API Login"))
                }

            })

    }
}