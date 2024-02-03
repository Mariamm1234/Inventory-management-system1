package com.example.inventory1.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.inventory1.Room.Location
import com.example.inventory1.Room.Products
import com.example.inventory1.Room.RecentlyAddedItem
import com.example.inventory1.Room.RecentlyCompletedItem
import com.example.inventory1.Room.Units
import com.example.inventory1.Room.relations.LocationandProducts
import com.example.inventory1.Room.relations.UnitsandProducts
import com.example.inventory1.Room.relations.WarehouseandProducts

class InventoryViewModel(private val reposatory: InventoryReposatory):ViewModel (){

    fun getAllProducts(): LiveData<List<Products>>{
        return reposatory.getAllProducts()
    }
    fun addProduct(product:Products){
        reposatory.addProduct(product)
    }
    fun deleteProduct(product:Products){
        reposatory.deleteProduct(product)
    }
    fun recentlyAddedItems(item: RecentlyAddedItem){
        reposatory.recentlyAddedItems(item)
    }
    fun deleteItemRecentlyAdded(item:Products){
        reposatory.deleteItemRecentlyAdded(item)
    }
    fun showRecentItems():LiveData<List<RecentlyAddedItem>>{
        return reposatory.showRecentItems()
    }
    fun recentlyCompletedItems(item: RecentlyCompletedItem){
        reposatory.recentlyCompletedItems(item)
    }
    fun deleteItemCompleted(item: RecentlyCompletedItem){
        reposatory.deleteItemCompleted(item)
    }
    fun showCompletedItem():LiveData<List<RecentlyCompletedItem>>{
        return reposatory.showCompletedItem()
    }
    //edited
    fun getProduct(parcode:String):Products{
        return reposatory.getProduct(parcode)
    }
    fun getUnitsandProducts(unitName:String):LiveData<List<UnitsandProducts>>{
        return reposatory.getUnitsandProducts(unitName)
    }
    fun getLocationandProductsById(locationId:String):LiveData<List<LocationandProducts>>{
        return reposatory.getLocationandProductsById(locationId)
    }
    fun getLocationandProductsByLaneandshelf(lane:String,shelf:String):LiveData<List<LocationandProducts>>{
        return reposatory.getLocationandProductsByLaneandshelf(lane,shelf)
    }
//    fun getLocationandProductsBywarehouse(warehouseId:String):LiveData<List<LocationandProducts>>{
//        return reposatory.getLocationandProductsBywarehouse(warehouseId)
//    }
    fun updateProductQuantity(id:String,quantity:Int){
        reposatory.updateProductQuantity(id,quantity)
    }
    fun updateProduct(product: Products){
        reposatory.updateProduct(product)
    }
    fun getLocationByLaneAndShelf(lane: String,shelf: String):LiveData<List<Location>>{
       return reposatory.getLocationByLaneAndShelf(lane, shelf)
    }

    fun getProductsByWarehouse(warehouseId:String):LiveData<List<WarehouseandProducts>>
    {
        return reposatory.getProductsByWarehouse(warehouseId)
    }
    fun updateLocation(location:Location){
        reposatory.updateLocation(location)
    }
    fun addUnit(unit: Units){
        reposatory.addUnit(unit)
    }
    fun getProductLocation(productLocationId:Int):LiveData<Location>{
        return reposatory.getProductLocation(productLocationId)
    }
    fun getAllUnits():LiveData<List<Units>>{
        return reposatory.getAllUnits()
    }
}