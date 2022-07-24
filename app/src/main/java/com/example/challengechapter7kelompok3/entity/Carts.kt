package com.example.challengechapter7kelompok3.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Carts",indices = [androidx.room.Index(value = ["item_name"])])
@Parcelize
data class Carts(
    @ColumnInfo(name = "item_name") var item_name: String?= "",
    @ColumnInfo(name = "item_desc") var item_desc: String? = "",
    @ColumnInfo(name = "item_price") var item_price: Int?=0,
    @ColumnInfo(name = "item_color") var item_color: String?= "",
    @ColumnInfo(name = "item_image") var item_image: String?= "",
    @ColumnInfo(name = "item_qty") var item_qty: Int? = 0,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
) : Parcelable