package com.example.dinnerdecider.model

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.dinnerdecider.R

class CustomViewAdapter(list: List<CustomModel>, private val context: Context?) :
    RecyclerView.Adapter<CustomViewAdapter.CustomViewHolder>() {

    var customList: List<CustomModel> = list
    set(newList){
        field = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(context)
        Log.d("Michael", "Create View Holder")
        return CustomViewHolder(
            inflater.inflate(R.layout.view_custom, parent, false)
        )
    }
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        Log.d("Michael", "Bind View Holder")
    }
    override fun getItemCount(): Int = customList.size

    inner class CustomViewHolder (mView: View) : RecyclerView.ViewHolder(mView) {
        private var mCategory: EditText = mView.findViewById(R.id.edit_txt_category)

        init{
            mCategory.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    customList[adapterPosition].title = mCategory.text.toString()
                }
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int){}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int){}
            })
        }
    }
}