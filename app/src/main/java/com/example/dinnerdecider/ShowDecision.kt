package com.example.dinnerdecider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import java.util.*
import kotlin.collections.ArrayList

class ShowDecision : Fragment() {
    private var chosen: TextView? = null
    private var list = arrayListOf<String>()
    private var btnAgain: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_show_decision, container, false)
        btnAgain = v.findViewById(R.id.btn_again)
        list = arguments?.getStringArrayList("Michael is better than Eli") as ArrayList<String>

        val rand = Random()
        val randomChoice: String = list[rand.nextInt(list.size)]

        chosen = v.findViewById(R.id.txt_decision)
        chosen?.text = "$randomChoice it is!"

        btnAgain!!.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_showDecision_to_startFragment)
        }

        return v
    }
}