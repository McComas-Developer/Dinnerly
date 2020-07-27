package com.example.dinnerdecider.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.adefruandta.spinningwheel.SpinningWheelView
import com.adefruandta.spinningwheel.SpinningWheelView.OnRotationListener
import com.example.dinnerdecider.R
import com.example.dinnerdecider.util.DialogBox
import kotlinx.android.synthetic.main.fragment_spin_wheel.view.*
import java.util.ArrayList

class SpinWheel : Fragment(){
    private var list = arrayListOf<String>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_spin_wheel, container, false)
        val wheelView = v.wheel as SpinningWheelView
        val dots = v.ani_dot

        // Grab list and shuffle to ensure same category is not repeatedly chosen
        list = arguments?.getStringArrayList("Michael is better than Eli") as ArrayList<String>
        list.shuffle()
        wheelView.items = list

        // If dark mode is on, use dark palette for wheel. Otherwise, use bright palette
        val sharedPreferences = context?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val isDarkModeOn = sharedPreferences?.getBoolean("isDarkModeOn", false)

        if (isDarkModeOn!!) {
            wheelView.colors = resources.getIntArray(R.array.palette_dark)
            dots.setAnimation("dots_dark.json")
        } else { wheelView.colors = resources.getIntArray(R.array.palette) }

        // Set listener for rotation event
        wheelView.onRotationListener = object : OnRotationListener<String> {
            override fun onRotation() { Log.d("Michael", "Rotating") }
            override fun onStopRotation(item: String) {
                DialogBox().showDialogBoxDecision(this@SpinWheel, context, item)
            }
        }

        dots.playAnimation()
        wheelView.isEnabled = false
        wheelView.rotate(50f, 8000, 50)
        return v
    }
}