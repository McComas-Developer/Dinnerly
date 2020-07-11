package com.example.dinnerdecider

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView

class CategoryViewAdapter(private val list: List<CategoryModel>, val contxt: Context) :
    RecyclerView.Adapter<CategoryViewAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewAdapter.CategoryViewHolder {
        val inflater = LayoutInflater.from(contxt)
        Log.d("Michael", "Create View Holder")
        return CategoryViewHolder(
            inflater.inflate(
                R.layout.view_categories, parent
                , false
            )
        )
    }
    override fun onBindViewHolder(holder: CategoryViewAdapter.CategoryViewHolder, position: Int) {
        Log.d("Michael", "Bind View Holder")
        val categories: String = list[position].title
        holder.bind(categories)
    }
    override fun getItemCount(): Int = list.size

    inner class CategoryViewHolder (mView: View) : RecyclerView.ViewHolder(mView) {

        private var mCategory: TextView? = null
        private var mCheck: LottieAnimationView? = null

        init {
            mCategory = mView.findViewById(R.id.btn_category)
            mCheck = mView.findViewById(R.id.ani_check)
            mView.setOnClickListener {
                val model = list[adapterPosition]
                if(model.isClicked){
                    mCheck!!.progress = 0f
                    mCheck!!.pauseAnimation()
                    model.isClicked = false
                    Log.d("Michael","Un-clicked")
                }
                else{
                    mCheck!!.playAnimation()
                    model.isClicked = true
                    Log.d("Michael","Clicked")
                }
            }
        }
        fun bind(category: String) {
            mCategory?.text = category
        }
    }
}