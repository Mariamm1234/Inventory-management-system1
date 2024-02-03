package com.example.inventory1.Room

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Products (
    var productName:String,
    @PrimaryKey(autoGenerate = false)
    val productID:String,
    var productLocationId:Int,
    var unitName:String,
    var productType:String,
    var productCompany:String,
    var productQuantity:Int
)