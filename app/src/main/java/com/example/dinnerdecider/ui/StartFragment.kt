package com.example.dinnerdecider.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import com.example.dinnerdecider.R
import kotlinx.android.synthetic.main.fragment_start.view.*

class StartFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val v :View = inflater.inflate(R.layout.fragment_start, container, false)
        val btnCategories: Button = v.btn_byCategories
        val btnCustom: Button = v.btn_byCustom
        val imgAppIcon: ImageView = v.img_app_icon
        val btnSettings: ImageButton = v.btn_settings

        //TODO: If Room database tables are empty, add data based on language

        // Set image icon based on dark/light mode
        val sharedPreferences = context?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val isDarkModeOn = sharedPreferences?.getBoolean("isDarkModeOn", false)

        if (isDarkModeOn!!) { imgAppIcon.setImageResource(R.drawable.ic_app_dark) }
        else { imgAppIcon.setImageResource(R.drawable.ic_app) }

        btnCategories.setOnClickListener{
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_startFragment_to_chooseCategories)
        }
        btnCustom.setOnClickListener{
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_startFragment_to_customCategories)
        }
        btnSettings.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_startFragment_to_settings)
        }
        return v
    }
}