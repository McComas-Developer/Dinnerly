package com.example.dinnerdecider.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dinnerdecider.R
import com.example.dinnerdecider.model.CustomModel
import com.example.dinnerdecider.model.CustomViewAdapter
import com.example.dinnerdecider.model.CustomViewModel
import kotlinx.android.synthetic.main.fragment_custom_categories.view.*


class CustomCategories : Fragment(){
    private lateinit var customList: List<CustomModel>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_custom_categories, container, false)
        val btnRandom: Button = v.btn_random
        val customView: RecyclerView = v.recycler_custom
        val customAmount: EditText = v.edtxt_number
        val viewModel: CustomViewModel = ViewModelProvider(this)
            .get(CustomViewModel::class.java)

        viewModel.setList(2)
        customList = viewModel.getCustom()
        val customAdapter = CustomViewAdapter(customList, context)
        //TODO: Find way to adjust recycler view properly after new data is gathered
        customView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = customAdapter
        }
        //TODO: Adjust editText auto change for recycler view
        customAmount.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                viewModel.setList(Integer.parseInt(customAmount.text.toString()))
                customAdapter.setAdapter(viewModel.getCustom(), context)
                Toast.makeText(context, "text changed: ${viewModel.getCustom()}",
                    Toast.LENGTH_SHORT).show()
            }
        }
        
        btnRandom.setOnClickListener {
            /*TODO: Determine if connected to internet & make sure all text boxes have been filled*/
            /*TODO: If no issues, grab text box data and pass to "ShowDecision" */
        }
        return v
    }
}