package com.example.inventory1.Room

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class RecentlyCompletedItem (
    val productName:String,
    @PrimaryKey(autoGenerate = false)
    val productID:String,
    val productLocation:String,
    val productAmount:String,
    val productQuantity:Int
)