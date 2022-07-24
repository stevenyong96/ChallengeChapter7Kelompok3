package com.example.challengechapter7kelompok3.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "Users")
@Parcelize
data class Users(
    @ColumnInfo(name = "username") var username: String?="",
    @ColumnInfo(name = "password") var password: String?= "",
    @ColumnInfo(name = "nama") var nama: String?= "",
    @ColumnInfo(name = "email") var email: String?="",
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
) : Parcelable