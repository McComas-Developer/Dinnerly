package com.example.dinnerdecider.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import com.example.dinnerdecider.R


class ChooseType : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_choose_type, container, false)
        val btnCustom: Button? = v.findViewById(R.id.btn_custom)
        val btnCategory: Button? = v.findViewById(R.id.btn_categories)

        btnCustom!!.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_chooseType_to_customCategories)
        }
        btnCategory!!.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_chooseType_to_chooseCategories)
        }
        return v
    }
}