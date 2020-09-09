package com.michael.dinnerly.ui

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
import com.michael.dinnerly.R
import com.michael.dinnerly.util.DialogBox
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.fragment_spin_wheel.view.*
import kotlinx.android.synthetic.main.fragment_spin_wheel.view.adView
import java.util.ArrayList

class SpinWheel : Fragment(){
    private lateinit var dots: LottieAnimationView
    private var list = arrayListOf<String>()
    private lateinit var wheelView: SpinningWheelView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_spin_wheel, container, false)
        val mAdView = v.adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
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