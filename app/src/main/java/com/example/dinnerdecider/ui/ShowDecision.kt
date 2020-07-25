package com.example.dinnerdecider.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.dinnerdecider.R
import com.example.dinnerdecider.util.DialogBox
import kotlinx.android.synthetic.main.fragment_show_decision.view.*
import java.util.*

class ShowDecision : Fragment() {
    private var searchKey: String? = null
    private var list = arrayListOf<String>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Get variables for items in View
        val v: View = inflater.inflate(R.layout.fragment_show_decision, container, false)
        val btnAgain: Button = v.btn_again
        val chosen: TextView = v.txt_decision
        val webSearch: WebView = v.web_search
        // Randomly select a value from argument list
        list = arguments?.getStringArrayList("Michael is better than Eli") as ArrayList<String>
        val rand = Random()
        searchKey = list[rand.nextInt(list.size)]
        chosen.text = "$searchKey it is"
        // Navigate back to Main Screen
        btnAgain.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_showDecision_to_startFragment)
        }
        onSearchClick(webSearch)
        return v
    }
    // Search google with selected category
    private fun onSearchClick(v: WebView?) {
        try {
            v?.settings?.javaScriptEnabled = true;
            v?.loadUrl("https://www.google.com/search?q=$searchKey restaurants near me")
        } catch (e: Exception) {
            val dialog = DialogBox()
            dialog.showDialogBox(resources.getString(R.string.title_internet),
                resources.getString(R.string.detail_internet), context)
        }
    }
}