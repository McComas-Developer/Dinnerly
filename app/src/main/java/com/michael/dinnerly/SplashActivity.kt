package com.michael.dinnerly

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.dinnerdecider.ui.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // When user reopens the app, apply dark/light mode
        val sharedPreferences = this.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false)
        val isOSDarkModeOn = sharedPreferences.getBoolean("isOSDarkModeOn", false)
        setDarkMode(isDarkModeOn, isOSDarkModeOn)

        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }
    // Set dark mode based on user preference
    private fun setDarkMode(manual: Boolean, automatic: Boolean){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        if(automatic)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        else if (manual)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}