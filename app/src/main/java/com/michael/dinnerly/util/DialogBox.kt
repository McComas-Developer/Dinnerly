package com.michael.dinnerly.util

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.dinnerdecider.R
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.view_dialog.view.*
import kotlinx.android.synthetic.main.view_dialog_ads.view.*
import kotlinx.android.synthetic.main.view_dialog_ads.view.txt_dialog
import kotlinx.android.synthetic.main.view_dialog_ads.view.txt_dialog_title
import kotlinx.android.synthetic.main.view_dialog_darkmode.view.*
import kotlinx.android.synthetic.main.view_dialog_decision.view.*
import kotlinx.android.synthetic.main.view_dialog_decision.view.adView

class DialogBox {
    // Basic Template Dialog Box
    fun showDialogBox(mTitle: String, mMsg: String, mFrom: Context?){
        val build = AlertDialog.Builder(mFrom)
        val inflater = LayoutInflater.from(mFrom)
        val v: View = inflater.inflate(R.layout.view_dialog, null)
        build.setView(v)
        val close = v.btn_ok
        val title = v.txt_dialog_title
        val msg = v.txt_dialog
        title.text = mTitle
        msg.text = mMsg
        val box: AlertDialog = build.create()
        box.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        close.setOnClickListener { box.dismiss() }
        box.show()
    }
    // Dialog box about Ads
    fun showDialogBoxAds(mFrom: Context?){
        val build = AlertDialog.Builder(mFrom)
        val inflater = LayoutInflater.from(mFrom)
        val v: View = inflater.inflate(R.layout.view_dialog_ads, null)
        build.setView(v)
        val close = v.btn_Cancel
        val upgrade = v.btn_upgrade
        val box: AlertDialog = build.create()
        box.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        close.setOnClickListener { box.dismiss() }
        upgrade.setOnClickListener {Toast.makeText(mFrom, "Coming Soon", Toast.LENGTH_SHORT).show()}
        box.show()
    }
    // Dark Mode Dialog Box
    fun showDialogBoxDarkMode(mFrom: Context?){
        val build = AlertDialog.Builder(mFrom)
        val inflater = LayoutInflater.from(mFrom)
        val v: View = inflater.inflate(R.layout.view_dialog_darkmode, null)
        build.setView(v)
        val cancel = v.btn_cancel
        val update = v.btn_update
        val manual = v.switch_manual
        val auto = v.switch_automatic
        val box: AlertDialog = build.create()
        box.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Save state of app using SharedPreferences
        val sharedPreferences: SharedPreferences = mFrom!!.getSharedPreferences("sharedPrefs",
            Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        manual.isChecked = sharedPreferences.getBoolean("isDarkModeOn", false)
        auto.isChecked = sharedPreferences.getBoolean("isOSDarkModeOn", false)

        manual.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                editor.putBoolean("isDarkModeOn", true)
                auto.isChecked = false
            }
            else editor.putBoolean("isDarkModeOn", false)
        }
        auto.setOnCheckedChangeListener { _ , isChecked ->
            if(isChecked){
                editor.putBoolean("isOSDarkModeOn", true)
                manual.isChecked = false
            }
            else editor.putBoolean("isOSDarkModeOn", false)
        }
        update.setOnClickListener {
            box.dismiss()
            when{
                auto.isChecked -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                manual.isChecked -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            editor.apply()
        }
        cancel.setOnClickListener { box.dismiss() }
        box.show()
    }
    // Dialog box about dinner decision
    fun showDialogBoxDecision(mFrom: Fragment, from: Context?, key: String){
        val build = AlertDialog.Builder(from)
        val inflater = LayoutInflater.from(from)
        val v: View = inflater.inflate(R.layout.view_dialog_decision, null)
        build.setView(v)
        val again = v.btn_again
        val web = v.web_search
        val choice = v.txt_decision
        val mAdView = v.adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        choice.text = "$key it is!"
        try {
            web.settings.javaScriptEnabled = true;
            web.loadUrl("https://www.google.com/search?q=$key restaurants near me")
        } catch (e: Exception) {
            Toast.makeText(from, mFrom.resources.getString(R.string.msg_choice_error), Toast.LENGTH_SHORT).show()
        }
        val box: AlertDialog = build.create()
        box.setCancelable(false)
        box.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        again.setOnClickListener {
            NavHostFragment.findNavController(mFrom).navigate(R.id.action_spinWheel_to_startFragment)
            box.cancel()
        }
        box.show()
    }
}