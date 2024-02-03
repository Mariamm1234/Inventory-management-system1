package com.example.inventory1.Room

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Location (
    @PrimaryKey(autoGenerate = true)
    val ID:Int,
    val productLocationId:Int,
    var lane:String,
    var shelf:String,
    val warehouseID:String
)