package com.example.dinnerdecider.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.dinnerdecider.model.CategoryModel
import com.example.dinnerdecider.model.CategoryViewAdapter
import com.example.dinnerdecider.model.CategoryViewModel
import com.example.dinnerdecider.R
import com.example.dinnerdecider.db.Categories
import com.example.dinnerdecider.db.CategoryDb
import com.example.dinnerdecider.util.DialogBox
import kotlinx.android.synthetic.main.fragment_choose_categories.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class ChooseCategories : Fragment() {
    private lateinit var categoryList: List<CategoryModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Get variables for items in view
        val v: View = inflater.inflate(R.layout.fragment_choose_categories, container, false)
        val categoryView: RecyclerView = v.recycler_categories
        val randomize: Button = v.btn_random
        val btnInfo: ImageButton = v.img_info
        val viewModel: CategoryViewModel = ViewModelProvider(this)
            .get(CategoryViewModel::class.java)

        viewModel.setInfo(requireContext())
        viewModel.setCategories()
        categoryList = viewModel.getCategories()
        // Testing out Room
        viewModel.getTempList()

        // Animate expansion of RecyclerView
        (categoryView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        // Set recyclerview layout and adapter
        categoryView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CategoryViewAdapter(categoryList, context)
        }

        btnInfo.setOnClickListener { DialogBox().showDialogBox(resources.getString(R.string.title_categories),
        resources.getString(R.string.detail_categories), context) }

        // If connected to internet, find selected categories and start next fragment
        randomize.setOnClickListener {
            if (viewModel.isConnected()){
                val result = findSelected().map{ it.title }.takeIf { it.size >= 2 }
                if (result != null){
                    Bundle().putStringArrayList(key, result as ArrayList<String>?)

                    NavHostFragment.findNavController(this)
                       .navigate(R.id.action_chooseCategories_to_spinWheel, Bundle())
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
    private fun findSelected() = categoryList.filter { (it.isClicked) }
    // Key for passed arguments
    companion object { const val key = "Michael is better than Eli" }
    // Reset data
    override fun onStop() {
        super.onStop()
        ViewModelProvider(this).get(CategoryViewModel::class.java).setCategories()
    }
}