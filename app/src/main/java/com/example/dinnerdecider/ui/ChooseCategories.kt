package com.example.dinnerdecider.ui

import android.os.Bundle
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
import com.example.dinnerdecider.CategoryModel
import com.example.dinnerdecider.CategoryViewAdapter
import com.example.dinnerdecider.CategoryViewModel
import com.example.dinnerdecider.R
import com.example.dinnerdecider.util.Connectivity
import com.example.dinnerdecider.util.DialogBox
import java.util.ArrayList


class ChooseCategories : Fragment() {
    private lateinit var categoryList: List<CategoryModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Get variables for items in view
        val v: View = inflater.inflate(R.layout.fragment_choose_categories, container, false)
        val dialog = DialogBox()
        val categoryView: RecyclerView? = v.findViewById(R.id.recycler_categories)
        val randomize: Button? = v.findViewById(R.id.btn_random)
        val btnInfo: ImageButton? = v.findViewById(R.id.img_info)
        categoryList = ViewModelProvider(this).get(CategoryViewModel::class.java).getCategories()

        // Animate expansion of RecyclerView
        (categoryView!!.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        // Set recyclerview layout and adapter
        categoryView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CategoryViewAdapter(categoryList, context)
        }

        btnInfo!!.setOnClickListener { dialog.showDialogBox(resources.getString(R.string.title_categories),
        resources.getString(R.string.detail_categories), context) }

        // Find selected categories and start next fragment
        randomize!!.setOnClickListener {
            if (Connectivity.isOnline){
                val result = findSelected().map{ it.title }.takeIf { it.size >= 2 }
                if (result != null){
                    val bundle = Bundle()
                    bundle.putStringArrayList(key, result as ArrayList<String>?)
                    NavHostFragment.findNavController(this)
                        .navigate(R.id.action_chooseCategories_to_showDecision, bundle)
                } else
                    Toast.makeText(context, "Please select at least two categories",
                        Toast.LENGTH_SHORT).show()
            }
            else{
                val dialog = DialogBox()
                dialog.showDialogBox(resources.getString(R.string.title_internet),
                    resources.getString(R.string.detail_internet), context)
            }
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
        ViewModelProvider(this).get(CategoryViewModel::class.java).resetCategories()
    }
}