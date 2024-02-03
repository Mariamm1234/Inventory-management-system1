package com.example.inventory1.Room.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.inventory1.Room.Location
import com.example.inventory1.Room.Products

data class LocationandProducts (
    @Embedded
    val location:Location,
    @Relation(parentColumn = "productLocationId",
        entityColumn = "productLocationId")
    val products:List<Products>
)