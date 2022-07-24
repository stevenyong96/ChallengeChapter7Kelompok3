package com.example.challengechapter7kelompok3.dao

import androidx.room.*
import com.example.challengechapter7kelompok3.entity.Carts

@Dao
interface CartDao {

    @Query("SELECT * FROM Carts")
    fun getAllCart() : List<Carts>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCart(cart: Carts) : Long

    @Update
    fun updateCart(cart: Carts) : Int

    @Delete
    fun deleteCart(cart: Carts) : Int

    @Query("SELECT count(*) FROM Carts WHERE item_name = :par_item_name LIMIT 1")
    fun checkCart(par_item_name: String? = ""): Long

    @Query("SELECT count(*) FROM Carts")
    fun getTotalItem(): Long

    @Query("SELECT sum(item_price * item_qty) FROM Carts")
    fun getTotalPayment(): Long

    @Query("UPDATE Carts SET item_qty = :par_item_qty WHERE item_name = :par_item_name")
    fun updateQtyItem(par_item_name : String ? = "" , par_item_qty : Int ?=0): Int

    @Query("DELETE FROM Carts")
    fun deleteAllCart(): Int

}