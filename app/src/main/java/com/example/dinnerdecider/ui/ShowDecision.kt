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
import java.util.*


class ShowDecision : Fragment() {
    private var chosen: TextView? = null
    private var searchKey: String? = null
    private var webSearch: WebView? = null
    private var list = arrayListOf<String>()
    private var btnAgain: Button? = null
    private val GOOGLE_SERACH_URL = "https://www.google.com/search?q="
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_show_decision, container, false)
        btnAgain = v.findViewById(R.id.btn_again)
        webSearch = v.findViewById(R.id.web_search)
        list = arguments?.getStringArrayList("Michael is better than Eli") as ArrayList<String>

        val rand = Random()
        searchKey = list[rand.nextInt(list.size)]

        chosen = v.findViewById(R.id.txt_decision)
        chosen?.text = "$searchKey it is"

        btnAgain!!.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_showDecision_to_startFragment)
        }
        onSearchClick(webSearch)
        return v
    }

    private fun onSearchClick(v: View?) {
        try {
            webSearch?.getSettings()?.setJavaScriptEnabled(true);
            webSearch?.loadUrl(GOOGLE_SERACH_URL + "$searchKey near me");
            /*val intent = Intent(Intent.ACTION_WEB_SEARCH)
            //val term: String = chosen?.text.toString()
            //intent.putExtra(SearchManager.QUERY, term)
            startActivity(intent)*/
        } catch (e: Exception) {
            // TODO: handle exception
        }
    }
}