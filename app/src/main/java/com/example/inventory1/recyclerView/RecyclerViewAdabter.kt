package recyclerView

import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory1.R
import com.example.inventory1.Room.Products
import com.example.inventory1.databinding.ActivitySplashBinding


class RecyclerViewAdabter(var mlist: List<Products>): RecyclerView.Adapter<RecyclerViewAdabter.ItemViewHolder>() {

    class ItemViewHolder:RecyclerView.ViewHolder{
 var id:TextView
         var name:TextView
         var locationID:TextView
         var unit:TextView
       var quantity:TextView
         var company:TextView
         var type:TextView
        constructor(itemView:View):super(itemView){

            id=itemView.findViewById(R.id.id)
            name=itemView.findViewById(R.id.name)
            locationID=itemView.findViewById(R.id.location_id)
            unit=itemView.findViewById(R.id. unit)
            quantity=itemView.findViewById(R.id.quantity)
            company=itemView.findViewById(R.id.company)
            type=itemView.findViewById(R.id.type)

        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
       val view=LayoutInflater.from(parent.context)
           .inflate(R.layout.item_design,parent,false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {

        return mlist.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.apply {
            id.text=mlist.get(position).productID
            name.text=mlist.get(position).productName
            locationID.text=mlist.get(position).productLocationId.toString()
            unit.text=mlist.get(position).unitName
            quantity.text=mlist.get(position).productQuantity.toString()
            type.text=mlist.get(position).productType
            company.text=mlist.get(position).productCompany
        }
    }

}