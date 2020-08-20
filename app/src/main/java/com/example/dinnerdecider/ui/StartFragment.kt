package com.example.dinnerdecider.ui

import android.content.Context
import android.content.res.Configuration
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
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.fragment_start.view.*

class StartFragment : Fragment(){
    private lateinit var imgAppIcon: ImageView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val v :View = inflater.inflate(R.layout.fragment_start, container, false)
        val btnCategories: Button = v.btn_byCategories
        val btnCustom: Button = v.btn_byCustom
        imgAppIcon = v.img_app_icon
        val btnSettings: ImageButton = v.btn_settings

        val mAdView = v.adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        // Set image icon based on dark/light mode
        val sharedPreferences = requireContext().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false)
        val isOSDarkModeOn = sharedPreferences.getBoolean("isOSDarkModeOn", false)

        setIcon(isDarkModeOn, isOSDarkModeOn)

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

    private fun setIcon(manual: Boolean, automatic: Boolean){
        if(automatic){
            //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            if(resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES)
                imgAppIcon.setImageResource(R.drawable.ic_app_dark)
            else
                imgAppIcon.setImageResource(R.drawable.ic_app)
            return
        }
        if (manual){ imgAppIcon.setImageResource(R.drawable.ic_app_dark) }
        else { imgAppIcon.setImageResource(R.drawable.ic_app) }
    }
}