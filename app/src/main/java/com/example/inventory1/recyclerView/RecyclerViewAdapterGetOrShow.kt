package com.example.inventory1.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory1.R
import com.example.inventory1.Room.relations.LocationandProducts

class RecyclerViewAdapterGetOrShow (var mlist: List<LocationandProducts>): RecyclerView.Adapter<RecyclerViewAdapterGetOrShow.ItemViewHolder>() {

    class ItemViewHolder: RecyclerView.ViewHolder{
        var id: TextView
        var name: TextView
        var locationID: TextView


        constructor(itemView: View):super(itemView){

            id=itemView.findViewById(R.id.id)
            name=itemView.findViewById(R.id.name)

            locationID=itemView.findViewById(R.id.location_id)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view= LayoutInflater.from(parent.context)
            //نغير الديزين نعمله واحد
            .inflate(R.layout.item_design3,parent,false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {

        return mlist.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.apply {
            //مش ده الي جاي مع الlist الي فوق ... اغيره
            id.text=mlist.get(position).products.get(position).productID
            name.text=mlist.get(position).products.get(position).productName

            locationID.text= mlist.get(position).location.ID.toString()


        }
    }
}