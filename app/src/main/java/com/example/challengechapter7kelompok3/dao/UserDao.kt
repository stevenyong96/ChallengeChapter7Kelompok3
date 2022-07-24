package com.example.challengechapter7kelompok3.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.challengechapter7kelompok3.entity.Users

@Dao
interface UserDao {

    @Query("SELECT * FROM Users")
    fun getAllUser() : List<Users>

    @Insert(onConflict = REPLACE)
    fun insertUser(user: Users) : Long

    @Update
    fun updateUser(user: Users) : Int ?= 0

    @Delete
    fun deleteUser(user: Users) : Int ? = 0

    @Query("SELECT count(*) FROM Users WHERE username = :username " + "and password = :password LIMIT 1")
    fun checkUser(username: String, password: String): Int

    @Query("INSERT INTO Users(username,password,nama,email) VALUES(:username,:password,:nama,:email)")
    fun insUser(username: String?= "", password: String?= "", nama:String ?= "", email:String ?= ""): Long
}