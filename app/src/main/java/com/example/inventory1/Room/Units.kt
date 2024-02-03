package com.example.inventory1.Room

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Units (
    @PrimaryKey(autoGenerate = false)
    val unitName:String,
    val unitAmount:String
)