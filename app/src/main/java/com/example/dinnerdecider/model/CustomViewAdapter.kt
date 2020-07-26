package com.example.dinnerdecider.model

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.dinnerdecider.R
import kotlinx.android.synthetic.main.view_categories.view.*

class CustomViewAdapter(private val list: List<CustomModel>, private val context: Context?) :
    RecyclerView.Adapter<CustomViewAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(context)
        Log.d("Michael", "Create View Holder")
        return CustomViewHolder(
            inflater.inflate(R.layout.view_custom, parent, false)
        )
    }
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        Log.d("Michael", "Bind View Holder")
        val categories: String = list[position].title
        holder.bind(categories)
    }
    override fun getItemCount(): Int = list.size

    inner class CustomViewHolder (mView: View) : RecyclerView.ViewHolder(mView) {
        private var mCategory: TextView? = null
        init{ mCategory = mView.findViewById(R.id.btn_category) }
        fun bind(category: String) { mCategory?.text = category }
    }

    // Adjust Adapter based on input
    fun setAdapter(newList: List<CustomModel>, from: Context?){
        CustomViewAdapter(newList, from)
        notifyDataSetChanged()
    }
}