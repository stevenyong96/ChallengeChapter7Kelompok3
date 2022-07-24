package com.example.challengechapter7kelompok3.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Entity
import com.example.challengechapter7kelompok3.database.UserDatabase
import com.example.challengechapter7kelompok3.entity.Users
import kotlinx.coroutines.launch

class UserViewModel(private val userDatabase: UserDatabase) : ViewModel() {

    val notes = userDatabase.userDao().getAllUser()

    fun insertNote(userEntity: Users){
        viewModelScope.launch{
            userDatabase.userDao().insertUser(userEntity)
        }
    }
}