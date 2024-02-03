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
import android.widget.ScrollView
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory1.MVVM.InventoryReposatory
import com.example.inventory1.MVVM.InventoryViewModel
import com.example.inventory1.R
import com.example.inventory1.Room.AppDatabase
import com.example.inventory1.Room.Units
import com.example.inventory1.recyclerView.RecyclerViewAdapterAllUnit
import com.example.inventory1.recyclerView.RecyclerViewAdapterCompleted
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UnitsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UnitsFragment : Fragment() {
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
        val view=inflater.inflate(R.layout.fragment_units, container, false)
        val dao= AppDatabase.invoke(context = view.context).inventoryDao()
        val repo= InventoryReposatory(dao)
        var userViewModel= InventoryViewModel(repo)
        val recyclerview = view.findViewById<RecyclerView>(R.id.rec)
        val name=view.findViewById<EditText>(R.id.unit_name)
        val amount=view.findViewById<EditText>(R.id.unit_amount)
        val spinner=view.findViewById<Spinner>(R.id.select)
        val items= listOf("Choose","All units","Add unit")
        val arrayAdapter=ArrayAdapter(view.context,android.R.layout.simple_spinner_item,items)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=arrayAdapter
        val show=view.findViewById<Button>(R.id.show)
        val scroll=view.findViewById<ScrollView>(R.id.scroll)
        spinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selected=parent?.getItemAtPosition(position).toString()
                when(selected)
                {
                    "Choose"->{
                        name.visibility=View.GONE
                        amount.visibility=View.GONE
                        show.visibility=View.GONE
                        recyclerview.visibility=View.GONE
                    }
                    "All units"->{
                        recyclerview.visibility=View.VISIBLE
                        name.visibility=View.GONE
                        amount.visibility=View.GONE
                        show.visibility=View.GONE
                        userViewModel.getAllUnits().observe(viewLifecycleOwner){userlist->
                            val adabter = RecyclerViewAdapterAllUnit(userlist)

                            recyclerview.adapter = adabter
                            if (view != null) {
                                recyclerview.layoutManager = LinearLayoutManager(view.context)
                            }
                        }

                    }
                    "Add unit"->{
                        recyclerview.visibility=View.GONE
                        name.visibility=View.VISIBLE
                        amount.visibility=View.VISIBLE
                        show.visibility=View.VISIBLE
                        show.setOnClickListener {
                        val unit=Units(unitName = name.text.toString(), unitAmount = amount.text.toString())
                            userViewModel.addUnit(unit)
                            showSnackbar("Unit added")
                        }
                        name.text=null
                        amount.text=null
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
         * @return A new instance of fragment UnitsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UnitsFragment().apply {
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