package com.example.dinnerdecider.util

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.dinnerdecider.R

class DialogBox {
    // Basic Template Dialog Box
    fun showDialogBox(mTitle: String, mMsg: String, mFrom: Context?){
        // Set Up Dialog box
        val build = AlertDialog.Builder(mFrom)
        val inflater = LayoutInflater.from(mFrom)
        val v: View = inflater.inflate(R.layout.view_dialog, null)
        build.setView(v)
        val close = v.findViewById<Button>(R.id.btn_ok)
        val title = v.findViewById<TextView>(R.id.txt_dialog_title)
        val msg = v.findViewById<TextView>(R.id.txt_dialog)
        title.text = mTitle
        msg.text = mMsg
        val box: AlertDialog = build.create()
        box.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        close.setOnClickListener { box.dismiss() }
        box.show()
    }
    fun showDialogLanguage(mTitle: String, mMsg: String, mFrom: Context?){
        // Set Up Dialog box
        val build = AlertDialog.Builder(mFrom)
        val inflater = LayoutInflater.from(mFrom)
        val v: View = inflater.inflate(R.layout.view_dialog_language, null)
        build.setView(v)
        val close = v.findViewById<Button>(R.id.btn_cancel)
        val update = v.findViewById<Button>(R.id.btn_update)
        val title = v.findViewById<TextView>(R.id.txt_dialog_title)
        val msg = v.findViewById<TextView>(R.id.txt_dialog)
        title.text = mTitle
        msg.text = mMsg
        val box: AlertDialog = build.create()
        box.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        close.setOnClickListener { box.dismiss() }
        box.show()
    }
}