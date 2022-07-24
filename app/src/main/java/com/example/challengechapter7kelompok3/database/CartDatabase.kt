package com.example.challengechapter7kelompok3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.challengechapter7kelompok3.dao.CartDao
import com.example.challengechapter7kelompok3.entity.Carts

@Database(entities = [Carts::class], version = 1)
abstract class CartDatabase : RoomDatabase() {

    abstract  fun  cartDao(): CartDao

    companion object{
        @Volatile
        private var INSTANCE : CartDatabase? = null

        fun getInstance(context: Context): CartDatabase? {
            if(INSTANCE == null) {
                synchronized(CartDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        CartDatabase::class.java, "Carts.db")
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