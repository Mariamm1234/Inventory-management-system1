package com.example.inventory1.Room.relations

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.inventory1.Room.Products
import com.example.inventory1.Room.Warehouse

@Entity(
    primaryKeys = ["ID", "productID"],
    foreignKeys = [
        ForeignKey(
            entity = Warehouse::class,
            parentColumns = ["ID"],
            childColumns = ["ID"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Products::class,
            parentColumns = ["productID"],
            childColumns = ["productID"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class WarehouseandProducts(
    val ID:Int,
    val productID:String
)
