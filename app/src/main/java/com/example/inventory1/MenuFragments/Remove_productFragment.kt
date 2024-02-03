package com.example.inventory1.MenuFragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.inventory1.MVVM.InventoryReposatory
import com.example.inventory1.MVVM.InventoryViewModel
import com.example.inventory1.R
import com.example.inventory1.Room.AppDatabase
import com.example.inventory1.screens.Home
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Remove_productFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Remove_productFragment : Fragment() {
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
        val view=inflater.inflate(R.layout.fragment_remove_product, container, false)
        val show=view.findViewById<Button>(R.id.remove_product)
        val dao= AppDatabase.invoke(context = view.context).inventoryDao()
        val repo= InventoryReposatory(dao)
        var userViewModel= InventoryViewModel(repo)

        show.setOnClickListener {
            val par=view.findViewById<EditText>(R.id.parcode)
            val item=userViewModel.getProduct(par.text.toString())
            userViewModel.deleteProduct(item)
//            Snackbar.make(view.rootView, "Product deleted", Snackbar.LENGTH_INDEFINITE).apply {
//                view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)?.run {
//                    maxLines = 5
//                    setTextIsSelectable(true)
//                } // test it
            showSnackbar("product deleted")
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
         * @return A new instance of fragment Remove_productFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Remove_productFragment().apply {
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