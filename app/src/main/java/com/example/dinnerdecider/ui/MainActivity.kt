package com.example.dinnerdecider.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.dinnerdecider.R


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = this.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false)

        // When user reopens the app, apply dark/light mode
        if (isDarkModeOn) { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES) }
        else { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) }
    }
}