package com.example.inventory1.Room.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.inventory1.Room.Products
import com.example.inventory1.Room.Units

data class UnitsandProducts (
    @Embedded
    val unit:Units,
    @Relation(parentColumn = "unitName", entityColumn = "unitName")
    val products:List<Products>
)