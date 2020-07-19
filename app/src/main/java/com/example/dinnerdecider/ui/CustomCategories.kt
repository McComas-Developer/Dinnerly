package com.example.dinnerdecider.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.dinnerdecider.R
import com.example.dinnerdecider.model.CategoryViewModel
import com.example.dinnerdecider.model.CustomModel
import com.example.dinnerdecider.model.CustomViewModel

class CustomCategories : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_custom_categories, container, false)
        val btnRandom: Button? = v.findViewById(R.id.btn_random)
        val customView: RecyclerView? = v.findViewById(R.id.recycler_custom)
        val customAmount: EditText? = v.findViewById(R.id.edtxt_number)
        val model: CustomViewModel = ViewModelProvider(this)
            .get(CustomViewModel::class.java)


        return v
    }
}