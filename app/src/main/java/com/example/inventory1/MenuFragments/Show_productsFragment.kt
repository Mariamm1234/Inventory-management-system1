package com.example.inventory1.MenuFragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory1.MVVM.InventoryReposatory
import com.example.inventory1.MVVM.InventoryViewModel
import com.example.inventory1.R
import com.example.inventory1.Room.AppDatabase
import com.example.inventory1.Room.Location
import com.example.inventory1.Room.Products
import com.example.inventory1.recyclerView.RecyclerViewAdapterCompleted
import com.example.inventory1.recyclerView.RecyclerViewAdapterGetOrShow
import com.example.inventory1.recyclerView.RecyclerViewAdapterGetUnit
import recyclerView.RecyclerViewAdabter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Show_productsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Show_productsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_show_products, container, false)
        val dao= AppDatabase.invoke(context = view.context).inventoryDao()
        val repo= InventoryReposatory(dao)
        var userViewModel= InventoryViewModel(repo)
        val recyclerview = view.findViewById<RecyclerView>(R.id.rec)
        val spinner=view.findViewById<Spinner>(R.id.select)
        val option =view.findViewById<EditText>(R.id.option)
        val option2 =view.findViewById<EditText>(R.id.option2)
        val locationSelect=view.findViewById<Spinner>(R.id.location_select)
        val listItems= listOf("Choose","Completed Products","Products")
        val locationList= listOf("Choose","ID","Unit","Lane and Shelf","Warehouse")
        val arrayAdapter=ArrayAdapter(view.context,android.R.layout.simple_spinner_item,listItems)
        val scroll=view.findViewById<ScrollView>(R.id.scroll)
        val show=view.findViewById<Button>(R.id.show)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=arrayAdapter
        val locationAdapter=ArrayAdapter(view.context,android.R.layout.simple_spinner_item,locationList)
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        locationSelect.adapter=locationAdapter
        spinner.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem= parent?.getItemAtPosition(position).toString()
if(selectedItem=="Choose")
    locationSelect.visibility=View.GONE

                else if(selectedItem=="Completed Products")
                {
                    locationSelect.visibility=View.GONE
                    option.visibility=View.GONE
                    option2.visibility=View.GONE
                    show.visibility=View.GONE
                    scroll.visibility=View.VISIBLE
                    userViewModel.showCompletedItem().observe(viewLifecycleOwner) { userlist ->
                        val adabter = RecyclerViewAdapterCompleted(userlist)

                        recyclerview.adapter = adabter
                        if (view != null) {
                            recyclerview.layoutManager = LinearLayoutManager(view.context)
                        }
                    }

                }
                else{
                    locationSelect.visibility=View.VISIBLE
                  scroll.visibility=View.GONE
                    locationSelect.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            val selectedItem= parent?.getItemAtPosition(position).toString()
                           when(selectedItem)
                           {
                               "Choose"->{
                                   option.visibility=View.GONE
                                   option2.visibility=View.GONE
                                   scroll.visibility=View.GONE
                                   show.visibility=View.GONE
                               }
                               "ID"->{
                                   option.visibility=View.VISIBLE
                                   option2.visibility=View.GONE
                                   show.visibility=View.VISIBLE
                                   scroll.visibility=View.GONE
                                   option.hint="Enter Location ID"
                                   option.text=null
                                   show.setOnClickListener {
                                   userViewModel.getLocationandProductsById(option.text.toString()).observe(viewLifecycleOwner) { userlist ->
                                       val adabter = RecyclerViewAdapterGetOrShow(userlist)

                                       recyclerview.adapter = adabter
                                       if (view != null) {
                                           recyclerview.layoutManager = LinearLayoutManager(view.context)
                                       }
                                   }
                                       scroll.visibility=View.VISIBLE
                                   }

                               }

                                   "Warehouse"->{
                                       option.visibility=View.VISIBLE
                                       option2.visibility=View.GONE
                                       show.visibility=View.VISIBLE
                                       scroll.visibility=View.GONE
                                       option.hint="Enter Warehouse"
                                       option.text=null
                                       var producrs: List<Products>? =null
                                       show.setOnClickListener {
                                           userViewModel.getProductsByWarehouse(option.text.toString())
                                               .observe(viewLifecycleOwner){userlist->
                                                   for(i in 0..userlist.size-1)
                                                   { if (producrs != null) {

                                                       producrs += userViewModel.getProduct(userlist[i].productID)

                                                       val adabter = RecyclerViewAdabter(producrs)

                                                       recyclerview.adapter = adabter
                                                       if (view != null) {
                                                           recyclerview.layoutManager =
                                                               LinearLayoutManager(view.context)
                                                       }


                                                   } }
                                           }
//                                           userViewModel.getLocationandProductsBywarehouse(option.text.toString())
//                                               .observe(viewLifecycleOwner) { userlist ->
//                                                   val adabter =
//                                                       RecyclerViewAdapterGetOrShow(userlist)
//
//                                                   recyclerview.adapter = adabter
//                                                   if (view != null) {
//                                                       recyclerview.layoutManager =
//                                                           LinearLayoutManager(view.context)
//                                                   }
//                                               }
                                           scroll.visibility=View.VISIBLE
                                       }

                                   }
                               "Unit"->{
                                   option.visibility=View.VISIBLE
                                   option2.visibility=View.GONE
                                   show.visibility=View.VISIBLE
                                   scroll.visibility=View.GONE
                                   option.hint="Enter Unit"
                                   option.text=null
                                   show.setOnClickListener {
                                   userViewModel.getUnitsandProducts(option.text.toString()).observe(viewLifecycleOwner) { userlist ->
                                       val adabter = RecyclerViewAdapterGetUnit(userlist)

                                       recyclerview.adapter = adabter
                                       if (view != null) {
                                           recyclerview.layoutManager = LinearLayoutManager(view.context)
                                       }
                                   }
                                       scroll.visibility=View.VISIBLE
                                   }

                               }
                               "Lane and Shelf"->{
                                   option2.visibility=View.VISIBLE
                                   option.visibility=View.VISIBLE
                                   show.visibility=View.VISIBLE
                                   option.text=null
                                   option2.text=null
                                   option.hint="Enter Lane"
                                   option2.hint="Enter Shelf"
                                   scroll.visibility=View.GONE
                                   show.setOnClickListener {
//                                       val location:List<Location>
                                       userViewModel.getLocationByLaneAndShelf(option.text.toString(),option2.text.toString())
                                           .observe(viewLifecycleOwner){userlist->
                                               for (i in 0..userlist.size-1){
                                                   userViewModel.getLocationandProductsById(userlist[i].productLocationId.toString())
                                                       .observe(viewLifecycleOwner){product->
                                                           val adabter = RecyclerViewAdapterGetOrShow(product)
                                           recyclerview.adapter = adabter
                                           if (view != null) {
                                               recyclerview.layoutManager =
                                                   LinearLayoutManager(view.context)
                                           }
                                                       }
                                               }
                                           }
//                                       userViewModel.getLocationandProductsByLaneandshelf(
//                                           option.text.toString(),
//                                           option2.text.toString()
//                                       ).observe(viewLifecycleOwner) { userlist ->
//                                           val adabter = RecyclerViewAdapterGetOrShow(userlist)
//
//                                           recyclerview.adapter = adabter
//                                           if (view != null) {
//                                               recyclerview.layoutManager =
//                                                   LinearLayoutManager(view.context)
//                                           }
//                                       }
                                       scroll.visibility=View.VISIBLE
                                   }


                               }

                               }
                           }


                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }
                    }

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Show_productsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Show_productsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}