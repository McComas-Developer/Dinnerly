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

class CustomViewAdapter(private val list: List<CategoryModel>, private val context: Context) :
    RecyclerView.Adapter<CustomViewAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(context)
        Log.d("Michael", "Create View Holder")
        return CategoryViewHolder(
            inflater.inflate(R.layout.view_categories, parent, false)
        )
    }
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Log.d("Michael", "Bind View Holder")
        val categories: String = list[position].title
        val model = list[position]
        holder.bind(categories)

        holder.itemView.ani_arrow.setOnClickListener {
            val expanded: Boolean = model.isExpanded
            model.isExpanded = !expanded
            holder.itemView.ani_arrow.playAnimation()
            notifyItemChanged(position)
        }
    }
    override fun getItemCount(): Int = list.size

    inner class CategoryViewHolder (mView: View) : RecyclerView.ViewHolder(mView) {
        private var mCategory: TextView? = null
        private var txtDesc: TextView? = null
        init{
            mCategory = mView.findViewById(R.id.btn_category)
            val mCheck: LottieAnimationView? = mView.findViewById(R.id.ani_check)
            txtDesc = mView.findViewById(R.id.txt_desc)
            mView.setOnClickListener {
                val model = list[adapterPosition]
                if(model.isClicked){
                    mCheck!!.progress = 0f
                    mCheck.pauseAnimation()
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
            val model = list[adapterPosition]
            mCategory?.text = category
            val expanded: Boolean = model.isExpanded
            txtDesc!!.visibility = if (expanded) View.VISIBLE else View.GONE
        }
    }
}