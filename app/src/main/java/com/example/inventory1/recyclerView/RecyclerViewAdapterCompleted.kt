package com.example.inventory1.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory1.R
import com.example.inventory1.Room.Products
import com.example.inventory1.Room.RecentlyCompletedItem
import recyclerView.RecyclerViewAdabter

class RecyclerViewAdapterCompleted (var mlist: List<RecentlyCompletedItem>): RecyclerView.Adapter<RecyclerViewAdapterCompleted.ItemViewHolder>() {

    class ItemViewHolder: RecyclerView.ViewHolder{
        var id: TextView
        var name: TextView
        var locationID: TextView
      var amount:TextView
        var quantity: TextView

        constructor(itemView: View):super(itemView){

            id=itemView.findViewById(R.id.id)
            name=itemView.findViewById(R.id.name)
            locationID=itemView.findViewById(R.id.location_id)
           amount=itemView.findViewById(R.id.type)
            quantity=itemView.findViewById(R.id.quantity)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.item_design2,parent,false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {

        return mlist.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.apply {
            id.text=mlist.get(position).productID
            name.text=mlist.get(position).productName
            locationID.text=mlist.get(position).productLocation
           amount.text=mlist.get(position).productAmount
            quantity.text=mlist.get(position).productQuantity.toString()

        }
    }
}