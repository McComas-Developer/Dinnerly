package com.example.dinnerdecider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChooseCategories : Fragment() {
    private var categoryView: RecyclerView? = null
    private var randomize: Button? = null
    private var categoryList: List<CategoryModel> = listOf(CategoryModel("Fast food"),
        CategoryModel("Mexican"), CategoryModel("Chinese"), CategoryModel("Japanese"),
        CategoryModel("Thai"), CategoryModel("Southern"))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_choose_categories, container, false)

        categoryView = v.findViewById(R.id.recycler_categories)
        randomize = v.findViewById(R.id.btn_random)
        // Set recyclerview layout and adapter
        categoryView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CategoryViewAdapter(categoryList, context)
        }

        randomize!!.setOnClickListener {
            val result = findSelected()
            var ans: String = ""
            for(i in 0 until result.count()){
                ans += result[i].title + ", "
            }
            Toast.makeText(context, "Got: $ans", Toast.LENGTH_SHORT).show()
        }
        return v
    }

    private fun findSelected() = categoryList.filter{(it.isClicked)}
}