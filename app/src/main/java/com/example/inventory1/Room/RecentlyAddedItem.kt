package com.example.inventory1.Room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
@Entity
data class RecentlyAddedItem (
    val productName:String,
    @PrimaryKey(autoGenerate = false)
    val productID:String,
    val productLocation:Int,
    val productAmount:String,
    val productType:String,
    val productCompany:String,
    val productQuantity:Int,
    val addTime:String
)