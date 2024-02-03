package com.example.inventory1.MVVM

import androidx.lifecycle.LiveData
import com.example.inventory1.Room.InventoryDao
import com.example.inventory1.Room.Location
import com.example.inventory1.Room.Products
import com.example.inventory1.Room.RecentlyAddedItem
import com.example.inventory1.Room.RecentlyCompletedItem
import com.example.inventory1.Room.Units
import com.example.inventory1.Room.relations.LocationandProducts
import com.example.inventory1.Room.relations.UnitsandProducts
import com.example.inventory1.Room.relations.WarehouseandProducts

class InventoryReposatory(private val inventoryDao:InventoryDao) {
    fun getAllProducts(): LiveData<List<Products>>{
        return inventoryDao.getAllProducts()
    }
    fun addProduct(product:Products){
        inventoryDao.addProduct(product)
    }
    fun deleteProduct(product:Products){
        inventoryDao.deleteProduct(product)
    }
    fun recentlyAddedItems(item: RecentlyAddedItem){
        inventoryDao.recentlyAddedItems(item)
    }
    fun deleteItemRecentlyAdded(item:Products){
        inventoryDao.deleteItemRecentlyAdded(item)
    }
    fun showRecentItems():LiveData<List<RecentlyAddedItem>>{
        return inventoryDao.showRecentItems()
    }
    fun recentlyCompletedItems(item: RecentlyCompletedItem){
        inventoryDao.recentlyCompletedItems(item)
    }
    fun deleteItemCompleted(item:RecentlyCompletedItem){
        inventoryDao.deleteItemCompleted(item)
    }
    fun showCompletedItem():LiveData<List<RecentlyCompletedItem>>{
        return inventoryDao.showCompletedItem()
    }
    //edited
    fun getProduct(parcode:String):Products{
        return inventoryDao.getProduct(parcode)
    }
    fun getUnitsandProducts(unitName:String):LiveData<List<UnitsandProducts>>{
        return inventoryDao.getUnitsandProducts(unitName)
    }
    fun getLocationandProductsById(locationId:String):LiveData<List<LocationandProducts>>{
        return inventoryDao.getLocationandProductsById(locationId)
    }
    fun getLocationandProductsByLaneandshelf(lane:String,shelf:String):LiveData<List<LocationandProducts>>{
        return inventoryDao.getLocationandProductsByLaneandshelf(lane,shelf)
    }
//    fun getLocationandProductsBywarehouse(warehouseId:String):LiveData<List<LocationandProducts>>{
//        return inventoryDao.getLocationandProductsBywarehouse(warehouseId)
//    }
    fun updateProductQuantity(id:String,quantity:Int){
        inventoryDao.updateProductQuantity(id,quantity)
    }
    fun updateProduct(product: Products){
        inventoryDao.updateProduct((product))
    }
    fun getLocationByLaneAndShelf(lane: String,shelf: String):LiveData<List<Location>>{
        return inventoryDao.getLocationByLaneAndShelf(lane,shelf)
    }
    fun getProductsByWarehouse(warehouseId:String):LiveData<List<WarehouseandProducts>>{
        return inventoryDao.getProductsByWarehouse(warehouseId)
    }
    fun updateLocation(location:Location){
        inventoryDao.updateLocation(location)
    }
    fun addUnit(unit: Units){
        inventoryDao.addUnit(unit)
    }
    fun getProductLocation(productLocationId:Int):LiveData<Location>{
        return inventoryDao.getProductLocation(productLocationId)
    }
    fun getAllUnits():LiveData<List<Units>>{
        return inventoryDao.getAllUnits()
    }
}