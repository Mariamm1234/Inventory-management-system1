package com.example.inventory1.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inventory1.Room.relations.WarehouseandProducts

@Database(entities = [Location::class,
Products::class,
RecentlyAddedItem::class,
RecentlyCompletedItem::class,
Units::class,
    WarehouseandProducts::class,
Warehouse::class
], version = 8)
abstract  class AppDatabase :RoomDatabase() {
    abstract fun inventoryDao():InventoryDao
    companion object{
        val appDatabaseName="AppDatabase"
        @Volatile private var instance:AppDatabase?=null
        val LOCK=Any()
        operator fun invoke(context: Context)= instance?: synchronized(LOCK){
            instance?:buildDatabase(context).also{ instance=it}
        }

        private fun buildDatabase(context: Context)= Room.databaseBuilder(context,AppDatabase::class.java,
            appDatabaseName)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()


    }
}