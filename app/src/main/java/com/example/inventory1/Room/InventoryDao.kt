package com.example.inventory1.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.inventory1.Room.relations.LocationandProducts
import com.example.inventory1.Room.relations.UnitsandProducts
import com.example.inventory1.Room.relations.WarehouseandProducts

@Dao
interface InventoryDao {
    @Query("SELECT*FROM Products")
    fun getAllProducts():LiveData<List<Products>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(product:Products)
    @Delete
    fun deleteProduct(product:Products)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun recentlyAddedItems(item:RecentlyAddedItem)
    @Delete
    fun deleteItemRecentlyAdded(item:Products)
    @Query("SELECT*FROM recentlyaddeditem")
    fun showRecentItems():LiveData<List<RecentlyAddedItem>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun recentlyCompletedItems(item:RecentlyCompletedItem)
    @Delete
    fun deleteItemCompleted(item:RecentlyCompletedItem)
    @Query("SELECT*FROM recentlycompleteditem")
    fun showCompletedItem():LiveData<List<RecentlyCompletedItem>>
    //edited
    @Query("SELECT *FROM Products WHERE productID=:parcode")
    fun getProduct(parcode:String):Products
    @Query("SELECT*FROM Units WHERE unitName=:unitName")
    //عايز ريسيكلير جديد
    fun getUnitsandProducts(unitName:String):LiveData<List<UnitsandProducts>>
    @Query("SELECT*FROM Location WHERE productLocationId=:locationId")
    fun getLocationandProductsById(locationId:String):LiveData<List<LocationandProducts>>
    @Query("SELECT*FROM Location WHERE lane=:lane and shelf=:shelf")
    //عايزه 2 تيكست بوكس
    fun getLocationandProductsByLaneandshelf(lane:String,shelf:String):LiveData<List<LocationandProducts>>
//    @Query("SELECT*FROM Location WHERE warehouseID=:warehouseId")
//    fun getLocationandProductsBywarehouse(warehouseId:String):LiveData<List<LocationandProducts>>
    @Query("UPDATE Products SET productQuantity=:quantity WHERE productID=:id")
    fun updateProductQuantity(id:String,quantity:Int)
  @Update
    fun updateProduct(product: Products)
    @Query("SELECT*FROM Location WHERE lane=:lane and shelf=:shelf")
    fun getLocationByLaneAndShelf(lane:String,shelf: String):LiveData<List<Location>>
@Query("SELECT*FROM WarehouseandProducts WHERE ID=:warehouseId")
fun getProductsByWarehouse(warehouseId:String):LiveData<List<WarehouseandProducts>>
@Update
fun updateLocation(location:Location)
@Insert(onConflict = OnConflictStrategy.REPLACE)
fun addUnit(unit:Units)
@Query("SELECT*FROM Location WHERE productLocationId=:productLocationId")
fun getProductLocation(productLocationId:Int):LiveData<Location>
@Query("SELECT*FROM Units")
fun getAllUnits():LiveData<List<Units>>
}