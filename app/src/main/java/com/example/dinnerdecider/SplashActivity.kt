package com.example.dinnerdecider

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
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

    private fun setDarkMode(manual: Boolean, automatic: Boolean){
        if(automatic) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            return
        }
        if (manual)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}