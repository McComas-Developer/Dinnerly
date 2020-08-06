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

//TODO: Resolve error when clicking item in view (other items clicked but not clicked in model)
class CategoryViewAdapter(private val list: List<CategoryModel>, private val context: Context) :
    RecyclerView.Adapter<CategoryViewAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(context)
        return CategoryViewHolder(
            inflater.inflate(R.layout.view_categories, parent, false)
        )
    }
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val model = list[position]
        val category: String = model.title
        holder.mCategory.text = category

        val expanded: Boolean = model.isExpanded
        if(model.isClicked){
            if(!holder.mCheck.isAnimating)
                holder.mCheck.progress = 1f
        }
        else{
            holder.mCheck.progress = 0f
        }
        holder.txtDesc.visibility = if (expanded) View.VISIBLE else View.GONE
    }
    override fun getItemCount(): Int = list.size

    inner class CategoryViewHolder (mView: View) : RecyclerView.ViewHolder(mView) {
        var mCategory: TextView = mView.txt_category
        var txtDesc: TextView = mView.txt_desc
        var mCheck: LottieAnimationView = mView.ani_check
        private var mArrow: LottieAnimationView = mView.ani_arrow
        init{
            mView.setOnClickListener {
                val model = list[adapterPosition]
                if(model.isClicked){
                    //mCheck.progress = 0f
                    //mCheck.pauseAnimation()
                    model.isClicked = false
                    Log.d("Michael","Un-clicked")
                }
                else{
                    mCheck.playAnimation()
                    model.isClicked = true
                    Log.d("Michael","Clicked")
                }
                notifyItemChanged(adapterPosition)
            }
            mArrow.setOnClickListener {
                val model = list[adapterPosition]
                model.isExpanded = !model.isExpanded
                mArrow.playAnimation()
                notifyItemChanged(adapterPosition)
            }
        }
    }
}

/*fun LottieAnimationView.isAnimating(): Boolean {
    return this.progress > 0f && this.progress < 1f
}*/