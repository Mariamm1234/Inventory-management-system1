package com.example.inventory1.MenuFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.LiveData
import com.example.inventory1.MVVM.InventoryReposatory
import com.example.inventory1.MVVM.InventoryViewModel
import com.example.inventory1.R
import com.example.inventory1.Room.AppDatabase
import com.example.inventory1.Room.Location
import com.example.inventory1.Room.Products
import com.example.inventory1.Room.relations.LocationandProducts
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Edit_productFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Edit_productFragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_edit_product, container, false)
        val dao= AppDatabase.invoke(context = view.context).inventoryDao()
        val repo= InventoryReposatory(dao)
        var userViewModel= InventoryViewModel(repo)
        val spinner=view.findViewById<Spinner>(R.id.select)
        val item= listOf("Choose","Quantity","Product","Location of warehouse")
        val arrayAdapter=ArrayAdapter(view.context,android.R.layout.simple_spinner_item,item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=arrayAdapter
        val productSpinner=view.findViewById<Spinner>(R.id.productOption)
        val options= listOf("Choose","Name","Location","Type","Company","Unit")
        val productAdapter=ArrayAdapter(view.context,android.R.layout.simple_spinner_item,options)
        productAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        productSpinner.adapter=productAdapter
        val locationOption=view.findViewById<Spinner>(R.id.locationOption)
        val adabter=ArrayAdapter(view.context,android.R.layout.simple_spinner_item,
            listOf("Choose","Lane","Shelf")
        )
        adabter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        locationOption.adapter=adabter
        val productId=view.findViewById<EditText>(R.id.ID)
        val name=view.findViewById<EditText>(R.id.Name)
        val location=view.findViewById<EditText>(R.id.locationID)
        val unit=view.findViewById<EditText>(R.id.unit)
        val type=view.findViewById<EditText>(R.id.type)
        val company=view.findViewById<EditText>(R.id.company)
        val quantity=view.findViewById<EditText>(R.id.quantity)
        val done=view.findViewById<Button>(R.id.done)
        val laneORshelf=view.findViewById<EditText>(R.id.laneORshelf)
        var product:Products?
        spinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem=parent?.getItemAtPosition(position).toString()
                when(selectedItem)
                {
                    "Choose"->{
                        locationOption.visibility=View.GONE
                        productSpinner.visibility=View.GONE
                        productId.visibility=View.GONE
                        name.visibility=View.GONE
                        location.visibility=View.GONE
                        unit.visibility=View.GONE
                        type.visibility=View.GONE
                        company.visibility=View.GONE
                        quantity.visibility=View.GONE
                        laneORshelf.visibility=View.GONE
                        done.visibility=View.GONE

                    }
                    "Quantity"->{
                        productSpinner.visibility=View.GONE
                        laneORshelf.visibility=View.GONE
                        locationOption.visibility=View.GONE
                        productId.visibility=View.VISIBLE
                        quantity.visibility=View.VISIBLE
                        done.visibility=View.VISIBLE
                        name.visibility=View.GONE
                        location.visibility=View.GONE
                        unit.visibility=View.GONE
                        type.visibility=View.GONE
                        company.visibility=View.GONE
                        done.setOnClickListener {
                            userViewModel.updateProductQuantity(productId.text.toString(), quantity.text.toString().toInt())
                            showSnackbar("Quantity updated")
                        }
                        productId.text=null
                        quantity.text=null
                    }
                    "Product"->{
                        laneORshelf.visibility=View.GONE
                        locationOption.visibility=View.GONE
                        productId.visibility=View.GONE
                        quantity.visibility=View.GONE
                        done.visibility=View.VISIBLE
                        productSpinner.visibility=View.VISIBLE
                        productSpinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                val selectOption=parent?.getItemAtPosition(position).toString()
                                when(selectOption)
                                {
                                    "Name"->{
                                        laneORshelf.visibility=View.GONE
                           productId.visibility=View.VISIBLE
                            name.visibility=View.VISIBLE
                                        done.visibility=View.VISIBLE
                                        location.visibility=View.GONE
                                        unit.visibility=View.GONE
                                        type.visibility=View.GONE
                                        company.visibility=View.GONE
                                        quantity.visibility=View.GONE

                                        done.setOnClickListener {
                                            product=userViewModel.getProduct(productId.text.toString())
                                            product!!.productName=name.text.toString()
                                            userViewModel.updateProduct(
                                            product!!
                                        )
                                        showSnackbar("Product updated")
                                        }
                                         productId.text=null
                                        name.text=null
                                    }
                                    "Location"->{
                                        laneORshelf.visibility=View.GONE
                                        productId.visibility=View.VISIBLE
                                        location.visibility=View.VISIBLE
                                        name.visibility=View.GONE
                                        unit.visibility=View.GONE
                                        type.visibility=View.GONE
                                        company.visibility=View.GONE
                                        quantity.visibility=View.GONE
                                        done.visibility=View.VISIBLE

                                        done.setOnClickListener {
                                            product=userViewModel.getProduct(productId.text.toString())
                                            product!!.productLocationId=location.text.toString().toInt()
                                            userViewModel.updateProduct(
                                            product!!
                                        )
                                            showSnackbar("Product updated")
                                        }
                                          location.text=null
                                        productId.text=null
                                    }
                                    "Unit"->{
                                        laneORshelf.visibility=View.GONE
                                        productId.visibility=View.VISIBLE
                                        unit.visibility=View.VISIBLE
                                        name.visibility=View.GONE
                                        location.visibility=View.GONE
                                        type.visibility=View.GONE
                                        company.visibility=View.GONE
                                        quantity.visibility=View.GONE
                                        done.visibility=View.VISIBLE

                                        done.setOnClickListener {
                                            product=userViewModel.getProduct(productId.text.toString())
                                            product!!.unitName=unit.text.toString()
                                            userViewModel.updateProduct(
                                            product!!
                                        )
                                            showSnackbar("Product updated")
                                        }
                                        unit.text=null
                                        productId.text=null

                                    }
                                    "Type"->{
                                        laneORshelf.visibility=View.GONE
                                        productId.visibility=View.VISIBLE
                                        type.visibility=View.VISIBLE
                                        name.visibility=View.GONE
                                        location.visibility=View.GONE
                                        unit.visibility=View.GONE
                                        company.visibility=View.GONE
                                        quantity.visibility=View.GONE
                                        done.visibility=View.VISIBLE

                                        done.setOnClickListener {
                                            product=userViewModel.getProduct(productId.text.toString())
                                            product!!.productType=type.text.toString()
                                            userViewModel.updateProduct(
                                            product!!
                                        )
                                            showSnackbar("Product updated")
                                        }
                                        type.text=null
                                        productId.text=null
                                    }
                                    "Company"->{
                                        laneORshelf.visibility=View.GONE
                                        productId.visibility=View.VISIBLE
                                        company.visibility=View.VISIBLE
                                        name.visibility=View.GONE
                                        location.visibility=View.GONE
                                        unit.visibility=View.GONE
                                        type.visibility=View.GONE
                                        quantity.visibility=View.GONE
                                        done.visibility=View.VISIBLE

                                        done.setOnClickListener {
                                            product=userViewModel.getProduct(productId.text.toString())
                                            product!!.productCompany=company.text.toString()
                                            userViewModel.updateProduct(
                                            product!!
                                        )
                                            showSnackbar("Product updated")
                                        }
                                        company.text=null
                                        productId.text=null
                                    }
                                }
                            }

                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                TODO("Not yet implemented")
                            }
                        }


                    }
                    "Location of warehouse"->{
//نعمل spinner ليه
                        laneORshelf.visibility=View.GONE
                        locationOption.visibility=View.VISIBLE
                        locationOption.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                val selected=parent?.getItemAtPosition(position).toString()
                                when(selected)
                                {
                                    "Choose"->{}
                                    "Lane"->{
                                        //productlocation
                                        productId.visibility=View.VISIBLE
                                        laneORshelf.visibility=View.VISIBLE
                                        productSpinner.visibility=View.GONE
                                        done.visibility=View.VISIBLE

                                        done.setOnClickListener {
                                            val product=userViewModel.getProduct(productId.text.toString())
                                            userViewModel.getProductLocation(product.productLocationId)
                                                .observe(viewLifecycleOwner) { useritem ->
                                                    useritem.lane=laneORshelf.text.toString()
                                                    userViewModel.updateLocation(useritem)
                                                }
                                            showSnackbar("Location updated")
                                        }
                                        productId.text=null
                                        laneORshelf.text=null

                                    }
                                    "Shelf"->{
                                        productId.visibility=View.VISIBLE
                                        laneORshelf.visibility=View.VISIBLE
                                        productSpinner.visibility=View.GONE
                                        done.visibility=View.VISIBLE
                                        done.setOnClickListener {
                                            val product=userViewModel.getProduct(productId.text.toString())

                                            userViewModel.getProductLocation(product.productLocationId)
                                                .observe(viewLifecycleOwner) { useritem ->
                                                    useritem.shelf=laneORshelf.text.toString()
                                                    userViewModel.updateLocation(useritem)
                                                }
                                            showSnackbar("Location updated")
                                        }
                                        productId.text=null
                                        laneORshelf.text=null
                                    }

                                }
                            }

                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                TODO("Not yet implemented")
                            }
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
         * @return A new instance of fragment Edit_productFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Edit_productFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun showSnackbar(message: String) {
        // Replace 'yourCoordinatorLayout' with the actual ID of the CoordinatorLayout or the root view of your fragment layout
        val rootView = view?.findViewById<View>(R.id.remove_product) ?: view

        if (rootView != null) {
            Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
        }
    }
}