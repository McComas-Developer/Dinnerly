package com.example.dinnerdecider.ui

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adefruandta.spinningwheel.SpinningWheelView
import com.adefruandta.spinningwheel.SpinningWheelView.OnRotationListener
import com.airbnb.lottie.LottieAnimationView
import com.example.dinnerdecider.R
import com.example.dinnerdecider.util.DialogBox
import kotlinx.android.synthetic.main.fragment_spin_wheel.view.*
import java.util.ArrayList

class SpinWheel : Fragment(){
    private lateinit var dots: LottieAnimationView
    private var list = arrayListOf<String>()
    private lateinit var wheelView: SpinningWheelView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_spin_wheel, container, false)
        wheelView = v.wheel as SpinningWheelView
        dots = v.ani_dot

        // Grab list and shuffle to ensure same category is not repeatedly chosen
        list = arguments?.getStringArrayList("Michael is better than Eli") as ArrayList<String>
        list.shuffle()
        wheelView.items = list
        setWheelColors()

        // Set listener for rotation event
        wheelView.onRotationListener = object : OnRotationListener<String> {
            override fun onRotation() { Log.d("Michael", "Rotating") }
            override fun onStopRotation(item: String) {
                DialogBox().showDialogBoxDecision(this@SpinWheel, context, item)
                wheelView.onRotationListener = null
            }
        }
        dots.playAnimation()
        wheelView.isEnabled = false
        wheelView.rotate(50f, 8000, 50)
        return v
    }

    private fun setWheelColors(){
        if(resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES){
            wheelView.colors = resources.getIntArray(R.array.palette_dark)
            dots.setAnimation("dots_dark.json")
        } else{
            wheelView.colors = resources.getIntArray(R.array.palette)
            dots.setAnimation("dots.json")
        }
    }

    override fun onStop() {
        super.onStop()
        wheelView.onRotationListener = null
    }
}