package com.example.dinnerdecider.ui

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.dinnerdecider.util.DialogBox
import com.example.dinnerdecider.R
import kotlinx.android.synthetic.main.fragment_settings.view.*


class Settings : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_settings, container, false)
        val dialog = DialogBox()
        val btnAds: Button = v.btn_ads
        val btnDark: Button = v.btn_dark
        val btnInfo: Button = v.btn_info
        val btnFeedback: Button = v.btn_feedback
        val swDark: Switch = v.switch_dark

        // Save state of app using SharedPreferences
        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false)

        swDark.isChecked = isDarkModeOn

        btnAds.setOnClickListener { dialog.showDialogBoxAds(context) }
        btnInfo.setOnClickListener { dialog.showDialogBox(resources.getString(R.string.title_info)
            ,resources.getString(R.string.detail_info), context) }
        btnFeedback.setOnClickListener { dialog.showDialogBox(resources.getString(
            R.string.title_feedback)
            ,resources.getString(R.string.detail_feedback), context) }

        swDark.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("isDarkModeOn", true)
                editor.apply()
                Toast.makeText(context, resources.getString(R.string.msg_Dark_On),
                    Toast.LENGTH_SHORT).show()
            } else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("isDarkModeOn", false)
                editor.apply()
                Toast.makeText(context, resources.getString(R.string.msg_Dark_Off),
                    Toast.LENGTH_SHORT).show()
            }
        }
        btnDark.setOnClickListener { swDark.performClick() }
        return v
    }
}