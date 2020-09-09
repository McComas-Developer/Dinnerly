package com.michael.dinnerly.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dinnerdecider.R
import com.google.android.gms.ads.MobileAds


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(applicationContext)
    }
}