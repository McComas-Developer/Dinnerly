package com.michael.dinnerly.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

        btnAds.setOnClickListener { dialog.showDialogBoxAds(context) }
        btnInfo.setOnClickListener { dialog.showDialogBox(resources.getString(R.string.title_info)
            ,resources.getString(R.string.detail_info), context) }
        btnFeedback.setOnClickListener { dialog.showDialogBox(resources.getString(
            R.string.title_feedback)
            ,resources.getString(R.string.detail_feedback), context) }

        btnDark.setOnClickListener { dialog.showDialogBoxDarkMode(context) }
        return v
    }
}