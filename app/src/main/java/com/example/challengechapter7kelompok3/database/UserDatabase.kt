package com.example.challengechapter7kelompok3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.challengechapter7kelompok3.dao.UserDao
import com.example.challengechapter7kelompok3.entity.Users


@Database(entities = [Users::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract  fun  userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE : UserDatabase? = null

        fun getInstance(context: Context): UserDatabase? {
            if(INSTANCE == null) {
                synchronized(UserDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        UserDatabase::class.java, "Users.db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}