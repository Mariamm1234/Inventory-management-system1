package com.example.inventory1.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Warehouse(
    @PrimaryKey(autoGenerate = true)
    val ID:Int,
    val numOfPieces:Int,
    val numOfProducts:Int
)
