package com.example.inventory1.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory1.R
import com.example.inventory1.Room.Units
import com.example.inventory1.Room.relations.LocationandProducts
import com.example.inventory1.Room.relations.UnitsandProducts

class RecyclerViewAdapterAllUnit (var mlist: List<Units>): RecyclerView.Adapter<RecyclerViewAdapterAllUnit.ItemViewHolder>() {

    class ItemViewHolder: RecyclerView.ViewHolder{

        var name: TextView
        var amount: TextView


        constructor(itemView: View):super(itemView){


            name=itemView.findViewById(R.id.name)
            amount=itemView.findViewById(R.id.amount)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view= LayoutInflater.from(parent.context)
            //نغير الديزين نعمله واحد
            .inflate(R.layout.item_design4,parent,false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {

        return mlist.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.apply {
            //مش ده الي جاي مع الlist الي فوق ... اغيره

            name.text= mlist.get(position).unitName
            //الunit مكان الlocation
           amount.text= mlist.get(position).unitAmount


        }
    }
}