package com.michael.dinnerly.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.michael.dinnerly.model.CategoryModel
import com.michael.dinnerly.model.CategoryViewAdapter
import com.michael.dinnerly.model.CategoryViewModel
import com.michael.dinnerly.R
import com.michael.dinnerly.db.Categories
import com.michael.dinnerly.util.DialogBox
import kotlinx.android.synthetic.main.fragment_choose_categories.view.*
import kotlin.collections.ArrayList

class ChooseCategories : Fragment() {
    private lateinit var categoryList: List<CategoryModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_choose_categories, container, false)
        val categoryView: RecyclerView = v.recycler_categories
        val randomize: Button = v.btn_random
        val btnInfo: ImageButton = v.img_info
        val viewModel: CategoryViewModel = ViewModelProvider(this)
            .get(CategoryViewModel::class.java)

        val catEng = resources.getStringArray(R.array.categories_english)
        val desEng = resources.getStringArray(R.array.descriptions_english)
        val catSpa = resources.getStringArray(R.array.categories_spanish)
        val desSpa = resources.getStringArray(R.array.descriptions_spanish)

        // Set database
        viewModel.setInfo(requireContext())
        (categoryView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        // Send Arrays to database for input if needed
        viewModel.setCategoryList(catEng, desEng, catSpa, desSpa)
        // Observe changes to database category list
        viewModel.getCategories().observe(viewLifecycleOwner, Observer {
            categoryList = it
            categoryView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = CategoryViewAdapter(categoryList, context)
            }
        })

        btnInfo.setOnClickListener { DialogBox().showDialogBox(resources.getString(R.string.title_categories),
        resources.getString(R.string.detail_categories), context) }

        // If connected to internet, find selected categories and start next fragment
        randomize.setOnClickListener {
            if (viewModel.isConnected()){
                val result = findSelected().map{ it.title }.takeIf { it.size >= 2 }
                Log.d("Michael", result.toString())
                if (!result.isNullOrEmpty()){
                    val bundle = Bundle()
                    bundle.putStringArrayList(key, result as ArrayList<String>)

                    NavHostFragment.findNavController(this)
                       .navigate(R.id.action_chooseCategories_to_spinWheel, bundle)
                }
                else Toast.makeText(context, resources.getString(R.string.msg_Categories),
                        Toast.LENGTH_SHORT).show()
            }
            else DialogBox().showDialogBox(resources.getString(R.string.title_internet),
                    resources.getString(R.string.detail_internet), context)
        }
        return v
    }
    // Find selected options
    private fun findSelected() = categoryList.filter { it.isClicked }
    // Key for passed arguments
    companion object { const val key = "Michael is better than Eli" }
    // Reset data
    override fun onStop() {
        super.onStop()
        ViewModelProvider(this).get(CategoryViewModel::class.java).getCategories()
    }
}