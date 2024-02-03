package com.example.inventory1.screens.ui.main

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory1.MVVM.InventoryReposatory
import com.example.inventory1.MVVM.InventoryViewModel
import com.example.inventory1.R
import com.example.inventory1.Room.AppDatabase
import com.example.inventory1.Room.Products
import com.example.inventory1.Room.RecentlyCompletedItem
import com.example.inventory1.recyclerView.RecyclerViewAdapterCompleted



class CompletedProductsFragment:Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view=inflater.inflate(R.layout.fragment_completed_products, container, false)
        val dao= AppDatabase.invoke(context = view.context).inventoryDao()
        val repo= InventoryReposatory(dao)
        var userViewModel= InventoryViewModel(repo)
        val show=view.findViewById<Button>(R.id.all_products)
        val recyclerview = view.findViewById<RecyclerView>(R.id.rec)

//        Products(productName = "milk", productID = "22225220", productQuantity = 8, productLocationId = 25, productCompany = "lamar", productType = "liquid", unitName = "letres")
       show.setOnClickListener {

         // userViewModel.recentlyCompletedItems(RecentlyCompletedItem(productName = "rice", productID = "5555566666", productQuantity = 5, productAmount = "package", productLocation = "88"))
           userViewModel.showCompletedItem().observe(viewLifecycleOwner,{userlist->
               val adabter = RecyclerViewAdapterCompleted(userlist)

               recyclerview.adapter = adabter
               recyclerview.layoutManager = LinearLayoutManager(view.context)
           })
       }

        return view
    }
}