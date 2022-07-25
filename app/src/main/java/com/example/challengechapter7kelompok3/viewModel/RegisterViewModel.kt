package com.example.challengechapter7kelompok3.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengechapter7kelompok3.helper.Event
import com.example.challengechapter7kelompok3.model.ResponseInsUser
import com.example.challengechapter7kelompok3.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    val responseData : MutableLiveData<Event<String>> = MutableLiveData()
    val responseDataError : MutableLiveData<Event<String>> = MutableLiveData()

    fun doInsUser(p_username: String,p_password : String,p_nama : String,p_email : String) {

        ApiClient.instance.insUsers(p_username,p_password,p_nama,p_email).enqueue(object : Callback<ResponseInsUser> {
            override fun onResponse(
                call: Call<ResponseInsUser>,
                response: Response<ResponseInsUser>
            ) {
                if (response.isSuccessful) {
                    var dataTemp = response.body()
                    if (dataTemp?.status == "SUCCESS") {
                        responseData.postValue(Event("Register User Success"))
                    } else {
                        responseDataError.postValue(Event(dataTemp?.data.toString()))
                    }
                }
                else
                    responseDataError.postValue(Event("Failed To Register"))
            }

            override fun onFailure(call: Call<ResponseInsUser>, t: Throwable) {
                responseDataError.postValue(Event("Something Went Wrong"))
            }

        })

    }
}