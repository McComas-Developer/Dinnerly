package com.example.dinnerdecider.util

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.dinnerdecider.R
import kotlinx.android.synthetic.main.view_dialog.view.*
import kotlinx.android.synthetic.main.view_dialog_ads.view.*
import kotlinx.android.synthetic.main.view_dialog_ads.view.txt_dialog
import kotlinx.android.synthetic.main.view_dialog_ads.view.txt_dialog_title

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
        val close = v.btn_cancel
        val upgrade = v.btn_upgrade
        val box: AlertDialog = build.create()
        box.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        close.setOnClickListener { box.dismiss() }
        upgrade.setOnClickListener { /*TODO: When clicked, open link to upgrade app*/  }
        box.show()
    }
}