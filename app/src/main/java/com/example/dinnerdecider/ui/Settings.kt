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


class Settings : Fragment() {
    private var btnDark: Button? = null
    private var swDark: Switch? = null
    private var btnLanguage: Button? = null
    private var btnInfo: Button? = null
    private var btnFeedback: Button? = null
    private var dialog = DialogBox()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_settings, container, false)
        btnDark = v.findViewById(R.id.btn_dark)
        btnLanguage = v.findViewById(R.id.btn_language)
        btnInfo = v.findViewById(R.id.btn_info)
        btnFeedback = v.findViewById(R.id.btn_feedback)
        swDark = v.findViewById(R.id.switch_dark)

        // Save state of app using SharedPreferences
        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false)

        swDark!!.isChecked = isDarkModeOn

        btnLanguage!!.setOnClickListener { dialog.showDialogLanguage(resources.getString(
            R.string.title_language
        )
            ,resources.getString(R.string.detail_language), context) }
        btnInfo!!.setOnClickListener { dialog.showDialogBox(resources.getString(R.string.title_info)
            ,resources.getString(R.string.detail_info), context) }
        btnFeedback!!.setOnClickListener { dialog.showDialogBox(resources.getString(
            R.string.title_feedback
        )
            ,resources.getString(R.string.detail_feedback), context) }

        swDark?.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("isDarkModeOn", true)
                editor.apply()
                Toast.makeText(context, "Dark Mode On", Toast.LENGTH_SHORT).show()
            } else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("isDarkModeOn", false)
                editor.apply()
                Toast.makeText(context, "Dark Mode Off", Toast.LENGTH_SHORT).show()
            }
        }
        return v
    }
}