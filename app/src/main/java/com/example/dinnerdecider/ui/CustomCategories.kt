package com.example.dinnerdecider.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
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


class CustomCategories : Fragment(){
    private lateinit var customList: List<CustomModel>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_custom_categories, container, false)
        val btnRandom: Button? = v.findViewById(R.id.btn_random)
        val customView: RecyclerView? = v.findViewById(R.id.recycler_custom)
        val customAmount: EditText? = v.findViewById(R.id.edtxt_number)
        val model: CustomViewModel = ViewModelProvider(this)
            .get(CustomViewModel::class.java)

        model.setList(4)
        customList = model.getCustom()
        val customAdapter = CustomViewAdapter(customList, context)
        customView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = customAdapter
        }

        /*customAmount!!.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                model.setList(Integer.parseInt(customAmount.text.toString()))
                customAdapter.notifyDataSetChanged()
                Toast.makeText(context, "text changed", Toast.LENGTH_SHORT).show()
            }
        }*/

        btnRandom!!.setOnClickListener {
            model.setList(Integer.parseInt(customAmount!!.text.toString()))
            customAdapter.notifyDataSetChanged()
            Toast.makeText(context, "text changed: ${model.getCustom()}", Toast.LENGTH_SHORT).show()
        }
        return v
    }
}