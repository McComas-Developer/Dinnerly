package com.example.dinnerdecider

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment

class StartFragment : Fragment() {
    var btnStart: Button? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v :View = inflater.inflate(R.layout.fragment_start, container, false)
        btnStart = v.findViewById(R.id.btn_start)

        btnStart!!.setOnClickListener{
            NavHostFragment.findNavController(this).navigate(R.id.action_startFragment_to_chooseCategories)
        }
        return v
    }

}