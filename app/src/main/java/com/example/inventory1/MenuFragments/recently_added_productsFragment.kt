package com.example.inventory1.MenuFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.inventory1.MVVM.InventoryReposatory
import com.example.inventory1.MVVM.InventoryViewModel
import com.example.inventory1.R
import com.example.inventory1.Room.AppDatabase
import com.example.inventory1.Room.Products
import com.example.inventory1.Room.RecentlyAddedItem
import com.example.inventory1.databinding.ActivityHomeBinding
import com.example.inventory1.databinding.FragmentRecentlyAddedProductsBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [recently_added_productsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class recently_added_productsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentRecentlyAddedProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId", "ShowToast")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_recently_added_products, container, false)
        val id=view.findViewById<EditText>(R.id.id)
        val name=view.findViewById<EditText>(R.id.name)
        val quantity=view.findViewById<EditText>(R.id.quantity)
        val button=view.findViewById<Button>(R.id.add_product)
        val unit=view.findViewById<EditText>(R.id.unit)
        val type=view.findViewById<EditText>(R.id.type)
        val company=view.findViewById<EditText>(R.id.company)
        val location=view.findViewById<EditText>(R.id.location)
        binding = FragmentRecentlyAddedProductsBinding.inflate(layoutInflater)
      var root=binding.root

        val dao= AppDatabase.invoke(context = view.context).inventoryDao()
        val repo= InventoryReposatory(dao)
        var userViewModel= InventoryViewModel(repo)
//        if (binding.location.text.toString().toInt()!=0)
//        {
//            binding.locationDetails.visibility=View.VISIBLE
//        }

        button.setOnClickListener {
//          Log.d("error:",binding.quantity.text.toString())
         userViewModel.addProduct(Products(productID = id.text.toString(),
             productName =name.text.toString(),
             productQuantity = quantity.text.toString().toInt(),
             unitName = unit.text.toString(),
             productType = type.text.toString(),
             productCompany = company.text.toString(),
             productLocationId = location.text.toString().toInt()))
            userViewModel.recentlyAddedItems(RecentlyAddedItem(productID =id.text.toString(),
                productName =name.text.toString(),
                productQuantity = quantity.text.toString().toInt(),
                productAmount = unit.text.toString(),
                productType = type.text.toString(),
                productCompany = company.text.toString(),
               productLocation = location.text.toString().toInt(), addTime = Calendar.getInstance().time.toString() ))
            var text="Item added successfuly"
            Snackbar.make(binding.root, text, Snackbar.LENGTH_INDEFINITE).apply {
                view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)?.run {
                    maxLines = 5
                    setTextIsSelectable(true)
                }
        }
        }
        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment recently_added_productsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            recently_added_productsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}