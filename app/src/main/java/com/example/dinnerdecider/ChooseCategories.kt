package com.example.dinnerdecider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChooseCategories : Fragment() {
    private var categoryView: RecyclerView? = null
    private var randomize: Button? = null
    private var chosen = arrayListOf<String>()
    private var categoryList: List<CategoryModel> = listOf(
        CategoryModel("Fast food"),
        CategoryModel("Mexican"), CategoryModel("Chinese"), CategoryModel("Japanese"),
        CategoryModel("Thai"), CategoryModel("Southern"), CategoryModel("Italian")
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_choose_categories, container, false)

        categoryView = v.findViewById(R.id.recycler_categories)
        randomize = v.findViewById(R.id.btn_random)
        // Set recyclerview layout and adapter
        categoryView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CategoryViewAdapter(categoryList, context)
        }

        // Find selected categories and start next fragment
        randomize!!.setOnClickListener {
            val result = findSelected()
            if(result.isNotEmpty() && result.size >= 2) {
                for (i in 0 until result.count())
                    chosen.add(result[i].title)
                val bundle = Bundle()
                bundle.putStringArrayList(key, chosen)
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_chooseCategories_to_showDecision, bundle)
            }
            else
                Toast.makeText(context, "Please select at least two categories",
                    Toast.LENGTH_SHORT).show()
        }

        return v
    }

    // Find selected options
    private fun findSelected() = categoryList.filter { (it.isClicked) }

    companion object {
        const val key = "Michael is better than Eli"
    }

    // Reset data
    override fun onStop() {
        super.onStop()
        chosen.clear()
        categoryList = listOf(
            CategoryModel("Fast food"),
            CategoryModel("Mexican"), CategoryModel("Chinese"), CategoryModel("Japanese"),
            CategoryModel("Thai"), CategoryModel("Southern"), CategoryModel("Italian")
        )
    }
}